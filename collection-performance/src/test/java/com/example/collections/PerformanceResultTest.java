package com.example.collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit тесты для класса PerformanceResult
 */
class PerformanceResultTest {

    @Test
    void testConstructorAndGetters() {
        PerformanceResult result = new PerformanceResult("test", 1000, 5000, 10000);

        assertEquals("test", result.getOperationName());
        assertEquals(1000, result.getOperationCount());
        assertEquals(5000, result.getArrayListTime());
        assertEquals(10000, result.getLinkedListTime());
    }

    @Test
    void testGetFasterList_ArrayListFaster() {
        PerformanceResult result = new PerformanceResult("test", 1000, 1000, 2000);
        assertEquals("ArrayList", result.getFasterList());
    }

    @Test
    void testGetFasterList_LinkedListFaster() {
        PerformanceResult result = new PerformanceResult("test", 1000, 2000, 1000);
        assertEquals("LinkedList", result.getFasterList());
    }

    @Test
    void testGetTimeDifference() {
        PerformanceResult result = new PerformanceResult("test", 1000, 1000, 3000);
        assertEquals(2000, result.getTimeDifference());
    }

    @Test
    void testGetPerformanceRatio() {
        PerformanceResult result = new PerformanceResult("test", 1000, 1000, 3000);
        assertEquals(3.0, result.getPerformanceRatio(), 0.01);
    }

    @Test
    void testGetPerformanceRatio_Reverse() {
        PerformanceResult result = new PerformanceResult("test", 1000, 3000, 1000);
        assertEquals(3.0, result.getPerformanceRatio(), 0.01);
    }

    @Test
    void testToString() {
        PerformanceResult result = new PerformanceResult("test", 1000, 1000, 3000);
        String stringResult = result.toString();

        assertTrue(stringResult.contains("test"));
        assertTrue(stringResult.contains("ArrayList=1000"));
        assertTrue(stringResult.contains("LinkedList=3000"));
        assertTrue(stringResult.contains("Faster=ArrayList"));
    }

    @Test
    void testZeroTimes() {
        PerformanceResult result = new PerformanceResult("test", 1000, 0, 0);
        assertEquals(0, result.getPerformanceRatio());
        assertEquals(0, result.getTimeDifference());
    }
}