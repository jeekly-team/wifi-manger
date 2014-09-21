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
import com.jyzn.wifi.dao.shop.WifiUserDao;
import com.jyzn.wifi.dao.shop.WifiUserGroupDao;
import com.jyzn.wifi.entity.shop.ValidateLog;
import com.jyzn.wifi.entity.shop.WifiUser;
import com.jyzn.wifi.entity.shop.WifiUserGroup;
import com.jyzn.wifi.entity.shop.summary.WifiUserCount;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/*
 此类默认使用jpaTransactionManager事务;
 在此Service类中,对继承 HibernateSupportDao 的用户自定义Dao,在其方法上注解HibernateSupportDao事务@Transactional("transactionManager")
 否则将出现org.hibernate.HibernateException: No Session found for current thread
 */

@Component
@Transactional("jpaTransactionManager")
public class ShopService {

    @Autowired
    private ValidateLogJpaDao jpaDao;
    @Autowired
    private WifiUserGroupDao groupDao;
    @Autowired
    private WifiUserDao userDao;

    @PersistenceContext
    private EntityManager em;

    /*废柴 不靠谱 浪费老子太多时间*/
    public Page findValidateLogsByFilters(List<PropertyFilter> filters, Pageable page) {
        return jpaDao.findAll(ValidateLogSpecifications.toPredicate(filters), page);
    }

    public List<ValidateLog> findValidateLogsByFilters(List<PropertyFilter> filters) {
        return jpaDao.findAll(ValidateLogSpecifications.toPredicate(filters));
    }

    public Page<?> findWifiUserCountBySid(String sid, Pageable pageable) {
        return jpaDao.findWifiUserCountBySid(sid, pageable);
    }

    /*JPA Criteria动态条件查询ValidateLog 并分组/ 获取需要的字段/ 注入到WifiUserCount*/
    public Map findWifiUserCountByFilters(List<PropertyFilter> filters, int t, Pageable page) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        //根据条件查询
        final CriteriaQuery<WifiUserCount> query = cb.createQuery(WifiUserCount.class);
        final Root<ValidateLog> logroot = query.from(ValidateLog.class);
        final Path wu = logroot.get("wifiuser");
        final Path dt = logroot.get("dt");

        if (filters.size() > 0) {
            //过滤出grid,且filters最后一个元素必须是grid
            String SinglePropertyName = filters.get(filters.size() - 1).getSinglePropertyName();
            if (StringUtils.isNotEmpty(SinglePropertyName) && "grid".equals(SinglePropertyName)) {
                Root<WifiUserGroup> group = query.from(WifiUserGroup.class);
                Predicate p = cb.equal(group.get("id"), filters.get(filters.size() - 1).getMatchValue());
                filters.remove(filters.size() - 1);
                Join ml_join = group.join("membersList", JoinType.LEFT);
                query.where(
                        cb.equal(ml_join.get("id"), wu.get("id")),
                        toPredicate(filters, logroot, query, cb),
                        p
                );
            } else {
                query.where(toPredicate(filters, logroot, query, cb));
            }
        }

        query.select(cb.construct(WifiUserCount.class, logroot.get("wifiuser"), cb.count(logroot), cb.max(dt), cb.min(dt)))
                .distinct(true)
                .groupBy(wu)
                .having(cb.ge(cb.count(logroot), t));
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

    public void delValidateLogs(List<ValidateLog> logList) {
        jpaDao.delete(logList);
    }   
    //----------------------------------------------------------------------------------//

    @Transactional("transactionManager")
    public List<WifiUserGroup> findGroupBySysUserId(String uid) {
        return groupDao.findByProperty("user.id", uid);
    }

    @Transactional("transactionManager")
    public WifiUserGroup getWifiUserGroup(String id) {
        return groupDao.get(id);
    }

    @Transactional("transactionManager")
    public List<WifiUser> getWifiUsers(List<String> ids) {
        return userDao.get(ids);
    }

    @Transactional("transactionManager")
    public void delWifiUsers(List<String> ids) {
        userDao.deleteAll(ids);
    }

    @Transactional("transactionManager")
    public void saveWifiuserGroupRestriction(WifiUserGroup entity) {
        groupDao.save(entity);
    }

}
