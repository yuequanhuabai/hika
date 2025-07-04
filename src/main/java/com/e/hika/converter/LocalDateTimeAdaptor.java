//package com.e.hika.converter;
//
//import com.google.gson.*;
//
//import java.lang.reflect.Type;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class LocalDateTimeAdaptor implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
//
//    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
//
//    @Override
//    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//        return LocalDateTime.parse(jsonElement.getAsString(), FORMATTER);
//    }
//
//    @Override
//    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
//        return new JsonPrimitive(FORMATTER.format(localDateTime));
//    }
//}
