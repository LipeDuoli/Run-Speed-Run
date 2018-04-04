package br.com.duoli.sr4j.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import br.com.duoli.sr4j.models.common.ErrorMessage;
import retrofit2.Response;

public class ErrorUtil {

    public static ErrorMessage parseError(Response<?> response){
        ErrorMessage message = new ErrorMessage();
        try {
            String json = response.errorBody().string();
            Gson gson = new GsonBuilder().create();
            message = gson.fromJson(json, ErrorMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return message;
    }
}
