package com.palms.mood.tracker.api.manager.service.api.model.checkin;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.palms.mood.tracker.api.manager.service.api.model.Mood;
import com.palms.mood.tracker.api.manager.service.api.model.MoodFactor;
import com.palms.mood.tracker.api.manager.service.api.util.ApiUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@NoArgsConstructor
@ApiModel("Check-in to save")
public class CheckInRequest {

    @ApiModelProperty("Id")
    private Long id;

    @ApiModelProperty(value = "Date of check-in", required = true)
    @JsonFormat(shape = STRING, pattern = ApiUtil.API_DATE_FORMAT, timezone = ApiUtil.API_DATE_TIMEZONE)
    private LocalDate date;
    
    @ApiModelProperty(value = "Mood", required = true)
    private Mood mood;

    @ApiModelProperty("Name")
    private String name;

    @ApiModelProperty("List of activity ids")
    private List<Long> activityIds;

    @ApiModelProperty("List of feelling ids")
    private List<Long> feelingIds;

    @ApiModelProperty("Mood factors")
    private List<MoodFactor> factors;

    @ApiModelProperty("Notes")
    private String notes;
}
