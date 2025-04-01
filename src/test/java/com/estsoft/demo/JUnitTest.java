package com.estsoft.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JUnitTest {
    @Test
    public void addTest() {
        int a = 1;
        int b = 2;
        int result = 4;

        assertEquals(result, a+ b);
    }
}
