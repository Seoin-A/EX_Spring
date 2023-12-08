package com.example.firstproject.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    @Test
    @DisplayName("자바 객체를 JSON으로 변환")
    public void javaObjectJson() throws JsonProcessingException {

        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패치","순쇠고시 패티","토마토", "스파이시 어니언 소스");
        Burger burger = new Burger("맥도날드", 5500,ingredients);

        // 수행
        String json = objectMapper.writeValueAsString(burger);

        // 예상
        String expected = "{\"name\":\"맥도날드\",\"price\":5500,\"ingredients\":[\"통새우 패치\",\"순쇠고시 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";

        // 검증
        assertEquals(expected,json);
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    @DisplayName("Json객체를 자바 객체로 변환")
    public void jsonObjectjava() throws JsonProcessingException {
        // 준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("통새우 패티","순쇠고기 패티","토마토", "스파이시 어니언 소스");
        // String json = "{\"name\":\"맥도날드\",\"price\":5500,\"ingredients\":[\"통새우 패치\",\"순쇠고시 패티\",\"토마토\",\"스파이시 어니언 소스\"]}";
        /*
        {
            "name" : "맥도날드 슈비버거",
            "price": 5500,
            "ingredients" : ["통새우 패티","순쇠고시 패티","토마토", "스파이시 어니언 소스"]
        }
        */
        ObjectNode objectNode =objectMapper.createObjectNode();
        objectNode.put("name", "맥도날드");
        objectNode.put("price",5500);

        ArrayNode arrayNode = objectMapper.createArrayNode();
        arrayNode.add("통새우 패티");
        arrayNode.add("순쇠고기 패티");
        arrayNode.add("토마토");
        arrayNode.add("스파이시 어니언 소스");

        objectNode.set("ingredients",arrayNode);

        String json= objectNode.toString();




        // 수행
        Burger burger = objectMapper.readValue(json,Burger.class);

        // 예상

        Burger expected = new Burger("맥도날드", 5500,ingredients);

        // 검증
        assertEquals(expected,burger);

        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }

}