package my.bak.L0_Fib;


class Solution {
    {
        int a =2;
        System.out.println(a*2);
    }

    public Solution() {
    }

    // 普通递归
    public int fib(int n){
        if (n==0 || n==1)
            return n;
        return fib(n-1) + fib(n-2);
    }

    // 尾递归
    public int fibTail(int n){
        // 传入初始的 0,1(当n=0/1时，结果就是0,1)
        // 递归的目的就是进行n次的相加，相当于倒序相加
        return fibTail(n,0,1);
    }

    public int fibTail(int n,int cur,int next){
        if (n==0) {
            return cur;
        }
        return fibTail(n-1,next,cur+next);
    }

}

public class 斐波那契 {
    public static void main(String[] args) {
        //System.out.println(new Solution().fib(2));
        System.out.println(new Solution().fibTail(4));
    }
}
