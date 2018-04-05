package br.com.duoli.sr4j.models.common;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class JsonEmbedDataAdapter<T> implements JsonDeserializer<T> {

    @Override
    public T deserialize(JsonElement json, Type typeOfT,
                         JsonDeserializationContext jsc)
            throws JsonParseException {

        T result;

        if (json.isJsonObject()) {
            JsonObject envelope = json.getAsJsonObject();
            JsonObject data = envelope.getAsJsonObject("data");
            result = jsc.deserialize(data, typeOfT);
        } else {
            result = jsc.deserialize(json, typeOfT);
        }

        return result;
    }
}
