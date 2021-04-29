package org.palms.mood.tracker.api.model;

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

    private String defineName(Integer moodScore) {
        String moodName;
        final int terribleInd = 20;
        final int badInd = 49;
        final int okInd = 70;
        final int goodInd = 90;
        if (moodScore <= terribleInd) {
            moodName = MoodRate.TERRIBLE.getValue();
        } else if (moodScore > terribleInd && moodScore <= badInd) {
            moodName = MoodRate.SOMEWHAT_BAD.getValue();
        } else if (moodScore > badInd && moodScore <= okInd) {
            moodName = MoodRate.COMPLETELY_OK.getValue();
        } else if (moodScore > okInd && moodScore <= goodInd) {
            moodName = MoodRate.PRETTY_GOOD.getValue();
        } else {
            moodName = MoodRate.AWESOME.getValue();
        }
        return moodName;
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
        PRETTY_GOOD("PRETTY GOOD"),
        AWESOME("AWESOME");

        private String value;
    }
}
