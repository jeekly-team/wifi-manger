/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jyzn.wifi.service.shop;

import com.github.dactiv.orm.core.PropertyFilter;
import com.github.dactiv.orm.core.spring.data.jpa.JpaRestrictionBuilder;
import com.github.dactiv.orm.core.spring.data.jpa.specification.SpecificationEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Administrator
 */
public class ValidateLogSpecifications implements Serializable {

    public static Specification toPredicate(final List<PropertyFilter> filters) {

        return new Specification() {

            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder builder) {

                List<Predicate> list = new ArrayList<>();
                for (PropertyFilter filter : filters) {
                    list.add(JpaRestrictionBuilder.getRestriction(filter, new SpecificationEntity(root, query, builder)));
                }

                return list.size() > 0 ? builder.and(list.toArray(new Predicate[list.size()])) : null;

            }
        };
    }
}
