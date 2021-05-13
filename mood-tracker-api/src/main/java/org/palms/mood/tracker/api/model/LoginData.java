package org.palms.mood.tracker.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@ApiModel("Login data")
public class LoginData {

    @ApiModelProperty(value = "Login", required = true)
    private String login;

    @ApiModelProperty(value = "Password", required = true)
    private String password;
}
