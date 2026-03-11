package Algorithm;

public class FindFirstMissingPositive {
    // 问题：给一个全是整数的数组，找到第一个缺失的整数(给定一个含n个整数的数组，找出其中未出现的最小正整数)
    // 解决思路：用hash，数组的下表作为key，表示第i-1个位置，内容应该存放数值i，
    // 假如有1到5个座位，编号从0到4，有5个人，假如他们身上贴有编号1,2,3,4,5,编号1应该做到第0个位置
    // 编号2应该坐到第1个位置，依此类推；假如这5个人是乱坐的，比如3，2，1，5，4
    // 那么就应该调整位置，第0个位置坐的人编号是3，3按照规则应该做到第2个位置，那么就应该交换第0个位置上编号为3的人和第2个位置上坐的编号为1的人。
    // 继续调整第1个座位的上编号为2的人，它应该做到第1个位置上，那么就交换第1个位置上做的编号为2的人和第1个位置上坐的编号为2的人。
    // 以此类推。
    public int findFirstMissingPositive(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        // 交换元素num到对应位置num - 1
        for (int i = 0; i < arr.length; i++) {
            // 如果arr[i]在1到n之间，那么就应该放到正确的索引位置：arr[i]-1
            if (arr[i] >= 1 && arr[i] <= arr.length) {
                int temp = arr[i];
                arr[i] = arr[arr[i]-1];
                arr[temp-1] = temp;
            }
        }

        // 寻找第一个位置i，arr[i] != i+1
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i+1) {
                return i+1;
            }
        }
        return arr.length+1;
    }

    public static void main(String[] args) {
        FindFirstMissingPositive f = new FindFirstMissingPositive();
        // 测试用例
        // input:[1,2],expect 3
//        System.out.println(f.findFirstMissingPositive(new int[]{1,2}) == 3);
        // input:[-3,-2],expect 1
//        System.out.println(f.findFirstMissingPositive(new int[]{-3,-2}) == 1);

        // input:[100, -3,2],expect 1
//        System.out.println(f.findFirstMissingPositive(new int[]{-3,-2}) == 1);

        // input:[-3,1,2,3],expect 4
        System.out.println(f.findFirstMissingPositive(new int[]{-3,1,2,3}) == 4);

        // input:[100, 100, 100],expect 1
        System.out.println(f.findFirstMissingPositive(new int[]{100, 100, 100}) == 1);

        // input:[1,-2,4,6],expect 2
        System.out.println(f.findFirstMissingPositive(new int[]{1,-2,4,6}) == 2);

    }
}
