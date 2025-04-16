package com.anytech.anytxn.biz.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;

/**
 * @Author: sukang
 * @Date: 2021/4/20 15:07
 */
@Slf4j
public class JacksonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    static {

        JavaTimeModule javaTimeModule = new JavaTimeModule();

        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone(OffsetDateTime.now().getOffset()));

        /* 序列化配置,针对java8 时间 */
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        javaTimeModule.addSerializer(LocalDate.class, new com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(
                DateTimeFormatter.ofPattern("HH:mm:ss")));

        /* 反序列化配置,针对java8 时间 */
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(
                DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(
                DateTimeFormatter.ofPattern("HH:mm:ss")));

        OBJECT_MAPPER.registerModule(javaTimeModule);

        //反序列化 忽略对象中不存在的属性
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

    }


    public static Boolean isJson(Object o){
        if (Objects.isNull(o) || StringUtils.isBlank(String.valueOf(o))){
            return false;
        }

        //异常会返回null, true 是json
        try {
            String orElse = Optional.ofNullable(toJsonStr(o)).orElse("");
            return orElse.startsWith("{") || orElse.startsWith("[{");
        } catch (Exception e) {
            //ignore
        }
        return false;
    }



    public static String toJsonStr(Object object){
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        }catch (Exception e){
            log.error("object to json error",e);
        }
        return null;
    }

    public static <T> T readValue(String json, Class<T> clazz){
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        }catch (Exception e){
            log.error("json to object error",e);
        }
        return null;
    }

    public static <T> T readValue(String json, TypeReference<T> tTypeReference){
        try {
            return OBJECT_MAPPER.readValue(json, tTypeReference);
        }catch (Exception e){
            log.error("json to object error",e);
        }
        return null;
    }


    public static JsonNode readTree(String json){
        try {
            return OBJECT_MAPPER.readTree(json);
        }catch (Exception e){
            log.error("json to object error",e);
        }
        return null;
    }


    public static ObjectNode objectNode(){
        return OBJECT_MAPPER.createObjectNode();
    }

    public static ArrayNode arrayNode(){
        return OBJECT_MAPPER.createArrayNode();
    }




}
