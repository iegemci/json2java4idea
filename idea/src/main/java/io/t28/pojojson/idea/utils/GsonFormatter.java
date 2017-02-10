package io.t28.pojojson.idea.utils;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class GsonFormatter implements JsonFormatter {
    private static final String EMPTY_JSON = "";

    private final Gson gson;
    private final JsonParser parser;

    public GsonFormatter() {
        this(new GsonBuilder().setPrettyPrinting().serializeNulls().create(), new JsonParser());
    }

    @VisibleForTesting
    GsonFormatter(@NotNull Gson gson, @NotNull JsonParser parser) {
        this.gson = gson;
        this.parser = parser;
    }

    @NotNull
    @Override
    public String format(@NotNull String json) throws IOException {
        final String trimmed = json.trim();
        if (Strings.isNullOrEmpty(trimmed)) {
            return EMPTY_JSON;
        }

        try {
            final JsonElement tree = parser.parse(trimmed);
            if (tree.isJsonNull() || tree.isJsonPrimitive()) {
                return trimmed;
            }
            return gson.toJson(tree);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("JSON is invalid syntax", e);
        }
    }
}