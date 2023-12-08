package com.example.firstproject.ioc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import javax.xml.bind.annotation.XmlEnum;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChefTest {

    @Autowired
    IngredientFactory ingredientFactory;

    @Autowired
    Chef chef;

    @Test
    @DisplayName("돈까스 요리하기")
    void cook_don(){

        // 준비
        // Chef chef = new Chef(ingredientFactory);
        String menu = "돈까스";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "한돈 등심으로 만든 돈까스";

        // 비교
        assertEquals(expected,food);
        System.out.println(food);
    }

    @Test
    @DisplayName("스테이크 요리하기")
    void cook_sta(){
        // 준비
        // Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "한우 꽃등심으로 만든 스테이크";

        // 비교
        assertEquals(expected,food);
        System.out.println(food);

    }

    @Test
    @DisplayName("크리스피 치킨으로 요리하기")
    void cook_chicken(){
        // 준비
        // Chef chef = new Chef(ingredientFactory);
        String menu = "크리스피 치킨";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";

        // 비교
        assertEquals(expected,food);
        System.out.println(food);
    }


}