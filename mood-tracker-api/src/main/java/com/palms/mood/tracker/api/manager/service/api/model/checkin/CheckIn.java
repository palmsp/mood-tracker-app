package com.palms.mood.tracker.api.manager.service.api.model.checkin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.palms.mood.tracker.api.manager.service.api.model.Activity;
import com.palms.mood.tracker.api.manager.service.api.model.Feeling;
import com.palms.mood.tracker.api.manager.service.api.model.Mood;
import com.palms.mood.tracker.api.manager.service.api.model.MoodFactor;
import com.palms.mood.tracker.api.manager.service.api.util.ApiUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@ApiModel("Check-in information")
public class CheckIn {

    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty(value = "Date of check-in", required = true)
    @JsonFormat(shape = STRING, pattern = ApiUtil.API_DATE_FORMAT, timezone = ApiUtil.API_DATE_TIMEZONE)
    private LocalDate date;

    @ApiModelProperty("Name")
    private String name;

    @ApiModelProperty("Mood")
    private Mood mood;

    @ApiModelProperty("List of activities")
    private List<Activity> activities;

    @ApiModelProperty("List of feelings")
    private List<Feeling> feelings;

    @ApiModelProperty("List of factors")
    private List<MoodFactor> factors;
    
    @ApiModelProperty("Notes")
    private String notes;
}
