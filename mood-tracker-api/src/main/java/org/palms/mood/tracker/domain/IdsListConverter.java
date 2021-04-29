package org.palms.mood.tracker.domain;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;

import static org.hibernate.internal.util.StringHelper.isNotEmpty;

import static java.util.Collections.emptyList;

/**
 * @author Maria Pal {@literal <pal.maria.msp@gmail.com>}
 */
public class IdsListConverter implements AttributeConverter<List<Long>, String> {

    @Override
    public String convertToDatabaseColumn(List<Long> ids) {
        return CollectionUtils.isEmpty(ids) ? "" : StringUtils.join(ids, ",");
    }

    @Override
    public List<Long> convertToEntityAttribute(String s) {
        if (isNotEmpty(s)) {
            final List<String> stringIds = Lists.newArrayList(s.split(","));
            return stringIds.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
        } else {
            return emptyList();
        }
    }
}
