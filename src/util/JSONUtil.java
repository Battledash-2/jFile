package main.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.Map;

public class JSONUtil {
    static Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static String stringify(Object object) {
        return gson.toJson(object);
    }
    public static String stringify(JsonObject object) {
        return gson.toJson(object);
    }
    public static Object parse(String string) {
        Object object = gson.fromJson(string, Map.class);
        return object;
    }
    public static Map mapParse(String string) {
        Object o = parse(string);
        return (Map)o;
    }
}
