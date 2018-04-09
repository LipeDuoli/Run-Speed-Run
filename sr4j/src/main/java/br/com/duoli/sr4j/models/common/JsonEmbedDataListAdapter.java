package br.com.duoli.sr4j.models.common;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonEmbedDataListAdapter<T> implements JsonDeserializer<List<T>> {

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
            JsonArray idArray = json.getAsJsonArray();
            result = new ArrayList<T>(idArray.size());
            for (JsonElement id : idArray) {
                if (typeOfT instanceof ParameterizedType) {
                    Type parameterType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
                    JsonObject obj = new JsonObject();
                    obj.add("id", id);
                    T element = jsc.deserialize(obj, parameterType);
                    result.add(element);
                }
            }
        }
        return result;
    }
}
