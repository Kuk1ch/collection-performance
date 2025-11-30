package com.example.collections;

/**
 * Главный класс приложения для запуска тестов производительности
 */
public class Main {

    /**
     * Точка входа в программу
     * @param args аргументы командной строки (можно передать количество операций)
     */
    public static void main(String[] args) {
        int operationCount = 10000;

        // Если передан аргумент, используем его как количество операций
        if (args.length > 0) {
            try {
                operationCount = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат числа. Использую значение по умолчанию: 10000");
            }
        }

        System.out.println("🚀 Запуск тестов производительности ArrayList vs LinkedList");
        System.out.println("==========================================================");

        ListPerformanceTester tester = new ListPerformanceTester(operationCount);
        PerformanceResult[] results = tester.runAllTests();

        printResults(results);
        printSummary(results);
    }

    /**
     * Выводит подробные результаты тестирования в табличном формате
     * @param results массив результатов тестирования
     */
    private static void printResults(PerformanceResult[] results) {
        System.out.printf("\n📊 Результаты тестирования (%d операций):\n", results[0].getOperationCount());
        System.out.println("==================================================================================");
        System.out.printf("%-25s %-15s %-15s %-12s %-10s%n",
                "Метод", "ArrayList (нс)", "LinkedList (нс)", "Быстрее", "Разница");
        System.out.println("----------------------------------------------------------------------------------");

        for (PerformanceResult result : results) {
            System.out.printf("%-25s %-15d %-15d %-12s %-10.2fx%n",
                    result.getOperationName(),
                    result.getArrayListTime(),
                    result.getLinkedListTime(),
                    result.getFasterList(),
                    result.getPerformanceRatio());
        }
    }

    /**
     * Выводит итоговую статистику по тестированию
     * @param results массив результатов тестирования
     */
    private static void printSummary(PerformanceResult[] results) {
        int arrayListWins = 0;
        int linkedListWins = 0;

        for (PerformanceResult result : results) {
            if ("ArrayList".equals(result.getFasterList())) {
                arrayListWins++;
            } else {
                linkedListWins++;
            }
        }

        System.out.println("\n📈 Итоговая статистика:");
        System.out.println("=====================");
        System.out.println("Побед ArrayList: " + arrayListWins);
        System.out.println("Побед LinkedList: " + linkedListWins);

        if (arrayListWins > linkedListWins) {
            System.out.println("🏆 ArrayList показал лучшую производительность в большинстве тестов");
        } else if (linkedListWins > arrayListWins) {
            System.out.println("🏆 LinkedList показал лучшую производительность в большинстве тестов");
        } else {
            System.out.println("⚖️ Производительность примерно одинаковая");
        }

        System.out.println("\n💡 Рекомендации по использованию:");
        System.out.println("- ArrayList лучше для частого доступа по индексу и добавления в конец");
        System.out.println("- LinkedList лучше для частых вставок/удалений в начале/середине списка");
    }
}