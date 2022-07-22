package my.bak.L1_RunWays;

class Solution {
    public int numWays(int n) {
        if(n==0 || n==1) //题目要求0级楼梯也应该跳1下
            return 1;

        int a=1,b=1,sum=0;
        for(int i=1;i<n;i++){  // 注意循环开始的下标，从2开始进入循环获取结果，那么下标i应该从1开始
            sum = (a + b)%1000000007;
            b = a;   //注意b的赋值应该在a前面，不然a=sum，b=a，b也等于sum了
            a = sum;
        }
        return sum;
    }
}

public class 青蛙跳楼梯 {
    public static void main(String[] args) {
        System.out.println(new Solution().numWays(92));
    }
}
