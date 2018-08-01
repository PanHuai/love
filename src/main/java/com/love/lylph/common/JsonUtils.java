package com.love.lylph.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author PanHuai
 * @data 2018/7/31 10:37
 */
public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转化为json字符串
     */
    public static String tojson() {

        return null;
    }





}
