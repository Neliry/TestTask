package com.example.testtask.mapper.qualifier;

import lombok.experimental.UtilityClass;
import org.mapstruct.Named;

import java.util.Collection;

@UtilityClass
public class MapperQualifier {
    @Named(QualifierNames.TO_LOWER_CASE)
    public static String toLowerCase(String source) {
        return source != null ? source.strip().toLowerCase() : null;
    }

    @Named(QualifierNames.STRIP)
    public static String strip(String source) {
        return source != null ? source.strip() : null;
    }

    @Named(QualifierNames.SIZE)
    public static int size(Collection source) {
        return source != null ? source.size() : 0;
    }
}
