package Algorithm;

public class QuickSortTemplate {
    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 1. 找到基准点的正确位置，并返回索引
            int pivotIndex = partition(arr, low, high);
            // 2. 递归排序左边
            quickSort(arr, low, pivotIndex - 1);
            // 3. 递归排序右边
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int left = low;
        int right = high;
        while (left < right) {
            // 右指针向左找：找到第一个比基准小的数
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            // 左指针向右找：找到第一个比基准大的数
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
        // 最后把基准数放到中间位置（此时left==right）
        swap(arr, low, left);
        return left;
    }

    public void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
