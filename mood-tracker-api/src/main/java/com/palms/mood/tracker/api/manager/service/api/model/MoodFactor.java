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
@ApiModel("Mood factor")
public class MoodFactor {

    @ApiModelProperty("Factor id")
    private Long id;

    @ApiModelProperty("Name")
    private String name;

    @ApiModelProperty("Value")
    private String value;
}
