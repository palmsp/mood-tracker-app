package com.palms.mood.tracker.api.manager.service.api.model.checkin;

import com.palms.mood.tracker.api.manager.service.api.model.ApiResponse;
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
@ApiModel("List of check-ins for period")
public class CheckInList extends ApiResponse {

    @ApiModelProperty("Check-in list")
    private List<CheckIn> checkIns;
}
