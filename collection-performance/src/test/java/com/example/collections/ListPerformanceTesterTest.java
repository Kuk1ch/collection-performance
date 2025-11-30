package com.example.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit тесты для класса ListPerformanceTester
 */
class ListPerformanceTesterTest {

    private ListPerformanceTester tester;
    private static final int SMALL_OPERATION_COUNT = 100;

    @BeforeEach
    void setUp() {
        tester = new ListPerformanceTester(SMALL_OPERATION_COUNT);
    }

    @Test
    void testConstructor() {
        assertEquals(SMALL_OPERATION_COUNT, tester.getOperationCount());
    }

    @Test
    void testAddToEnd() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testAddToEnd(arrayList);
        long linkedListTime = tester.testAddToEnd(linkedList);

        assertEquals(SMALL_OPERATION_COUNT, arrayList.size());
        assertEquals(SMALL_OPERATION_COUNT, linkedList.size());
        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testAddToBeginning() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testAddToBeginning(arrayList);
        long linkedListTime = tester.testAddToBeginning(linkedList);

        assertEquals(SMALL_OPERATION_COUNT, arrayList.size());
        assertEquals(SMALL_OPERATION_COUNT, linkedList.size());
        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testAddToRandomPosition() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testAddToRandomPosition(arrayList);
        long linkedListTime = tester.testAddToRandomPosition(linkedList);

        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testGetByIndex() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testGetByIndex(arrayList);
        long linkedListTime = tester.testGetByIndex(linkedList);

        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testGetByRandomIndex() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testGetByRandomIndex(arrayList);
        long linkedListTime = tester.testGetByRandomIndex(linkedList);

        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testRemoveFromBeginning() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testRemoveFromBeginning(arrayList);
        long linkedListTime = tester.testRemoveFromBeginning(linkedList);

        assertEquals(0, arrayList.size());
        assertEquals(0, linkedList.size());
        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testRemoveFromEnd() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testRemoveFromEnd(arrayList);
        long linkedListTime = tester.testRemoveFromEnd(linkedList);

        assertEquals(0, arrayList.size());
        assertEquals(0, linkedList.size());
        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testRemoveFromRandomPosition() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testRemoveFromRandomPosition(arrayList);
        long linkedListTime = tester.testRemoveFromRandomPosition(linkedList);

        assertEquals(0, arrayList.size());
        assertEquals(0, linkedList.size());
        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testSearchElement() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testSearchElement(arrayList);
        long linkedListTime = tester.testSearchElement(linkedList);

        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testIteration() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        long arrayListTime = tester.testIteration(arrayList);
        long linkedListTime = tester.testIteration(linkedList);

        assertTrue(arrayListTime >= 0);
        assertTrue(linkedListTime >= 0);
    }

    @Test
    void testRunAllTests() {
        PerformanceResult[] results = tester.runAllTests();

        assertNotNull(results);
        assertTrue(results.length > 0);

        for (PerformanceResult result : results) {
            assertNotNull(result.getOperationName());
            assertTrue(result.getArrayListTime() >= 0);
            assertTrue(result.getLinkedListTime() >= 0);
            assertTrue(result.getOperationCount() == SMALL_OPERATION_COUNT);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 500, 1000})
    void testDifferentOperationCounts(int operationCount) {
        ListPerformanceTester paramTester = new ListPerformanceTester(operationCount);
        PerformanceResult[] results = paramTester.runAllTests();

        assertEquals(operationCount, paramTester.getOperationCount());
        assertTrue(results.length > 0);
    }

    @Test
    void testListIntegrityAfterOperations() {
        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        // Тестируем добавление
        tester.testAddToEnd(arrayList);
        tester.testAddToEnd(linkedList);

        assertEquals(SMALL_OPERATION_COUNT, arrayList.size());
        assertEquals(SMALL_OPERATION_COUNT, linkedList.size());

        // Проверяем, что все элементы на месте
        for (int i = 0; i < SMALL_OPERATION_COUNT; i++) {
            assertEquals(i, arrayList.get(i));
            assertEquals(i, linkedList.get(i));
        }
    }
}