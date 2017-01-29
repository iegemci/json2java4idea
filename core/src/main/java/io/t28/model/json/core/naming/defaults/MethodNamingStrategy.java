package io.t28.model.json.core.naming.defaults;

import com.squareup.javapoet.TypeName;
import io.t28.model.json.core.naming.NamingCase;
import io.t28.model.json.core.naming.NamingStrategy;

import javax.annotation.Nonnull;

public class MethodNamingStrategy implements NamingStrategy {
    private static final String BOOLEAN_PREFIX = "is";
    private static final String GENERAL_PREFIX = "get";

    @Nonnull
    @Override
    public String transform(@Nonnull TypeName type, @Nonnull String name, @Nonnull NamingCase nameCase) {
        final StringBuilder builder = new StringBuilder();
        if (type.equals(TypeName.BOOLEAN)) {
            return builder.append(BOOLEAN_PREFIX)
                    .append(nameCase.to(NamingCase.UPPER_CAMEL_CASE, name))
                    .toString();
        }
        return builder.append(GENERAL_PREFIX)
                .append(nameCase.to(NamingCase.UPPER_CAMEL_CASE, name))
                .toString();
    }
}