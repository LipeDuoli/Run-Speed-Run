package br.com.duoli.sr4j.models.common;

import com.google.gson.JsonArray;
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
            if (isDataArray(envelope)){
                return null;
            }
            JsonObject data = envelope.getAsJsonObject("data");
            result = jsc.deserialize(data, typeOfT);
        } else {
            String id = json.getAsString();

            JsonObject obj = new JsonObject();
            obj.addProperty("id", id);

            result = jsc.deserialize(obj, typeOfT);
        }
        return result;
    }

    private boolean isDataArray(JsonObject envelope) {
        try {
            JsonArray data = envelope.getAsJsonArray("data");
            return data.isJsonArray();
        } catch (ClassCastException e) {
            return false;
        }
    }
}
