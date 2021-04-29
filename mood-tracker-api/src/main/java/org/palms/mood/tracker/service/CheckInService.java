package org.palms.mood.tracker.service;

import org.palms.mood.tracker.domain.ActivityEntity;
import org.palms.mood.tracker.domain.CheckInEntity;
import org.palms.mood.tracker.domain.FeelingEntity;

import java.util.List;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
public interface CheckInService {

    /**
     * Save check-in.
     *
     * @param entity {@link CheckInEntity}
     */
    void saveCheckIn(CheckInEntity entity);

    /**
     * Find all check-ins for user.
     *
     * @param userId user id
     * @return list
     */
    List<CheckInEntity> findCheckIns(Long userId);

    /**
     * Find all activities.
     *
     * @return list
     */
    List<ActivityEntity> findActivities();

    /**
     * Find all feelings.
     *
     * @return list
     */
    List<FeelingEntity> findFeelings();

    /**
     * Save activity.
     *
     * @param entity {@link ActivityEntity}
     */
    void saveActivity(ActivityEntity entity);

    /**
     * Save feeling.
     *
     * @param entity {@link FeelingEntity}
     */
    void saveFeeling(FeelingEntity entity);
}
