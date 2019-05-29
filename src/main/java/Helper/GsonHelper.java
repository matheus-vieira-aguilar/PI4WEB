package Helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GsonHelper {

    private GsonHelper() {
    }

    public static Gson getGson() {
        GsonHelper gsonHelper = new GsonHelper();
        Gson gson = gsonHelper.registerTypeGson();

        return gson;
    }

    public Gson registerTypeGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer())
                .create();
        return gson;
    }

    class LocalDateDeserializer implements JsonDeserializer< LocalDate> {

        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH));
        }
    }
    
    class LocalDateSerializer implements JsonSerializer <LocalDate>{

        @Override
        public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsc) {
            return new JsonPrimitive(localDate.toString());
        }


    }
}
