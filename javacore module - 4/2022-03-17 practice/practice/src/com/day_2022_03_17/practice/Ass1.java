package com.day_2022_03_17.practice;

import java.util.Arrays;

public class Ass1 {
    private static int[] ARR = new int[]{3, 4, 10, 4, 5, 6, 4, 2, 4, 5, 6};

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int[] BubbleSort(int[] array) {
        int arr[] = Arrays.copyOf(array, array.length);

        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    public static int countArraySum(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    public static void findMax(int[] arr) {
        int maxNumber = -99999999;
        for (int i : arr) {
            if (i > maxNumber)
                maxNumber = i;
        }
        System.out.println("Maxium number is " + maxNumber);
    }

    public static void printArr(int[] arr){
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.print("Array: ");
        printArr(ARR);

        System.out.print("Bubble sort: ");
        printArr(BubbleSort(ARR));

        System.out.println("Factorial each number: ");
        for(int i: ARR){
            System.out.println(i+"! = " + factorial(i));
        }

        System.out.println("Sum all value: " + countArraySum(ARR));
        findMax(ARR);
    }

}
