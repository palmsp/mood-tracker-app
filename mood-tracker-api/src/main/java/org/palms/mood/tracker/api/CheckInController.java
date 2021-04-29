package org.palms.mood.tracker.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.palms.mood.tracker.api.model.Activity;
import org.palms.mood.tracker.api.model.ApiResponse;
import org.palms.mood.tracker.api.model.Feeling;
import org.palms.mood.tracker.api.model.checkin.CheckIn;
import org.palms.mood.tracker.api.model.checkin.CheckInList;
import org.palms.mood.tracker.api.model.checkin.CheckInOptions;
import org.palms.mood.tracker.api.model.checkin.CheckInRequest;
import org.palms.mood.tracker.domain.ActivityEntity;
import org.palms.mood.tracker.domain.CheckInEntity;
import org.palms.mood.tracker.domain.FeelingEntity;
import org.palms.mood.tracker.service.CheckInService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

/**
 * REST controller for check-in.
 *
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@RestController
@RequestMapping("/check-in")
@RequiredArgsConstructor
@Api("Check-in operation controller")
@Slf4j
public class CheckInController {

    private final CheckInService checkInService;

    /**
     * Save check-in.
     *
     * @param request {@link CheckInRequest}
     * @return {@link ApiResponse}
     */
    @PostMapping
    @ApiOperation(value = "Daily check-in", response = ApiResponse.class)
    public ApiResponse checkIn(@ApiParam(value = "Daily check-in info", required = true)
                               @Valid @RequestBody CheckInRequest request) {
        final CheckInEntity entity = new CheckInEntity();
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setScore(request.getMoodScore());
        entity.setNotes(request.getNotes());
        entity.setUserId(LONG_ONE);
        entity.setActivityIds(request.getActivityIds());
        entity.setFeelingIds(request.getFeelingIds());
        checkInService.saveCheckIn(entity);
        return new ApiResponse();
    }

    /**
     * Get check-ins for period.
     *
     * @return {@link CheckInList}
     */
    @GetMapping
    @ApiOperation(value = "Check-ins for period", response = CheckInList.class)
    public CheckInList checkIns() {
        final List<CheckInEntity> checkInEntities = checkInService.findCheckIns(LONG_ONE);
        final List<ActivityEntity> activityEntities = ofNullable(checkInService.findActivities()).orElse(emptyList());
        final List<FeelingEntity> feelingEntities = ofNullable(checkInService.findFeelings()).orElse(emptyList());
        final List<CheckIn> checkIns = ofNullable(checkInEntities).orElse(emptyList()).stream().map(entity -> {
            final CheckIn checkIn = CheckIn.from(entity);
            final List<ActivityEntity> activities = activityEntities.stream().filter(i -> entity.getActivityIds()
                    .contains(i.getId()))
                    .collect(Collectors.toList());
            checkIn.setActivities(activities
                    .stream().map(activityEntity -> Activity.from(activityEntity))
                    .collect(Collectors.toList()));
            final List<FeelingEntity> feelings = feelingEntities.stream().filter(i -> entity.getFeelingIds()
                    .contains(i.getId()))
                    .collect(Collectors.toList());
            checkIn.setFeelings(feelings
                    .stream().map(feelingEntity -> Feeling.from(feelingEntity))
                    .collect(Collectors.toList()));
            return checkIn;
        }).collect(Collectors.toList());
        final CheckInList list = new CheckInList();
        list.setCheckIns(checkIns);
        return list;
    }

    /**
     * Get options for check-in.
     *
     * @return {@link CheckInOptions}
     */
    @GetMapping("/options")
    @ApiOperation(value = "Check-in options", response = CheckInOptions.class)
    public CheckInOptions select() {
        return new CheckInOptions();
    }

    /**
     * Save activity.
     *
     * @param activity {@link Activity}
     * @return {@link ApiResponse}
     */
    @PostMapping("/activity")
    @ApiOperation(value = "Save activity", response = ApiResponse.class)
    public ApiResponse saveActivity(@ApiParam(value = "Activity", required = true)
                                    @Valid @RequestBody Activity activity) {
        final ActivityEntity entity = new ActivityEntity();
        entity.setId(activity.getId());
        entity.setName(activity.getName());
        entity.setIcon(activity.getIcon());
        entity.setPublicInd(activity.isPublicInd());
        checkInService.saveActivity(entity);
        return new ApiResponse();
    }

    /**
     * Save feeling.
     *
     * @param feeling {@link Feeling}
     * @return {@link ApiResponse}
     */
    @PostMapping("/feeling")
    @ApiOperation(value = "Save feeling", response = ApiResponse.class)
    public ApiResponse saveFeeling(@ApiParam(value = "Feeling", required = true)
                                   @Valid @RequestBody Feeling feeling) {
        final FeelingEntity entity = new FeelingEntity();
        entity.setId(feeling.getId());
        entity.setName(feeling.getName());
        entity.setIcon(feeling.getIcon());
        checkInService.saveFeeling(entity);
        return new ApiResponse();
    }
}
