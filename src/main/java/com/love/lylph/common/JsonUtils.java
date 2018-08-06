package com.love.lylph.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.love.lylph.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author PanHuai
 * @data 2018/7/31 10:37
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final JsonUtils json = new JsonUtils();

    public static JsonUtils getJsonUtils() {
        return json ;
    }

    /**
     * 将对象转化为json字符串
     */
    public String toJson(Object o) {
        if (o == null) {
            return null;
        }
        return gson.toJson(o);
    }

    /**
     * 将json转化为对象
     */
    public <T> T fromJson(String json,Class<T> clazz){
        if (json == null) {
            return  null;
        }
        return gson.fromJson(json, clazz);
    }

    /**
     * 将json转化为集合
     */
    public <T> Collection<T> fromJson(String json,Class<? extends Collection> collectionClazz,Class<T> clazz){
        if(json == null){
            return null;
        }
        try {
            Collection<T> collection =  mapper.readValue(json, getCollectionType(collectionClazz,clazz));
            return collection;
        } catch (IOException e) {
            logger.error(String.format("json=[%s]", json), e);
        }
        return null;
    }

    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

   /*     public String jacksonToJson(Object o) {
        if (o == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error(String.format("o=[%s]", o.toString()), e);
        }
        return null;
    }

    public <T> T jacksonfromJson(String json,Class<T> clazz){
        if(json == null){
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error(String.format("json=[%s]", json), e);
        }
        return null;
    }*/

}
