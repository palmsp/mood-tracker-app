package org.palms.mood.tracker.repository;

import org.palms.mood.tracker.domain.CheckInEntity;

import java.util.Date;
import java.util.List;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
public interface CheckInRepositoryCustom {

    /**
     * Find check-ins for period.
     *
     * @param userId user id
     * @param dateFrom date from
     * @param dateTo date to
     * @return list
     */
    List<CheckInEntity> findCheckIns(Long userId, Date dateFrom, Date dateTo);

    /**
     * Find check-ins for period.
     *
     * @param userId user id
     * @return list
     */
    List<CheckInEntity> findCheckIns(Long userId);
}
