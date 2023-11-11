package aed.sorting;

import aed.utils.TemporalAnalysisUtils;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;

public class MergeInsertionSort extends Sort {

    private static final int MAX_INTERVAL = 64;

    // creates a random generator with a specific seed
    // this is useful for testing methods that are supposed to generate random
    // elements
    // because we can always repeat the same tests by using the same seed
    private static final Random pseudoRandom = new Random(4582);

    // sort an array of elements (using MergeSort Bottom Up)
    // this method uses extra memory of O(n) to perform the sort
    public static <T extends Comparable<T>> void traditionalBottomUpSort(T[] a) {
        int n = a.length;
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[n];
        for (int groupSize = 1; groupSize < n; groupSize *= 2) {
            for (int low = 0; low < n - groupSize; low += 2 * groupSize) {
                merge(a, aux, low, low + groupSize - 1, Math.min(low + 2 * groupSize - 1, n - 1));
            }
        }

    }

    public static <T extends Comparable<T>> void merge(T[] a, T[] aux, int low, int mid, int high) {
        int left = low;
        int right = mid + 1;
        for (int i = low; i <= high; i++) {
            aux[i] = a[i];
        }
        for (int i = low; i <= high; i++) {
            // left array is exausted
            if (left > mid)
                a[i] = aux[right++];
            // right array is exausted
            else if (right > high)
                a[i] = aux[left++];
            else if (less(aux[right], aux[left]))
                a[i] = aux[right++];
            else
                a[i] = aux[left++];
        }
    }

