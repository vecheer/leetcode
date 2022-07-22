package my.bak.L4_robot_moving;

class Solution {
    public int movingCount(int m, int n, int k) {
        // 访问标记
        // 如果已经访问过，就置为1
        // 如果未访问过，就置为0
        // 广度搜完之后，数一下有多少个1，就是最终的格子总数
        byte[][] visited = new byte[m][n];

        /*
        // 这个不需要双重循环遍历，因为某一个坐标不可达时，该坐标后续延伸的节点一定都是不可达的
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                bfs(m, n, k, i, j, visited);
            }
        }*/
        bfs(m, n, k, 0, 0, visited);

        
        
        int result = 0;
        for (byte[] bytes : visited) {
            for (byte aByte : bytes) {
                result += aByte;
            }
        }
        

        return result;
    }

    private void bfs(int m, int n, int k, int x, int y, byte[][] visited) {
        // 如果 （越界 || 访问过）  说明已经不可达了！
        // 则直接返回 0
        if (x < 0 || x == m || y < 0 || y == n || visited[x][y] == 1) {
            return;
        }
        
        
        // 解析字符的做法
        // 如果不满足位数之和小于 k
        // 则直接返回 0
        int sum = 0;
        for (char c : (x + "" + y).toCharArray()) {
            sum = sum + ((int) c - 48);  // ASCII 码表中 ，数字字符比其ASCII码小 48
        }

        if (sum > k) {
            return;
        }
        
        
        if (visited[x][y] == 0) {
            visited[x][y] = 1;
            // 实际上只需要向右 向下遍历即可，因为左上一定之前被遍历过了
            bfs(m, n, k, x + 1, y, visited);
            bfs(m, n, k, x, y + 1, visited);
            bfs(m, n, k, x - 1, y, visited);
            bfs(m, n, k, x, y - 1, visited);
        }

    }
}

// BFS直接做   优化版
//     - 优化了BFS逻辑，只向右下方向搜索，左上的不在进行搜索
//     - 优化了取位数进行运算的逻辑，使用除以10 + 对10取模的方式，获取每个位
class Solution2 {
    public int movingCount(int m, int n, int k) {
        // 访问标记
        // 如果已经访问过，就置为1
        // 如果未访问过，就置为0
        // 广度搜完之后，数一下有多少个1，就是最终的格子总数
        byte[][] visited = new byte[m][n];

        bfs(m, n, k, 0, 0, visited);



        int result = 0;
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[i].length; j++) {
                result += visited[i][j];
            }
        }


        return result;
    }

    private void bfs(int m, int n, int k, int x, int y, byte[][] visited) {
        // 如果 （越界 || 访问过）  说明已经不可达了！
        // 则直接返回 0
        if (x < 0 || x == m || y < 0 || y == n || visited[x][y] == 1) {
            return;
        }

        int sum = calculate(x) + calculate(y);

        if (sum > k) {
            return;
        }


        if (visited[x][y] == 0) {
            visited[x][y] = 1;
            // 只需要向右 向下遍历即可，因为左上一定之前被遍历过了
            bfs(m, n, k, x + 1, y, visited);
            bfs(m, n, k, x, y + 1, visited);
        }

    }

    private int calculate(int n){
        int sum = 0;
        while (n!=0){
            sum += n%10;
            n=n/10;
        }
        return sum;
    }
}


public class 机器人移动格子 {

    public static void main(String[] args) {
      /*  int x = 11;
        int y = 22;

        int sum = 0;
        for (char c : (x + "" + y).toCharArray()) {
            sum = sum + ((int) c - 48);  // ASCII 码表中 ，数字字符比其ASCII码小 48
        }
        System.out.println(sum);*/

        System.out.println(new Solution().movingCount(12,12,10));
    }
}
