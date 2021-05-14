package org.palms.mood.tracker.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("JWT response")
public class JwtResponse extends ApiResponse {

    @ApiModelProperty("token")
    private String token;
}
