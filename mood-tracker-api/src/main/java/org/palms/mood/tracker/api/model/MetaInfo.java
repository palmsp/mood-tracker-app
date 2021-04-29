package org.palms.mood.tracker.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@ApiModel(description = "Meta information model")
public class MetaInfo {

    @ApiModelProperty(value = "Status", allowableValues = "OK, ERROR", required = true)
    private MetaStatus status;

    @ApiModelProperty(value = "Code", required = true)
    private Integer code;

    @ApiModelProperty("Message")
    private String message;

    public MetaInfo(MetaStatus status, Integer code) {
        this.status = status;
        this.code = code;
    }
}
