package org.palms.mood.tracker.api.model.checkin;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.palms.mood.tracker.api.model.Activity;
import org.palms.mood.tracker.api.model.Feeling;
import org.palms.mood.tracker.api.model.Mood;
import org.palms.mood.tracker.api.model.MoodFactor;
import org.palms.mood.tracker.api.util.ApiUtil;
import org.palms.mood.tracker.domain.CheckInEntity;

import java.util.Date;
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
    private Date date;

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

    public void setMood(Integer score) {
        this.mood = new Mood(score);
    }

    /**
     * @param entity {@link CheckInEntity}
     * @return {@link CheckIn}
     */
    public static CheckIn from(CheckInEntity entity) {
        final CheckIn checkIn = new CheckIn();
        checkIn.setId(entity.getId());
        checkIn.setName(entity.getName());
        checkIn.setDate(entity.getSysCreationDate());
        checkIn.setMood(entity.getScore());
        checkIn.setNotes(entity.getNotes());
        return checkIn;
    }
}
