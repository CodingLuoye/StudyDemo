package com.study.algorithm;

public class BinarySearch {

    static final double flag= 0.000001;

    public static void main(String[] args) {
        System.out.println(sqrt2(2));
        System.out.println(binarySearch(2));

        System.out.println(bitCount(15));
    }



    public static int bitCount(int n){
        int count = 0;
        while(n != 0){
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static double sqrt2(double x) {
        if (x == 0) {
            return 0;
        }
        double last = 0.0;
        double res = 1.0;
        while (res != last) {
            last = res;
            res = (res + x / res) / 2;
        }
        return res;
    }

    public static double binarySearch(double n){
        double low = 0.0;double high = n;
        double mid = (low + high )/2;
        while ((mid -low) > flag){
            if(mid * mid > n){
                high = mid;
            }else{
                low = mid;
            }
            mid = (high + low) / 2;
        }
        return mid;
    }

}
