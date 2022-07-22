package my.bak.L3_PathInMetrix;

// 个人暴力写 但是略有优化
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] wordChars = word.toCharArray();

        // 状态矩阵
        //     - 0: 未访问过，应该前去尝试
        //     - 1: 被标记为路径!
        byte[][] stats = new byte[board.length][board[0].length];

        // 该下标 指向字符串的当前字符
        int tarIndex = 0;


        // 对矩阵进行遍历(DFS)，当匹配到第一个字符的时候，直接在该字符的上 下 左 右方向开始匹配，一旦撞墙，就回退到上个存档点，继续上下左右
        // 当字符下标指向最后一位时，说明串内字符都已经找到
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (findChar(board, i, j, tarIndex, wordChars, stats)){
                    return true;
                }
            }
        }// 必须使用双重循环，不能使用 findChar(board, 0, 0, tarIndex, wordChars, stats)代替

        return false;
    }

    /**
     * 递归形式 查找目标字符
     *
     * @param board 给定矩阵
     * @param x     当前x坐标
     * @param y     当前y坐标
     * @param t     待查找字符下标
     * @param wordChars  待查找字符串
     * @param stats 状态矩阵
     * @return
     */
    private boolean findChar(char[][] board, int x, int y, int t, char[] wordChars, byte[][] stats) {

        // 该方向上 无法继续前行
        if (x < 0 || x == board.length || y < 0 || y == board[x].length)
            return false;

        // 该坐标的字符 已经在路径数组中
        if (stats[x][y] == 1) {
            return false;
        }

        // 该坐标的字符 匹配失败
        if (board[x][y] != wordChars[t]) {
            return false;
        }

        // 已经匹配到串尾 则返回true
        if (t + 1 == wordChars.length) {
            stats[x][y] = 1;
            return true;
        }

        if (board[x][y] == wordChars[t]) {
            stats[x][y] = 1;
            // 朝着上下左右四个方向匹配
            if (findChar(board, x + 1, y, t + 1, wordChars, stats) || findChar(board, x - 1, y, t + 1, wordChars, stats)
                || findChar(board, x, y + 1, t + 1, wordChars, stats) || findChar(board, x, y - 1, t + 1, wordChars, stats))
                return true;
            // 四个方向都撞墙了，说明这条路确实不通，那么本节点置0，返回false，回到上一个存档点
            stats[x][y]=0;
            return false;
        }
        return false;
    }
}

// 部分处理逻辑，合并了判断语句
class Solution2 {
    public boolean exist(char[][] board, String word) {
        char[] wordChars = word.toCharArray();

        // 状态矩阵
        //     - 0: 未访问过，应该前去尝试
        //     - 1: 被标记为路径!
        byte[][] stats = new byte[board.length][board[0].length];

        // 该下标 指向字符串的当前字符
        int tarIndex = 0;


        // 对矩阵进行遍历(DFS)，当匹配到第一个字符的时候，直接在该字符的上 下 左 右方向开始匹配，一旦撞墙，就回退到上个存档点，继续上下左右
        // 当字符下标指向最后一位时，说明串内字符都已经找到
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (findChar(board, i, j, tarIndex, wordChars, stats)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 递归形式 查找目标字符
     *
     * @param board 给定矩阵
     * @param x     当前x坐标
     * @param y     当前y坐标
     * @param t     待查找字符下标
     * @param wordChars  待查找字符串
     * @param stats 状态矩阵
     * @return
     */
    private boolean findChar(char[][] board, int x, int y, int t, char[] wordChars, byte[][] stats) {

        // 越界 / 匹配失败
        if (x < 0 || x == board.length || y < 0 || y == board[x].length || board[x][y] != wordChars[t])
            return false;

        // 该坐标的字符 已经在路径数组中
        if (stats[x][y] == 1) {
            return false;
        }

        // 已经匹配到串尾 则返回true
        if (t + 1 == wordChars.length) {
            stats[x][y] = 1;
            return true;
        }

        if (board[x][y] == wordChars[t]) {
            stats[x][y] = 1;
            // 朝着上下左右四个方向匹配
            if (findChar(board, x + 1, y, t + 1, wordChars, stats) || findChar(board, x - 1, y, t + 1, wordChars, stats)
                    || findChar(board, x, y + 1, t + 1, wordChars, stats) || findChar(board, x, y - 1, t + 1, wordChars, stats))
                return true;
            // 四个方向都撞墙了，说明这条路确实不通，那么本节点置0，返回false，回到上一个存档点
            stats[x][y]=0;
            return false;
        }
        return false;
    }
}


// 最终优化
// 取消了状态矩阵，而是直接在传入的矩阵中进行修改
class Solution3 {
    public boolean exist(char[][] board, String word) {
        char[] wordChars = word.toCharArray();


        // 该下标 指向字符串的当前字符
        int tarIndex = 0;


        // 对矩阵进行遍历(DFS)，当匹配到第一个字符的时候，直接在该字符的上 下 左 右方向开始匹配，一旦撞墙，就回退到上个存档点，继续上下左右
        // 当字符下标指向最后一位时，说明串内字符都已经找到
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (findChar(board, i, j, tarIndex, wordChars)){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 递归形式 查找目标字符
     *
     * @param board 给定矩阵
     * @param x     当前x坐标
     * @param y     当前y坐标
     * @param t     待查找字符下标
     * @param wordChars  待查找字符串
     * @return
     */
    private boolean findChar(char[][] board, int x, int y, int t, char[] wordChars) {

        // 越界 || 匹配失败
        // 匹配失败有两种情况:
        //     -
        if (x < 0 || x == board.length || y < 0 || y == board[x].length || board[x][y] != wordChars[t])
            return false;

        // 已经匹配到串尾 则直接返回true
        if (t + 1 == wordChars.length) {
            //board[x][y] = '\0';
            return true;
        }

        if (board[x][y] == wordChars[t]) {
            board[x][y] = '\0';
            // 朝着上下左右四个方向匹配
            if (findChar(board, x + 1, y, t + 1, wordChars) || findChar(board, x - 1, y, t + 1, wordChars)
                    || findChar(board, x, y + 1, t + 1, wordChars) || findChar(board, x, y - 1, t + 1, wordChars))
                return true;
            // 四个方向都撞墙了，说明这条路确实不通，那么恢复本节点原始数据，返回false，回到上一个存档点
            board[x][y]=wordChars[t];
            return false;
        }
        return false;
    }
}

public class 矩阵中的路径 {
    public static void main(String[] args) {
        /*char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(new 机器人移动格子().exist(board,"ABCCEDAF"));*/
        char[][] board = {{'C','A','A'},{'A','A','A'},{'B','C','D'}};
        System.out.println(new Solution().exist(board,"AAB"));
    }
}
