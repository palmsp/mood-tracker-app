package org.palms.mood.tracker.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.palms.mood.tracker.domain.ActivityEntity;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@NoArgsConstructor
@ApiModel("Activity")
public class Activity {

    @ApiModelProperty(value = "Id of activity", required = true)
    private Long id;

    @ApiModelProperty(value = "Name", required = true)
    private String name;

    @ApiModelProperty(value = "Indicator shows is activity is public")
    private boolean publicInd;

    @ApiModelProperty("Icon url")
    private String icon;

    /**
     * @param entity {@link ActivityEntity}
     * @return {@link Activity}
     */
    public static Activity from(ActivityEntity entity) {
        final Activity activity = new Activity();
        activity.setId(entity.getId());
        activity.setName(entity.getName());
        activity.setPublicInd(entity.isPublicInd());
        activity.setIcon(entity.getIcon());
        return activity;
    }
}
