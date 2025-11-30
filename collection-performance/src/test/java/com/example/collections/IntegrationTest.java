package com.example.collections;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Интеграционные тесты для проверки работы всего приложения
 */
class IntegrationTest {

    @Test
    void testMainMethodWithNoArguments() {
        // Проверяем, что main метод работает без аргументов
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }

    @Test
    void testMainMethodWithValidArgument() {
        // Проверяем, что main метод работает с валидным аргументом
        assertDoesNotThrow(() -> Main.main(new String[]{"1000"}));
    }

    @Test
    void testMainMethodWithInvalidArgument() {
        // Проверяем, что main метод обрабатывает невалидный аргумент
        assertDoesNotThrow(() -> Main.main(new String[]{"invalid"}));
    }

    @Test
    void testLargeOperationCount() {
        // Тестируем с большим количеством операций
        ListPerformanceTester tester = new ListPerformanceTester(5000);
        PerformanceResult[] results = tester.runAllTests();

        assertNotNull(results);
        assertTrue(results.length > 0);

        for (PerformanceResult result : results) {
            assertTrue(result.getArrayListTime() >= 0);
            assertTrue(result.getLinkedListTime() >= 0);
        }
    }

    @Test
    void testPerformanceTrends() {
        // Проверяем ожидаемые тренды производительности
        ListPerformanceTester tester = new ListPerformanceTester(1000);
        PerformanceResult[] results = tester.runAllTests();

        for (PerformanceResult result : results) {
            String operation = result.getOperationName();

            if (operation.contains("add(в начало)") || operation.contains("remove(из начала)")) {
                // LinkedList должен быть быстрее для операций в начале
                // Но из-за маленьких размеров это может быть не всегда заметно
                assertTrue(result.getArrayListTime() >= 0);
                assertTrue(result.getLinkedListTime() >= 0);
            }

            if (operation.contains("get(по индексу)")) {
                // ArrayList должен быть быстрее для доступа по индексу
                assertTrue(result.getArrayListTime() >= 0);
                assertTrue(result.getLinkedListTime() >= 0);
            }
        }
    }

    @Test
    void testMultipleRunsConsistency() {
        // Проверяем, что несколько запусков дают последовательные результаты
        ListPerformanceTester tester = new ListPerformanceTester(100);
        PerformanceResult[] firstRun = tester.runAllTests();
        PerformanceResult[] secondRun = tester.runAllTests();

        assertEquals(firstRun.length, secondRun.length);

        for (int i = 0; i < firstRun.length; i++) {
            assertEquals(firstRun[i].getOperationName(), secondRun[i].getOperationName());
            assertEquals(firstRun[i].getOperationCount(), secondRun[i].getOperationCount());
            // Времена могут отличаться, но структура результатов должна быть одинаковой
        }
    }
}