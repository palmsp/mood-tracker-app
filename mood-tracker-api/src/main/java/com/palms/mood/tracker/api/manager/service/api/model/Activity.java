package com.palms.mood.tracker.api.manager.service.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@NoArgsConstructor
@ApiModel("Activity")
public class Activity {

    @ApiModelProperty(value = "Id of activity", required = true)
    private Long id;

    @ApiModelProperty(value = "Name", required = true)
    private String name;

    @ApiModelProperty(value = "Indicator shows is activity is public")
    private boolean publicInd;

    @ApiModelProperty("Icon url")
    private String icon;
}
