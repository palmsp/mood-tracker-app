package com.palms.mood.tracker.api.manager.service.api.model.checkin;

import com.palms.mood.tracker.api.manager.service.api.model.Activity;
import com.palms.mood.tracker.api.manager.service.api.model.ApiResponse;
import com.palms.mood.tracker.api.manager.service.api.model.Feeling;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@NoArgsConstructor
@ApiModel("Check-in options")
public class CheckInOptions extends ApiResponse {

    @ApiModelProperty("List of activities to select")
    private List<Activity> activities;

    @ApiModelProperty("List of feelings to select")
    private List<Feeling> feelings;
}
