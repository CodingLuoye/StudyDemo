package com.study.algorithm;

public class IntegerRepeat {

    public static void main(String[] args) {
        int [] nums = new int[]{1,3,4,2,2};
        System.out.println(findDuplicate(nums));
    }

    public static int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        while(true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(slow == fast) {
                fast = 0;
                while(nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }
}
