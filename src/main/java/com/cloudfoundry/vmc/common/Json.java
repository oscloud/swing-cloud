package com.cloudfoundry.vmc.common;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class Json {
    private static final Logger LOG = Logger.getLogger(Json.class);
    
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static String writeAsString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            LOG.error("write json error", e);
        }
        return "";
    }
    
    public static <T> T readAsObject(final String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            LOG.error("read json error", e);
        }
        return null;
    }
    
    public static String formatObjectAsString(final Object object) {
         return formatAsString(writeAsString(object));
    }

    public static String formatAsString(final String json, String... symbol) {
        if (json == null || json.trim().length() == 0) {
            return "";
        }
        String fillStringUnit = "   ";
        if (null != symbol && symbol.length > 0) {
            fillStringUnit = symbol[0];
        }

        int fixedLenth = 0;
        ArrayList<String> tokenList = new ArrayList<String>();
        {
            String jsonTemp = json;
            // 预读取
            while (jsonTemp.length() > 0) {
                String token = getToken(jsonTemp);
                jsonTemp = jsonTemp.substring(token.length());
                token = token.trim();
                tokenList.add(token);
            }
        }

        for (int i = 0; i < tokenList.size(); i++) {
            String token = tokenList.get(i);
            int length = token.getBytes().length;
            if (length > fixedLenth && i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) {
                fixedLenth = length;
            }
        }

        StringBuilder buf = new StringBuilder();
        int count = 0;
        for (int i = 0; i < tokenList.size(); i++) {

            String token = tokenList.get(i);

            if (token.equals(",")) {
                buf.append(token);
                doFill(buf, count, fillStringUnit);
                continue;
            }
            if (token.equals(":")) {
                buf.append(" ").append(token).append(" ");
                continue;
            }
            if (token.equals("{")) {
                String nextToken = tokenList.get(i + 1);
                if (nextToken.equals("}")) {
                    i++;
                    buf.append("{ }");
                } else {
                    count++;
                    buf.append(token);
                    doFill(buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("}")) {
                count--;
                doFill(buf, count, fillStringUnit);
                buf.append(token);
                continue;
            }
            if (token.equals("[")) {
                String nextToken = tokenList.get(i + 1);
                if (nextToken.equals("]")) {
                    i++;
                    buf.append("[ ]");
                } else {
                    count++;
                    buf.append(token);
                    doFill(buf, count, fillStringUnit);
                }
                continue;
            }
            if (token.equals("]")) {
                count--;
                doFill(buf, count, fillStringUnit);
                buf.append(token);
                continue;
            }

            buf.append(token);
//            // 左对齐
//            if (i < tokenList.size() - 1 && tokenList.get(i + 1).equals(":")) {
//                int fillLength = fixedLenth - token.getBytes().length;
//                if (fillLength > 0) {
//                    for (int j = 0; j < fillLength; j++) {
//                        buf.append(" ");
//                    }
//                }
//            }
        }
        return buf.toString();
    }

    private static String getToken(String json) {
        StringBuilder buf = new StringBuilder();
        boolean isInYinHao = false;
        while (json.length() > 0) {
            String token = json.substring(0, 1);
            json = json.substring(1);

            if (!isInYinHao
                    && (token.equals(":") || token.equals("{") || token.equals("}") || token.equals("[")
                            || token.equals("]") || token.equals(","))) {
                if (buf.toString().trim().length() == 0) {
                    buf.append(token);
                }
                break;
            }

            if (token.equals("\\")) {
                buf.append(token);
                buf.append(json.substring(0, 1));
                json = json.substring(1);
                continue;
            }
            if (token.equals("\"")) {
                buf.append(token);
                if (isInYinHao) {
                    break;
                } else {
                    isInYinHao = true;
                    continue;
                }
            }
            buf.append(token);
        }
        return buf.toString();
    }

    private static void doFill(StringBuilder buf, int count, String fillStringUnit) {
        buf.append("\n");
        for (int i = 0; i < count; i++) {
            buf.append(fillStringUnit);
        }
    }
    
    public static void main(String[] args) {
        String json = "{\"key-value\":{\"redis\":{\"2.2\":{\"id\":2,\"vendor\":\"redis\",\"version\":\"2.2\",\"tiers\":{\"free\":{\"options\":{},\"order\":1}},\"type\":\"key-value\",\"description\":\"Redis key-value store service\",\"provider\":null,\"default_plan\":\"free\",\"alias\":\"current\"}}},\"generic\":{\"rabbitmq\":{\"2.4\":{\"id\":7,\"vendor\":\"rabbitmq\",\"version\":\"2.4\",\"tiers\":{\"free\":{\"options\":{},\"order\":1}},\"type\":\"generic\",\"description\":\"RabbitMQ message queue\",\"provider\":null,\"default_plan\":\"free\",\"alias\":\"current\"}}},\"database\":{\"mysql\":{\"5.1\":{\"id\":1,\"vendor\":\"mysql\",\"version\":\"5.1\",\"tiers\":{\"free\":{\"options\":{},\"order\":1}},\"type\":\"database\",\"description\":\"MySQL database service\",\"provider\":null,\"default_plan\":\"free\",\"alias\":\"current\"}},\"postgresql\":{\"9.0\":{\"id\":6,\"vendor\":\"postgresql\",\"version\":\"9.0\",\"tiers\":{\"free\":{\"options\":{},\"order\":1}},\"type\":\"database\",\"description\":\"PostgreSQL database service (vFabric)\",\"provider\":null,\"default_plan\":\"free\",\"alias\":\"current\"}}},\"document\":{\"mongodb\":{\"2.0\":{\"id\":3,\"vendor\":\"mongodb\",\"version\":\"2.0\",\"tiers\":{\"free\":{\"options\":{},\"order\":1}},\"type\":\"document\",\"description\":\"MongoDB NoSQL store\",\"provider\":null,\"default_plan\":\"free\",\"alias\":\"current\"}}}}";
        System.out.println(Json.formatAsString(json));
    }
}
