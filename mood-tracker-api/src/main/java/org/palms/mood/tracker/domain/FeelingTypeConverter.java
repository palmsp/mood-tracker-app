package org.palms.mood.tracker.domain;

import javax.persistence.AttributeConverter;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import static java.util.Objects.nonNull;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
public class FeelingTypeConverter implements AttributeConverter<FeelingType, String> {

    @Override
    public String convertToDatabaseColumn(FeelingType feelingType) {
        return nonNull(feelingType) ? feelingType.toString() : null;
    }

    @Override
    public FeelingType convertToEntityAttribute(String s) {
        return isNotEmpty(s) ? FeelingType.valueOf(s) : null;
    }
}
