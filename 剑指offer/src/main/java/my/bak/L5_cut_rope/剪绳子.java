package my.bak.L5_cut_rope;


class Solution {
    public int cuttingRope(int n) {
        int d;
        if(n<6){
            d = n/2;
            return(d*(n-d));
        }
        d = n/3;
        return (d * d * (n-d-d));
    }
}

public class 剪绳子 {
    public static void main(String[] args) {
        System.out.println(new Solution().cuttingRope(10));
    }
}
