package L04_find_in_2d_array;

public class 二维数组中的查找 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(
                solution.findNumberIn2DArray(
                        new int[][]{
                                {1,  4,  7,  11, 15},
                                {2,  5,  8,  12, 19},
                                {3,  6,  9,  16, 22},
                                {10, 13, 14, 17, 24},
                                {18, 21, 23, 26, 30}
                        }, 24));
    }
}

// （自）第1版 有问题的一版！
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0)
            return false;

        int n = matrix.length;
        int m = matrix[0].length;

        return doHandle(0, 0, n, m, matrix, target);
    }

    // 待递归处理的部分
    // 每次递归，需要知道本次递归的初始位置（长方形的位置坐标）
    public boolean doHandle(/*下次遍历的开始位置*/int xMark, int yMark, /*下次遍历的边界*/int xLimit, int yLimit, int[][] matrix, int target) {


        if (xMark >= xLimit || yMark >= yLimit)
            return false;

        int x = xMark, y = yMark;

        // 对角线方式 一直往下
        for (; (x < xLimit && y < yLimit); x++, y++) {
            if (matrix[y][x] == target)
                return true;

            else if (matrix[y][x] > target) {  // 提前进行结束比对，开始比对分支模块
                return doHandle(x, 0, xLimit, y, matrix, target) || doHandle(0, y, x, yLimit, matrix, target);
            }

        }

        // 循环结束，不代表没有可能了，最多代表一个方向上结束了

        // 循环结束，如果是【横着】方向，还可以递归，就继续递归
        if (y >= yLimit || x < xLimit) { // 横着方向没递归完，还能递归
            return  /*X轴方向*/doHandle(x, y-1, xLimit, y, matrix, target);
        }
        // 循环结束，如果是【竖着】方向，还可以递归，就继续递归
        else if (x >= xLimit || y < yLimit) { // 竖着方向没递归完，还能递归
            return  /*Y轴方向*/doHandle(x-1, y, x, yLimit, matrix, target);
        }

        // 有一个方向比对出结果，就返回true
        return false;
    }

}

// （自）第2版
class Solution2 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {

        if (matrix == null || matrix.length == 0)
            return false;

        int n = matrix.length;
        int m = matrix[0].length;

        return doHandle(0, 0, n, m, matrix, target);
    }

    // 待递归处理的部分
    // 每次递归，需要知道本次递归的初始位置（长方形的位置坐标）
    public boolean doHandle(/*下次遍历的开始位置*/int xMark, int yMark, /*下次遍历的边界*/int xLimit, int yLimit, int[][] matrix, int target) {


        if (xMark >= xLimit || yMark >= yLimit)
            return false;

        int x = xMark, y = yMark;

        for (; (x < xLimit && y < yLimit); x++, y++) {
            if (matrix[y][x] == target)
                return true;

            else if (matrix[y][x] > target)  // 提前进行结束比对，开始比对蓝色长方形
                break;

        }


        boolean xNext = false;
        boolean yNext = false;
        /**
         * 注意！问题就在现在传入的xMark和yMark
         * 如果现在yMark还是0，等到递归进去，下次再往y方向递归时，y其实不应该是0
         */
        // 循环结束，如果是【横着】方向，还可以递归，就继续递归
        if (x < xLimit) { // 横着方向没递归完，还能递归
            xNext =   /*X轴方向*/doHandle(x, 0, xLimit, y, matrix, target);
            if (xNext) return true;
        }
        // 循环结束，如果是【竖着】方向，还可以递归，就继续递归
        if (y < yLimit) { // 竖着方向没递归完，还能递归
            yNext =   /*Y轴方向*/doHandle(0, y, x, yLimit, matrix, target);
            if (yNext) return true;
        }

        // 有一个方向比对出结果，就返回true
        return false;
    }

}