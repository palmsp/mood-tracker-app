package org.palms.mood.tracker.repository;

import org.palms.mood.tracker.domain.CheckInEntity;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
public class CheckInRepositoryImpl implements CheckInRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CheckInEntity> findCheckIns(Long userId, Date dateFrom, Date dateTo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CheckInEntity> cq = cb.createQuery(CheckInEntity.class);
        Root<CheckInEntity> root = cq.from(CheckInEntity.class);
        Predicate userIdPredicate = cb.equal(root.get("userId"), userId);
        Predicate dateFromPredicate = cb.greaterThanOrEqualTo(root.<Date>get("checkInDate"), dateFrom);
        Predicate dateToPredicate = cb.lessThanOrEqualTo(root.<Date>get("checkInDate"), dateTo);
        cq.where(userIdPredicate, dateFromPredicate, dateToPredicate);
        TypedQuery<CheckInEntity> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<CheckInEntity> findCheckIns(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<CheckInEntity> cq = cb.createQuery(CheckInEntity.class);
        Root<CheckInEntity> root = cq.from(CheckInEntity.class);
        Predicate userIdPredicate = cb.equal(root.get("userId"), userId);
        cq.where(userIdPredicate);
        TypedQuery<CheckInEntity> query = em.createQuery(cq);
        return query.getResultList();
    }

}
