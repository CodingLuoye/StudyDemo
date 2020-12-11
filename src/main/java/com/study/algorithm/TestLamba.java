package com.study.algorithm;

import java.util.Arrays;

public class TestLamba {
    public static void main(String[] args) {

        int array [] = new int[]{1,2,1,4,4,6};
        Arrays.stream(array).distinct().mapToObj(x->x+",").parallel().forEach(System.out::println);
        Arrays.stream(array).distinct().mapToObj(x->x+",").forEach(System.out::println);
    }
}
