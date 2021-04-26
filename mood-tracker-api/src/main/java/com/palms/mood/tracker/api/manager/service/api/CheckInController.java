package com.palms.mood.tracker.api.manager.service.api;

import com.palms.mood.tracker.api.manager.service.api.model.ApiResponse;
import com.palms.mood.tracker.api.manager.service.api.model.checkin.CheckIn;
import com.palms.mood.tracker.api.manager.service.api.model.checkin.CheckInList;
import com.palms.mood.tracker.api.manager.service.api.model.checkin.CheckInOptions;
import com.palms.mood.tracker.api.manager.service.api.model.checkin.CheckInRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
        final CheckIn checkIn = new CheckIn();
        final CheckInList list = new CheckInList();
        final List<CheckIn> checkIns = new ArrayList<>();
        checkIns.add(checkIn);
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
}
