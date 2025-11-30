package com.example.collections;

/**
 * Класс для хранения результатов производительности
 * Содержит информацию о времени выполнения операций для разных типов списков
 */
public class PerformanceResult {
    private final String operationName;
    private final int operationCount;
    private final long arrayListTime;
    private final long linkedListTime;
    private final String fasterList;

    /**
     * Конструктор для создания результата тестирования
     *
     * @param operationName название операции
     * @param operationCount количество операций
     * @param arrayListTime время выполнения для ArrayList в наносекундах
     * @param linkedListTime время выполнения для LinkedList в наносекундах
     */
    public PerformanceResult(String operationName, int operationCount,
                             long arrayListTime, long linkedListTime) {
        this.operationName = operationName;
        this.operationCount = operationCount;
        this.arrayListTime = arrayListTime;
        this.linkedListTime = linkedListTime;
        this.fasterList = arrayListTime < linkedListTime ? "ArrayList" : "LinkedList";
    }

    /**
     * @return название операции
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * @return количество операций
     */
    public int getOperationCount() {
        return operationCount;
    }

    /**
     * @return время выполнения для ArrayList в наносекундах
     */
    public long getArrayListTime() {
        return arrayListTime;
    }

    /**
     * @return время выполнения для LinkedList в наносекундах
     */
    public long getLinkedListTime() {
        return linkedListTime;
    }

    /**
     * @return какой список быстрее для данной операции
     */
    public String getFasterList() {
        return fasterList;
    }

    /**
     * @return разница во времени выполнения между списками
     */
    public long getTimeDifference() {
        return Math.abs(arrayListTime - linkedListTime);
    }

    /**
     * @return процентное соотношение производительности
     */
    public double getPerformanceRatio() {
        if (arrayListTime == 0 || linkedListTime == 0) return 0;
        return (double) Math.max(arrayListTime, linkedListTime) / Math.min(arrayListTime, linkedListTime);
    }

    @Override
    public String toString() {
        return String.format("%s: ArrayList=%d ns, LinkedList=%d ns, Faster=%s (%.2fx)",
                operationName, arrayListTime, linkedListTime, fasterList, getPerformanceRatio());
    }
}