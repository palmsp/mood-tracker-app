package org.palms.mood.tracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.palms.mood.tracker.domain.ActivityEntity;
import org.palms.mood.tracker.domain.CheckInEntity;
import org.palms.mood.tracker.domain.FeelingEntity;
import org.palms.mood.tracker.repository.ActivityRepository;
import org.palms.mood.tracker.repository.CheckInRepository;
import org.palms.mood.tracker.repository.FeelingRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository;
    private final ActivityRepository activityRepository;
    private final FeelingRepository feelingRepository;

    @Override
    public void saveCheckIn(CheckInEntity entity) {
        checkInRepository.save(entity);
    }

    @Override
    public List<CheckInEntity> findCheckIns(Long userId) {
        return checkInRepository.findCheckIns(userId);
    }

    @Override
    public List<CheckInEntity> findCheckIns(Long userId, Date dateFrom, Date dateTo) {
        return checkInRepository.findCheckIns(userId, dateFrom, dateTo);
    }

    @Override
    public List<ActivityEntity> findActivities() {
        return activityRepository.findAll();
    }

    @Override
    public List<FeelingEntity> findFeelings() {
        return feelingRepository.findAll();
    }

    @Override
    public void saveActivity(ActivityEntity entity) {
        activityRepository.save(entity);
    }

    @Override
    public void saveFeeling(FeelingEntity entity) {
        feelingRepository.save(entity);
    }
}
