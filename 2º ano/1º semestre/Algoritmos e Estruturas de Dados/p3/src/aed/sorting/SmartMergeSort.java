package aed.sorting;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class SmartMergeSort extends Sort {

    private static final int MAX_INTERVAL = 64;

    // creates a random generator with a specific seed
    // this is useful for testing methods that are supposed to generate random
    // elements
    // because we can always repeat the same tests by using the same seed
    private static final Random pseudoRandom = new Random(3729);

    // sort an array of elements (using InsertionSort) from low to high (including)
    // assuming that the first n elements are already sorted between themselves
    // this variation implies that we can start with a bigger hand immediately
    // this method is very useful for the SmartMergeSort in order to extend a
    // natural
    // ascending run with additional elements to make a run with minimum size
    public static <T extends Comparable<T>> void insertionSortWithInitialSortedHand(T[] a, int low, int n, int high) {
        assert (low < high);
        assert (n > 0);
        assert (low + n <= high);
        for (int i = low + n; i <= high; i++) {
            for (int j = i; j > low; j--) {
                if (less(a[j], a[j - 1])) {
                    exchange(a, j, j - 1);
                } else
                    break;
            }
        }
    }

    public static <T extends Comparable<T>> Run getNaturalRun(T[] a, int low, int high) {
        assert (low < high);
        Run run = new Run(low, 1);
        for (int i = low + 1; i <= high; i++) {
            if (less(a[i], a[i - 1]))
                break;
            else
                run.length++;
        }
        return run;
    }

    public static <T extends Comparable<T>> void invertArray(T[] a, int low, int high) {
        while (low < high) {
            exchange(a, low++, high--);
        }
    }

    public static <T extends Comparable<T>> Run getNaturalOrMakeAscendingRun(T[] a, int low, int high) {
        assert (low < high);
        Run run = new Run(low, 1);
        if (a.length == 1 || high - low < 1)
            return run;
        else if (!less(a[low + 1], a[low]))
            run = getNaturalRun(a, low, high);
        else {
            for (int i = low + 1; i <= high; i++) {
                if (less(a[i], a[i - 1]))
                    run.length++;
                else
                    break;
            }
            invertArray(a, low, low + run.length - 1);
        }
        return run;
    }

    public static <T extends Comparable<T>> Run getNextRunWithMinimumSize(T[] a, int low, int high, int minRunSize) {
        assert (low < high);
        assert (minRunSize > 0);
        Run run = getNaturalOrMakeAscendingRun(a, low, high);
        if (run.length < minRunSize) {
            insertionSortWithInitialSortedHand(a, low, run.length, Math.min(low + minRunSize - 1, high));
            run = getNaturalRun(a, low, high);
        }
        return run;
    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, Run leftRun, Run rightRun) {
        assert (rightRun.start == leftRun.start + leftRun.length);
        // if (leftRun.start > rightRun.start) {
        // Run run = leftRun;
        // leftRun = rightRun;
        // rightRun = run;
        // }
        int low = leftRun.start;
        int mid = rightRun.start - 1;
        int high = rightRun.start + rightRun.length - 1;
        // if(leftRun.length > rightRun.length){
        //     MergeInsertionSort.merge(a, aux, low, mid, high);
        // }
        MergeInsertionSort.merge(a, aux, low, mid, high);
    }

    public static <T extends Comparable<T>> void mergeCollapse(MergeStack stack, T[] a, T[] aux) {
        int h = stack.height();
        while (h > 1) {
            if (h >= 3 && stack.get(1).length > stack.get(3).length) {
                merge(a, aux, stack.get(3), stack.get(2));
                stack.collapse(2);
            } else if (h >= 2 && stack.get(1).length >= stack.get(2).length) {
                merge(a, aux, stack.get(2), stack.get(1));
                stack.collapse(1);
            } else if (h >= 3 && stack.get(1).length + stack.get(2).length >= stack.get(3).length) {
                merge(a, aux, stack.get(2), stack.get(1));
                stack.collapse(1);
            } else if (h >= 4 && stack.get(2).length + stack.get(3).length >= stack.get(4).length) {
                merge(a, aux, stack.get(3), stack.get(2));
                stack.collapse(2);
            } else
                break;
            h -= 1;
        }

    }

    public static <T extends Comparable<T>> void sort(T[] a) {

        // TODO: implement

        // Pro Tip: this is how we can create an aux array of Comparables
        int n = a.length;
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[n];
        MergeStack stack = new MergeStack();
        int k = MergeInsertionSort.determineRunSize(n);
        int length = 0;
        if (n < MAX_INTERVAL)
            MergeInsertionSort.insertionSort(a, 0, n - 1);
        else {
            for (int i = 0; i < n; i += length) {
                stack.push(getNextRunWithMinimumSize(a, i, n - 1, k));
                length = stack.get(1).length;
                mergeCollapse(stack, a, aux);
            }
            while (stack.height() > 1) {
                merge(a, aux, stack.get(2), stack.get(1)); // transport
                stack.collapse(1);
            }
        }
    }

    public static Integer[] generateLargeNaturalRunsExample(Random randomGenerator, int n) {
        Integer a[] = new Integer[n];
        for (int i = 1; i <= n; i++) {
            if (i % 1000 == 0) {
                a[i - 1] = 0;
            } else
                a[i - 1] = i;
        }
        return a;
    }

    // private static<T> double getAverageExecutionTime(int trials, int complexity, Consumer<T> method, Function<Integer, T> exampleGenerator) {
    //     T example;
    //     double initialTime;
    //     double finalTime = 0;
    //     double AET = 0;
    //         for (int n = 1; n <= trials; n++) {
    //             example = exampleGenerator.apply(complexity);

    //                 initialTime = (double) System.nanoTime();
    //                 method.accept(example);
    //                 finalTime += (double) System.nanoTime() - initialTime;
    //             example = null;
    //             System.gc();
    //         }
    //         AET = finalTime / 1000000 / trials;
    //     return AET;
    // }

    public static void main(String[] args) {

        //8)
        //SmartMergeSort demonstra ser mais eficiente quando 99.9% do array está ordenado e se "n" for uma pontência de 2
        // System.out.println(getAverageExecutionTime(30, 1048576, MergeInsertionSort::sort, SmartMergeSort::generateLargeNaturalRunsExample));  //average time do MergeInsertionSort sort
        // System.out.println(getAverageExecutionTime(30, 1048576, SmartMergeSort::sort, SmartMergeSort::generateLargeNaturalRunsExample));  //average time do SmartMergeSort sort

        // //SmartMergeSort demonstra ser mais eficiente quando 99.9% do array está ordenado e se "n" não for uma pontência de 2
        // System.out.println(getAverageExecutionTime(30, 1000000, MergeInsertionSort::sort, SmartMergeSort::generateLargeNaturalRunsExample));  //average time do MergeInsertionSort sort
        // System.out.println(getAverageExecutionTime(30, 1000000, SmartMergeSort::sort, SmartMergeSort::generateLargeNaturalRunsExample));  //average time do SmartMergeSort sort

        // //SmartMergeSort demonstra ser mais eficiente quando o array é aleatório e se "n" for uma pontência de 2
        // System.out.println(getAverageExecutionTime(30, 1048576, MergeInsertionSort::sort, MergeInsertionSort::generateRandomExample));  //average time do MergeInsertionSort sort
        // System.out.println(getAverageExecutionTime(30, 1048576, SmartMergeSort::sort, MergeInsertionSort::generateRandomExample));  //average time do SmartMergeSort sort

        // //SmartMergeSort demonstra ser mais eficiente quando o array é aleatório e se "n" não for uma pontência de 2
        // System.out.println(getAverageExecutionTime(30, 1000000, MergeInsertionSort::sort, MergeInsertionSort::generateRandomExample));  //average time do MergeInsertionSort sort
        // System.out.println(getAverageExecutionTime(30, 1000000, SmartMergeSort::sort, MergeInsertionSort::generateRandomExample));  //average time do SmartMergeSort sort

        // //SmartMergeSort demonstra ser esmagadoramente mais eficiente quando o array é estritamente decrescente
        // System.out.println(getAverageExecutionTime(30, 150000, MergeInsertionSort::sort, MergeInsertionSort::generateDescendingExample));  //average time do MergeInsertionSort sort
        // System.out.println(getAverageExecutionTime(30, 150000, SmartMergeSort::sort, MergeInsertionSort::generateDescendingExample));   //average time do SmartMergeSort sort
    
        // //Foi possivel concluir que o SmartMerge é mais eficiente no geral, não tendo uma grande diferença, mas quando o array é estritamente decrescente o SmartMerge destrói o MergeInsertionSort
        // //em termos de ifeciência.

    }
}
