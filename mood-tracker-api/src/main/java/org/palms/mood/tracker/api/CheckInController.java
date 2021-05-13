package org.palms.mood.tracker.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.palms.mood.tracker.api.model.Activity;
import org.palms.mood.tracker.api.model.ApiResponse;
import org.palms.mood.tracker.api.model.Feeling;
import org.palms.mood.tracker.api.model.checkin.CheckIn;
import org.palms.mood.tracker.api.model.checkin.CheckInList;
import org.palms.mood.tracker.api.model.checkin.CheckInOptions;
import org.palms.mood.tracker.api.model.checkin.CheckInRequest;
import org.palms.mood.tracker.api.util.JwtTokenUtil;
import org.palms.mood.tracker.domain.ActivityEntity;
import org.palms.mood.tracker.domain.CheckInEntity;
import org.palms.mood.tracker.domain.FeelingEntity;
import org.palms.mood.tracker.service.CheckInService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import static org.palms.mood.tracker.api.util.ApiUtil.API_DATE_FORMAT_DATE;
import static org.palms.mood.tracker.api.util.ApiUtil.AUTH;

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
    private final JwtTokenUtil tokenUtil;

    /**
     * Save check-in.
     *
     * @param token token
     * @param request {@link CheckInRequest}
     * @return {@link ApiResponse}
     */
    @PostMapping
    @ApiOperation(value = "Daily check-in", response = ApiResponse.class)
    public ApiResponse checkIn(@ApiParam(value = AUTH, required = true)
                               @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                               @ApiParam(value = "Daily check-in info", required = true)
                               @Valid @RequestBody CheckInRequest request) {
        final Long userId = tokenUtil.getUserIdFromToken(token);
        log.info("CheckInController.checkIn for userId {}", userId);
        final CheckInEntity entity = new CheckInEntity();
        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setScore(request.getMoodScore());
        entity.setCheckInDate(ofNullable(request.getDate()).orElse(new Date()));
        entity.setNotes(request.getNotes());
        entity.setUserId(userId);
        entity.setActivityIds(request.getActivityIds());
        entity.setFeelingIds(request.getFeelingIds());
        //think about factors impl
        checkInService.saveCheckIn(entity);
        return new ApiResponse();
    }

    /**
     * Get check-ins for period.
     *
     * @param token token
     * @param dateFrom date period start
     * @param dateTo date period from
     * @return {@link CheckInList}
     */
    @GetMapping
    @ApiOperation(value = "Check-ins for period", response = CheckInList.class)
    public CheckInList checkIns(@ApiParam(value = AUTH, required = true)
                                @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                @ApiParam(value = "Start of period", required = true)
                                @QueryParam(value = "dateFrom") @DateTimeFormat(pattern = API_DATE_FORMAT_DATE) Date dateFrom,
                                @ApiParam(value = "End of period", required = true)
                                @QueryParam("dateFrom") @DateTimeFormat(pattern = API_DATE_FORMAT_DATE) Date dateTo) {
        final Long userId = tokenUtil.getUserIdFromToken(token);
        log.info("CheckInController.checkIns for userId {} for period {} - {}", userId, dateFrom, dateTo);
        final Date dateStart = DateUtils.truncate(dateFrom, Calendar.DATE);
        final Date dateEnd = DateUtils.addMilliseconds(DateUtils.ceiling(dateTo, Calendar.DATE), -1);
        final List<CheckInEntity> checkInEntities = checkInService.findCheckIns(userId, dateStart, dateEnd);
        //possibly load not all entity, but only from request
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
     * @param token token
     * @return {@link CheckInOptions}
     */
    @GetMapping("/options")
    @ApiOperation(value = "Check-in options", response = CheckInOptions.class)
    public CheckInOptions select(@ApiParam(value = AUTH, required = true)
                                 @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        final Long userId = tokenUtil.getUserIdFromToken(token);
        log.info("CheckInController.select for userId {}", userId);
        final List<ActivityEntity> activityEntities = ofNullable(checkInService.findActivities()).orElse(emptyList());
        final List<FeelingEntity> feelingEntities = ofNullable(checkInService.findFeelings()).orElse(emptyList());
        final List<Activity> activities = activityEntities.stream().filter(activity -> !activity.isPublicInd())
                .map(Activity::from).collect(Collectors.toList());
        final List<Feeling> feelings = feelingEntities.stream().filter(feeling -> !feeling.isPublicInd())
                .map(Feeling::from).collect(Collectors.toList());
        final CheckInOptions options = new CheckInOptions();
        options.setActivities(activities);
        options.setFeelings(feelings);
        return options;
    }

    /**
     * Save activity.
     *
     * @param token token
     * @param activity activity {@link Activity}
     * @return {@link ApiResponse}
     */
    @PostMapping("/activity")
    @ApiOperation(value = "Save activity", response = ApiResponse.class)
    public ApiResponse saveActivity(@ApiParam(value = AUTH, required = true)
                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                    @ApiParam(value = "Activity", required = true)
                                    @Valid @RequestBody Activity activity) {
        log.info("CheckInController.saveActivity with name {}", activity.getName());
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
     * @param token token
     * @param feeling feeling {@link Feeling}
     * @return {@link ApiResponse}
     */
    @PostMapping("/feeling")
    @ApiOperation(value = "Save feeling", response = ApiResponse.class)
    public ApiResponse saveFeeling(@ApiParam(value = AUTH, required = true)
                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                   @ApiParam(value = "Feeling", required = true)
                                   @Valid @RequestBody Feeling feeling) {
        log.info("CheckInController.saveFeeling with name {}", feeling.getName());
        final FeelingEntity entity = new FeelingEntity();
        entity.setId(feeling.getId());
        entity.setName(feeling.getName());
        entity.setIcon(feeling.getIcon());
        checkInService.saveFeeling(entity);
        return new ApiResponse();
    }
}
