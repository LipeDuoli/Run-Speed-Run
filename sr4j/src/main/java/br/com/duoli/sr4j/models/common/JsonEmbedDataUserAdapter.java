package br.com.duoli.sr4j.models.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.List;

public class JsonEmbedDataUserAdapter<T> implements JsonDeserializer<List<T>> {

    @Override
    public List<T> deserialize(JsonElement json, Type typeOfT,
                               JsonDeserializationContext jsc)
            throws JsonParseException {

        List<T> result;

        if (json.isJsonObject()) {
            JsonObject envelope = json.getAsJsonObject();
            JsonArray data = envelope.getAsJsonArray("data");
            result = jsc.deserialize(data, typeOfT);
        } else {
            result = jsc.deserialize(json, typeOfT);
        }
        return result;
    }
}
