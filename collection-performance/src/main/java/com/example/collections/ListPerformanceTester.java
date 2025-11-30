package com.example.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Класс для сравнения производительности ArrayList и LinkedList
 * Тестирует основные операции: добавление, удаление, получение элементов
 */
public class ListPerformanceTester {

    private final int operationCount;
    private final Random random;

    /**
     * Конструктор
     * @param operationCount количество операций для тестирования
     */
    public ListPerformanceTester(int operationCount) {
        this.operationCount = operationCount;
        this.random = new Random();
    }

    /**
     * @return количество операций для тестирования
     */
    public int getOperationCount() {
        return operationCount;
    }

    /**
     * Тестирует операцию добавления элементов в начало списка
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testAddToBeginning(List<Integer> list) {
        long startTime = System.nanoTime();

        for (int i = 0; i < operationCount; i++) {
            list.add(0, i);
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Тестирует операцию добавления элементов в конец списка
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testAddToEnd(List<Integer> list) {
        long startTime = System.nanoTime();

        for (int i = 0; i < operationCount; i++) {
            list.add(i);
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Тестирует операцию добавления элементов в случайную позицию
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testAddToRandomPosition(List<Integer> list) {
        // Сначала добавляем немного элементов для возможности вставки
        for (int i = 0; i < Math.min(operationCount, 100); i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < operationCount; i++) {
            int position = list.isEmpty() ? 0 : random.nextInt(list.size());
            list.add(position, i);
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Тестирует операцию получения элементов по индексу
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testGetByIndex(List<Integer> list) {
        // Сначала заполняем список
        for (int i = 0; i < operationCount; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < operationCount; i++) {
            list.get(i % list.size());
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Тестирует операцию получения элементов по случайному индексу
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testGetByRandomIndex(List<Integer> list) {
        // Сначала заполняем список
        for (int i = 0; i < operationCount; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < operationCount; i++) {
            int index = random.nextInt(list.size());
            list.get(index);
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Тестирует операцию удаления элементов из начала списка
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testRemoveFromBeginning(List<Integer> list) {
        // Сначала заполняем список
        for (int i = 0; i < operationCount; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();

        while (!list.isEmpty()) {
            list.remove(0);
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Тестирует операцию удаления элементов из конца списка
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testRemoveFromEnd(List<Integer> list) {
        // Сначала заполняем список
        for (int i = 0; i < operationCount; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();

        while (!list.isEmpty()) {
            list.remove(list.size() - 1);
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Тестирует операцию удаления элементов из случайной позиции
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testRemoveFromRandomPosition(List<Integer> list) {
        // Сначала заполняем список
        for (int i = 0; i < operationCount; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();

        while (!list.isEmpty()) {
            int position = random.nextInt(list.size());
            list.remove(position);
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Тестирует операцию поиска элемента в списке
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testSearchElement(List<Integer> list) {
        // Сначала заполняем список
        for (int i = 0; i < operationCount; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();

        for (int i = 0; i < operationCount; i++) {
            list.contains(i);
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Тестирует операцию итерации по всем элементам списка
     * @param list тестируемый список
     * @return время выполнения в наносекундах
     */
    public long testIteration(List<Integer> list) {
        // Сначала заполняем список
        for (int i = 0; i < operationCount; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();

        for (Integer value : list) {
            // Просто итерация, без операций
        }

        return System.nanoTime() - startTime;
    }

    /**
     * Запускает все тесты и возвращает результаты
     * @return массив результатов производительности
     */
    public PerformanceResult[] runAllTests() {
        return new PerformanceResult[] {
                runTest("add(в начало)", this::testAddToBeginning),
                runTest("add(в конец)", this::testAddToEnd),
                runTest("add(случайная позиция)", this::testAddToRandomPosition),
                runTest("get(по индексу)", this::testGetByIndex),
                runTest("get(случайный индекс)", this::testGetByRandomIndex),
                runTest("remove(из начала)", this::testRemoveFromBeginning),
                runTest("remove(из конца)", this::testRemoveFromEnd),
                runTest("remove(случайная позиция)", this::testRemoveFromRandomPosition),
                runTest("search(contains)", this::testSearchElement),
                runTest("iteration(foreach)", this::testIteration)
        };
    }

    /**
     * Выполняет тест для обоих типов списков и возвращает результат
     * @param testName название теста
     * @param testFunction функция тестирования
     * @return результат производительности
     */
    private PerformanceResult runTest(String testName, TestFunction testFunction) {
        // Выполняем "прогрев" JVM
        testFunction.test(new ArrayList<>());
        testFunction.test(new LinkedList<>());

        // Основные замеры
        long arrayListTime = testFunction.test(new ArrayList<>());
        long linkedListTime = testFunction.test(new LinkedList<>());

        return new PerformanceResult(testName, operationCount, arrayListTime, linkedListTime);
    }

    /**
     * Функциональный интерфейс для тестирования
     */
    @FunctionalInterface
    private interface TestFunction {
        long test(List<Integer> list);
    }
}