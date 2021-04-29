package org.palms.mood.tracker.api.model.checkin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.palms.mood.tracker.api.model.Activity;
import org.palms.mood.tracker.api.model.ApiResponse;
import org.palms.mood.tracker.api.model.Feeling;

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
