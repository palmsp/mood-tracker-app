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
@ApiModel("Feeling")
public class Feeling {

    @ApiModelProperty(value = "Id of feeling", required = true)
    private Long id;

    @ApiModelProperty(value = "Name", required = true)
    private String name;

    @ApiModelProperty("Icon url")
    private String icon;

    @ApiModelProperty("Feeling category")
    private String category;
}
