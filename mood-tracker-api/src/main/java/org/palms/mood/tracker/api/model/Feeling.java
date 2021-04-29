package org.palms.mood.tracker.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.palms.mood.tracker.domain.FeelingEntity;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
@Data
@NoArgsConstructor
@ApiModel("Feeling")
public class Feeling {

    @ApiModelProperty(value = "Id of feeling", required = true)
    private Long id;

    @ApiModelProperty(value = "Name", required = true)
    private String name;

    @ApiModelProperty("Icon url")
    private String icon;

    @ApiModelProperty("Feeling category")
    private String category;

    /**
     * @param entity {@link FeelingEntity}
     * @return {@link Feeling}
     */
    public static Feeling from(FeelingEntity entity) {
        final Feeling feeling = new Feeling();
        feeling.setId(entity.getId());
        feeling.setName(entity.getName());
        feeling.setIcon(entity.getIcon());
        return feeling;
    }
}
