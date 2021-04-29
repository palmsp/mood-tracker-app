package org.palms.mood.tracker.api.model.checkin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.palms.mood.tracker.api.model.MoodFactor;
import org.palms.mood.tracker.api.util.ApiUtil;

import java.util.Date;
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
    private Date date;
    
    @ApiModelProperty(value = "Mood", required = true)
    private Integer moodScore;

    @ApiModelProperty("Name")
    private String name;

    @ApiModelProperty("List of activity ids")
    private List<Long> activityIds;

    @ApiModelProperty("List of feeling ids")
    private List<Long> feelingIds;

    @ApiModelProperty("Mood factors")
    private List<MoodFactor> factors;

    @ApiModelProperty("Notes")
    private String notes;
}
