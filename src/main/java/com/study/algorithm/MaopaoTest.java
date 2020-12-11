package com.study.algorithm;

import java.util.Arrays;

public class MaopaoTest {

    public static void main(String[] args) {

        int array [] = new int[]{5,4,1,2,3};
//        maopao(array);
        insert(array);
    }

    public static void maopao(int array[]){
        for (int i =0;i<array.length;i++){
            for (int j =0;j<array.length-1-i;j++){
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        Arrays.stream(array).forEach(System.out::print);
    }

    public static void insert(int array[]){
        for (int i =0;i<array.length;i++){
            int minindex = i;
            for (int j =i;j<array.length;j++){
                if(array[j]<array[minindex]){
                    minindex = j;
                }
            }
            int temp = array[minindex];
            array[minindex] = array[i];
            array[i] = temp;
        }
        Arrays.stream(array).forEach(System.out::print);
    }
}
