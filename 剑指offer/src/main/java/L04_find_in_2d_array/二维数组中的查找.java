package L04_find_in_2d_array;

public class 二维数组中的查找 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.findNumberIn2DArray(new int[][]{{-1, 3}}, 3));
    }
}


class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0)
            return false;

        int n = matrix.length;
        int m = matrix[0].length;

        return doHandle(0, 0, n, m, matrix, target);
    }

    // 待递归处理的部分
    // 每次递归，把蓝色正方形边界传过来
    public boolean doHandle(/*下次遍历的开始位置*/int xMark, int yMark, /*下次遍历的边界*/int xLimit, int yLimit, int[][] matrix, int target) {


        if (xMark >= xLimit || yMark >= yLimit)
            return false;

        int x = xMark, y = yMark;

        for (; (x < xLimit && y < yLimit); x++, y++) {
            if (matrix[y][x] > target) // 提前进行结束比对，开始比对蓝色长方形
                break;
            else if (matrix[y][x] == target)
                return true;
        }


        boolean xNext = false;
        boolean yNext = false;

        // 循环结束，如果是【横着】方向，还可以递归，就继续递归
        if (x < xLimit) { // 横着方向没递归完，还能递归
            xNext  =   /*X轴方向*/doHandle(x, 0, xLimit, y, matrix, target);
        }
        // 循环结束，如果是【竖着】方向，还可以递归，就继续递归
        if (y < yLimit) { // 竖着方向没递归完，还能递归
            yNext  =   /*Y轴方向*/doHandle(0, y,x,yLimit,matrix, target);
        }

        // 有一个方向比对出结果，就返回true
        return xNext || yNext;
    }

}