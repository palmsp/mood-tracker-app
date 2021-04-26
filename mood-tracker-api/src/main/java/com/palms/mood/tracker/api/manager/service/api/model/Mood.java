package com.palms.mood.tracker.api.manager.service.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@NoArgsConstructor
@ApiModel("Mood")
public class Mood {

    @ApiModelProperty("Score")
    private Integer score;

    @ApiModelProperty("Name")
    private String name;

    public Mood(Integer score) {
        this.score = score;
        this.name = defineName(score);
    }

    private String defineName(Integer score) {
        String name;
        if (score <= 20) {
            name = MoodRate.TERRIBLE.getValue();
        } else if (score > 20 && score <= 49) {
            name = MoodRate.SOMEWHAT_BAD.getValue();
        } else if (score > 49 && score <= 70) {
            name = MoodRate.COMPLETELY_OK.getValue();
        } else if (score > 70 && score <= 90) {
            name = MoodRate.PRETTY_GOOD.getValue();
        } else {
            name = MoodRate.AWESOME.getValue();
        }
        return name;
    }

    /**
     * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
     */
    @Getter
    @AllArgsConstructor
    protected enum MoodRate {
        TERRIBLE("TERRIBLE"),
        SOMEWHAT_BAD("SOMEWHAT BAD"),
        COMPLETELY_OK("COMPLETELY OKAY"),
        PRETTY_GOOD("PRETTY_GOOD"),
        AWESOME("AWESOME");

        private String value;
    }
}
