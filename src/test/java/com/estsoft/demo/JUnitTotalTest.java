package com.estsoft.demo;

import org.junit.jupiter.api.*;

public class JUnitTotalTest {
    @BeforeAll
    public static void beforeAll() {    // 한번만 호출 static 선언
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("@BeforeEach");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("@AfterEach");
    }

    @Test
    public void test() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }

    @Test
    public void test4() {
        System.out.println("test4");
    }

    @AfterAll
    public static void afterAll() {     // 한번만 호출 static 선언
        System.out.println("@AfterAll");
    }
}
