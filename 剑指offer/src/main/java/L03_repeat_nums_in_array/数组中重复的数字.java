package L03_repeat_nums_in_array;

import java.util.HashSet;
import java.util.Set;

public class 数组中重复的数字 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int repeatNumber = solution.findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3});
        System.out.println(repeatNumber);
    }

}


// 我的解法  暴力破解
class Solution {
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        for(;i<nums.length-1;i++){
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    return nums[j];
            }
        }
        return -1;
    }
}

class Solution2 {

    public int findRepeatNumber(int[] nums) {
        // 新建一个hashSet
        Set<Integer> numsSet = new HashSet<>();
        // 每次遇到数字，存到set中，存入失败则重复
        for (int num : nums) {
            boolean ok = numsSet.add(num);
            if (!ok)
                return num;
        }
        return -1;
    }
}