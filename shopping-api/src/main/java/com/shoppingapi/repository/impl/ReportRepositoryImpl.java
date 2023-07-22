package com.shoppingapi.repository.impl;

import com.shoppingapi.dto.ShopReportDTO;
import com.shoppingapi.model.Shop;
import com.shoppingapi.repository.ReportRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(Date initialDate, Date finalDate, Float minValue) {
        StringBuilder sb = new StringBuilder();
        sb.append("Select s ");
        sb.append("From Shop s ");
        sb.append("Where s.date >= :initialDate ");

        if (Objects.nonNull(finalDate)) {
            sb.append("and s.date <= :finalDate");
        }

        if (Objects.nonNull(minValue)) {
            sb.append("and s.total >= :minValue");
        }

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("initialDate", initialDate);


        if (Objects.nonNull(finalDate)) {
            query.setParameter("finalDate", finalDate);
        }

        if (Objects.nonNull(minValue)) {
            query.setParameter("minValue", minValue);
        }

        return query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(Date initialDate, Date finalDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("Select count(sp.id), sum(sp.total), avg(sp.total) ");
        sb.append("from shopping.shop sp ");
        sb.append("where sp.date >= :initialDate ");
        sb.append("and sp.date <= :finalDate ");

        Query query = entityManager.createQuery(sb.toString());
        query.setParameter("initialDate", initialDate);
        query.setParameter("finalDate", finalDate);

        Object[] result = (Object[]) query.getSingleResult();
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount(((BigInteger) result[0]).intValue());
        shopReportDTO.setTotal((Double) result[1]);
        shopReportDTO.setMean((Double) result[2]);

        return shopReportDTO;
    }
}
