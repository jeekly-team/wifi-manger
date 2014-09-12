/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.service.shop;

import com.github.dactiv.orm.core.PropertyFilter;
import com.github.dactiv.orm.core.spring.data.jpa.JpaRestrictionBuilder;
import com.github.dactiv.orm.core.spring.data.jpa.specification.SpecificationEntity;
import com.google.common.collect.ImmutableMap;
import com.jyzn.wifi.dao.shop.ValidateLogJpaDao;
import com.jyzn.wifi.entity.shop.ValidateLog;
import com.jyzn.wifi.entity.shop.summary.WifiUserCount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional("jpaTransactionManager")
public class ShopService {

    @Autowired
    private ValidateLogJpaDao jpaDao;

    @PersistenceContext
    private EntityManager em;

    /*废柴 不靠谱 浪费老子太多时间*/
    public Page findValidateLogPageByFilters(List<PropertyFilter> filters, Pageable page) {
        return jpaDao.findAll(ValidateLogSpecifications.toPredicate(filters), page);
    }

    public Page<?> findWifiUserCountBySid(String sid, Pageable pageable) {
        return jpaDao.findWifiUserCountBySid(sid, pageable);
    }

    /*JPA Criteria动态条件查询ValidateLog 并分组/ 获取需要的字段/ 注入到WifiUserCount*/
    public Map findWifiUserCountByFilters(List<PropertyFilter> filters, Pageable page) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        //根据条件查询
        CriteriaQuery<WifiUserCount> query = criteriaBuilder.createQuery(WifiUserCount.class);
        Root<ValidateLog> logroot = query.from(ValidateLog.class);
        Path uid = logroot.get("wifiuser");
        Path dt = logroot.get("dt");
        query.select(criteriaBuilder.construct(WifiUserCount.class, logroot.get("wifiuser"), criteriaBuilder.count(logroot), criteriaBuilder.max(dt), criteriaBuilder.min(dt)))
                .distinct(true)
                .where(toPredicate(filters, logroot, query, criteriaBuilder))
                .groupBy(uid);
        //统计记录总数,jpa不支持select count(o) from (select o from o where...)下面语句造成重复查询 期待更好的解决方法
        int count = em.createQuery(query).getResultList().size();
        //返回数据
        List resultList = em.createQuery(query).setFirstResult((page.getPageNumber() - 1) * page.getPageSize()).setMaxResults(page.getPageSize()).getResultList();

        return ImmutableMap.of(
                "pageNumber", page.getPageNumber(),
                // "totalPages", count % page.getPageSize() == 0  ?  (int)(count / page.getPageSize()) :(int)(count / page.getPageSize()) + 1 ,
                "totalPages", (int) Math.ceil(count * 1.0 / page.getPageSize()),
                "totalElements", count,
                "PageContent", resultList);
    }

    /*构造分页参数*/
    public Pageable constructPageSpecification(int pageIndex, int pageSize) {
        Pageable pageSpecification = new PageRequest(pageIndex, pageSize);
        return pageSpecification;
    }

    /*构建查询条件*/
    private Predicate toPredicate(List<PropertyFilter> filters, Root root, CriteriaQuery query, CriteriaBuilder builder) {
        List<Predicate> list = new ArrayList<>();
        for (PropertyFilter filter : filters) {
            list.add(JpaRestrictionBuilder.getRestriction(filter, new SpecificationEntity(root, query, builder)));
        }
        return list.size() > 0 ? builder.and(list.toArray(new Predicate[list.size()])) : null;

    }

    public List<Object[]> findLog() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        /* FROM */
        Root<ValidateLog> root = criteriaQuery.from(ValidateLog.class);
        criteriaQuery.multiselect(root.get("wifiuser"), criteriaBuilder.max((Path) root.get("dt")));
        criteriaQuery.groupBy(root.get("wifiuser"));
        return em.createQuery(criteriaQuery).getResultList();
    }

}
