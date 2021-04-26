package com.palms.mood.tracker.api.manager.service.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Api response model")
public class ApiResponse {

    @ApiModelProperty(required = true)
    private MetaInfo meta = new MetaInfo(MetaStatus.OK, HttpStatus.OK.value());
}