    // sort an array of elements (using InsertionSort) from low to high (including)
    public static <T extends Comparable<T>> void insertionSort(T[] a, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            for (int j = i; j > low; j--) {
                if (less(a[j], a[j - 1])) {
                    exchange(a, j, j - 1);
                } else
                    break;
            }
        }
    }

    public static int determineRunSize(int n) {
        int k = n;
        int r = 0;
        if (k >= MAX_INTERVAL) {
            while (k > 0) {
                if (k % 2 != 0)
                    r++;
                if (k / 2 >= 32 && k / 2 < 64 && r != 0)
                    return k / 2 + 1;
                else if (k / 2 >= 32 && k / 2 < 64)
                    return k / 2;
                k /= 2;
            }
        }
        return k;
    }

    public static <T extends Comparable<T>> void sort(T[] a) {
        int n = a.length;
        @SuppressWarnings("unchecked")
        T[] aux = (T[]) new Comparable[n];
        int k = determineRunSize(n);
        if (n < MAX_INTERVAL) {
            insertionSort(a, 0, n - 1);
            return;
        }
        for(int i = 0; i < n; i += k){
            insertionSort(a, i, Math.min(k + i - 1, n - 1));
        }
        // insertionSort(a, low, n-1);
        for (int groupSize = k; groupSize < n; groupSize *= 2) {
            for (int lowest = 0; lowest < n - groupSize; lowest += 2 * groupSize) {
                merge(a, aux, lowest, lowest + groupSize - 1, Math.min(lowest + 2 * groupSize - 1, n - 1));
            }
        }
    }

    public static Integer[] generateRandomExample(int n) {
        return generateRandomExample(pseudoRandom, n);
    }

    public static Integer[] generateRandomExample(Random randomGenerator, int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = randomGenerator.nextInt(n);
        }
        return a;
    }

    public static Integer[] generateMostlySortedExample(int n) {
        return generateMostlySortedExample(pseudoRandom, n);
    }

    public static Integer[] generateMostlySortedExample(Random randomGenerator, int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n - n / 10; i++) {
            a[i] = i + 1;
        }

        for (int i = n - n / 10; i < n; i++) {
            a[i] = n - i;
        }
        return a;
    }

    public static Integer[] generateAlmostSortedExample(int n) {
        return generateAlmostSortedExample(pseudoRandom, n);
    }

    public static Integer[] generateAlmostSortedExample(Random randomGenerator, int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n - n / 100; i++) {
            a[i] = i + 1;
        }

        for (int i = n - n / 100; i < n; i++) {
            a[i] = n - i;
        }
        return a;
    }

    public static Integer[] generateAscendingExample(int n) {
        return generateAscendingExample(pseudoRandom, n);
    }

    public static Integer[] generateAscendingExample(Random randomGenerator, int n) {
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        return a;
    }

    public static Integer[] generateDescendingExample(int n) {
        return generateDescendingExample(pseudoRandom, n);
    }

    public static Integer[] generateDescendingExample(Random randomGenerator, int n) {
        Integer[] a = new Integer[n];
        for (int i = 1; i <= n; i++) {
            a[i - 1] = n - i;
        }
        return a;
    }

    public static double sortedP(Integer[] a) {
        double n = a.length;
        double sorted = 0;
        for (int i = 1; i < n; i++) {
            if (a[i - 1] < a[i])
                sorted++;
        }
        return (sorted / n);
    }

        public static boolean isExponential(int n, int e){
        int k = 2;
         while(k < n){
            if(k == n) return true;
            else k *= e;
         }
         return false;
    }

    private static<T> double getAverageExecutionTime(int trials, int complexity, Consumer<T> method, Function<Integer, T> exampleGenerator) {
        T example;
        double initialTime;
        double finalTime = 0;
        double AET = 0;
            for (int n = 1; n <= trials; n++) {
                example = exampleGenerator.apply(complexity);

                    initialTime = (double) System.nanoTime();
                    method.accept(example);
                    finalTime += (double) System.nanoTime() - initialTime;
                example = null;
                System.gc();
            }
            AET = finalTime / 1000000 / trials;
        return AET;
    }

    private static<T> double getAverageExecutionTimeInsertionSort(int numberOfArrays, int complexity, Consumer<T> method, Function<Integer, T> exampleGenerator) {
        T example;
        double initialTime;
        double finalTime = 0;
        double AET = 0;
            for (int n = 1; n <= numberOfArrays; n++) {
                example = exampleGenerator.apply(complexity);
                // if (method.equals("sort")) {
                    initialTime = (double) System.nanoTime();
                    method.accept(example);
                    finalTime += (double) System.nanoTime() - initialTime;
                example = null;
                System.gc();
            }
            AET = finalTime / 1000000;
        return AET;
    }

    public static void main(String[] args) {
    
        //sort demonstra ser mais eficiente quando o array é aleatório e se "n" for uma pontência de 2
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "TraditionalBottomUpSort/Random", 
        MergeInsertionSort::generateRandomExample, MergeInsertionSort::traditionalBottomUpSort, 32, 17,
        30);
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "Sort/Random",                                           
        MergeInsertionSort::generateRandomExample, MergeInsertionSort::sort, 32, 17,
        30);

        //TraditionalBottomUpSort demonstra ser mais muito pouco mais eficiente quando o array é aleatório e se "n" não for uma pontência de 2
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "TraditionalBottomUpSort/Random", 
        MergeInsertionSort::generateRandomExample, MergeInsertionSort::traditionalBottomUpSort, 100, 15,
        30);
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "Sort/Random",                                           
        MergeInsertionSort::generateRandomExample, MergeInsertionSort::sort, 100, 15,
        30);

        //sort demonstra ser mais eficiente quando o  quando 90% do array está ordenado e se "n" for uma pontência de 2
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "traditionalBottomUpSort/Mostly",
        MergeInsertionSort::generateMostlySortedExample, MergeInsertionSort::traditionalBottomUpSort,
        32, 17, 30);
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "sort/Almost",
        MergeInsertionSort::generateMostlySortedExample, MergeInsertionSort::sort,
        32, 17, 30);

        //sort demonstra ser mais eficiente quando o  quando 90% do array está ordenado e se "n" não for uma pontência de 2
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "traditionalBottomUpSort/Mostly",
        MergeInsertionSort::generateMostlySortedExample, MergeInsertionSort::traditionalBottomUpSort,
        100, 15, 30);
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "sort/Almost",
        MergeInsertionSort::generateMostlySortedExample, MergeInsertionSort::sort,
        100, 15, 30);

        //sort demonstra ser mais eficiente quando o  quando 99% do array está ordenado e se "n" for uma pontência de 2
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "traditionalBottomUpSort/Mostly",
        MergeInsertionSort::generateAlmostSortedExample, MergeInsertionSort::traditionalBottomUpSort,
        32, 17, 30);
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "sort/Almost",
        MergeInsertionSort::generateAlmostSortedExample, MergeInsertionSort::sort,
        32, 17, 30);

        //sort demonstra ser mais eficiente quando o  quando 99% do array está ordenado e se "n" não for uma pontência de 2
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "traditionalBottomUpSort/Mostly",
        MergeInsertionSort::generateAlmostSortedExample, MergeInsertionSort::traditionalBottomUpSort,
        100, 15, 30);
        TemporalAnalysisUtils.runDoublingRatioTest("tests.tsv", "sort/Almost",
        MergeInsertionSort::generateAlmostSortedExample, MergeInsertionSort::sort,
        100, 15, 30);

        //traditionalBottomUpSort mostrou ser mais eficiente a ordenar 100 arrays de 40 elementos
        System.out.println(getAverageExecutionTimeInsertionSort(100, 40, MergeInsertionSort::traditionalBottomUpSort, MergeInsertionSort::generateRandomExample));
        System.out.println(getAverageExecutionTimeInsertionSort(100, 40, MergeInsertionSort::sort, MergeInsertionSort::generateRandomExample));


        ///sort mostrou ser mais eficiente a ordenar 1000 arrays de 40 elementos
        System.out.println(getAverageExecutionTimeInsertionSort(1000, 40, MergeInsertionSort::traditionalBottomUpSort, MergeInsertionSort::generateRandomExample));
        System.out.println(getAverageExecutionTimeInsertionSort(1000, 40, MergeInsertionSort::sort, MergeInsertionSort::generateRandomExample));

        //sort mostrou ser mais eficiente a ordenar 10000 arrays de 40 elementos
        System.out.println(getAverageExecutionTimeInsertionSort(10000, 40, MergeInsertionSort::traditionalBottomUpSort, MergeInsertionSort::generateRandomExample));
        System.out.println(getAverageExecutionTimeInsertionSort(10000, 40, MergeInsertionSort::sort, MergeInsertionSort::generateRandomExample));
        
        //Sumário: A função sort foi mais eficiente em 80% dos testes. Teve mais eficiência quando era o array tinha potência de 2, mas a função traditionalBottomUpSort teve mais
        //eficiência quando o array era completamente desordenado, os resultados dos testes indicaram que n havia grande diferença entra as duas funções quando o array era totalmente
        //desordenado mas quando esse mesmo array vinha ordenado de alguma forma o sort era mais eficiente, chegando a ser quase 2x mais rápido na sua execução que o traditionalBottomUpSort.
        //A eficiência das duas funções quando o array é pequeno foi um pouco estranho. O traditionalBottomUpSort foi mais eficiente quando a quantidade de arrays ordenados era 100,
        //mas quando subiamos esse valor para 1.000 e 10.000 a eficiência do sort(insertionsort) mostrava a sua força em arrays pequenos, apenas houve uma anomalia quando "numberOfArrays"
        // era 100.
    }

    // this method might be usefull for testing
    private static void printArray(Object[] a, int low, int high) {
        if (a == null || a.length == 0) {
            System.out.println("Array: []");
            return;
        }

        if (low > 0) {
            System.out.print("Array: [...");
        } else {
            System.out.print("Array: [");
        }

        for (int i = low; i <= high; i++) {
            System.out.print(a[i] + ",");
        }
        if (high < a.length - 1) {
            System.out.print(a[a.length - 1] + "...]");
        } else {
            System.out.print(a[a.length - 1] + "]");
        }

        System.out.println();

    }
}
