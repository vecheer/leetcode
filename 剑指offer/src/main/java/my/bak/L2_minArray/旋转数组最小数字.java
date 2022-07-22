package my.bak.L2_minArray;

// 暴力破解
class Solution1 {
    public int minArray(int[] numbers) {
        for(int i=0;i<numbers.length;i++){
            if(i+1==numbers.length)
                break;
            if(numbers[i] <= numbers[i+1])
                continue;
            else
                return numbers[i+1];
        }
        return numbers[0];
    }
}

// 分治
// 类似于二分查找的思想
// 特殊的地方在于：
// 旋转数组的图像类似于：
//
//             ____
//     _______/    |
//    /            |
//                 |   _____
//                 | /
class Solution2 {
    public int minArray(int[] numbers) {

        int start = 0;
        int end = numbers.length-1;
        int mid = (end - start)/2;

        int min = numbers[0];


        // mid < end      mid 到 end 之间的全部丢弃
        // mid > end      start 到 mid 之间的全部丢弃
        while(true){
            if(end == start){
                min = numbers[end];
                break;
            }

            if((end - start)==1){
                if (numbers[end] > numbers[start])
                    return numbers[start];
                else
                    return numbers[end];
            }else {
                mid = start + (end - start)/2;
            }

            if(numbers[mid] < numbers[end]) {
                end = mid;
            }
            else if(numbers[mid] > numbers[end]){
                start = mid;
            }
            else{   // 这种情况下，只能缩小 end 边界，继续循环
                end = end-1;
            }

        }


        return min;
    }

}


// 二分法 改进版
class Solution3 {
    public int minArray(int[] numbers) {

        int start = 0;
        int end = numbers.length-1;


        // mid < end      mid 到 end 之间的全部丢弃
        // mid > end      start 到 mid 之间的全部丢弃
        while(start < end){ // 将判断条件写到while中，避免使用while(true) + break
            int mid = (end + start)/2; // 每次重新生成mid
            if(numbers[mid] < numbers[end]) {
                end = mid;
            }
            else if(numbers[mid] > numbers[end]){
                start = mid + 1;  // 如果 mid 大于 end，说明最小值一定在mid右边，而且 mid 本身一定不会是最小值
            }
            else{   // 这种情况下，只能缩小 end 边界，继续循环
                end = end-1;
            }
        }

        return numbers[start];
    }

}


public class 旋转数组最小数字 {
    public static void main(String[] args) {
        int[] numbers = new int[]{2,2,2,0,1};
        System.out.println(new Solution3().minArray(numbers));
    }
}
