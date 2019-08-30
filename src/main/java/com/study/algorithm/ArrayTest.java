package com.study.algorithm;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author YCKJ1409
 * 给定一个整数数组和一个整数，返回两个数组的索引，这两个索引指向的数字的加和等于指定的整数。需要最优的算法，分析算法的空间和时间复杂度
 */
public class ArrayTest {
    public static void main(String[] args) {
        int [] nums  = new int[]{1,2,3,4};
        int target = 5;
        int [] result = twoSum(nums,target);
        System.out.println(Arrays.toString(result));
        for (int x:result) {
            System.out.println(x);
        }
    }
    public static int[] twoSum(int[] nums, int target) {
        if(nums==null || nums.length<2){
            return new int[]{0,0};
        }
        HashMap<Integer, Integer> map = new HashMap<>(2);
        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]), i};
            }else{
                map.put(target-nums[i], i);
            }
        }

        return new int[]{0,0};
    }
}
