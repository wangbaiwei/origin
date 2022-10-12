package 链表;

public class 二分查找 {
	
	
	public static void main(String[] args) {
		
		int[] arr = new int[] {1, 2, 3, 4, 5, 6};
		System.out.println(binarySearch(arr, 2));
	}
	
	
	public static int binarySearch(int[] arr, int target) {
		int l = 0;
		int r = arr.length;
		int mid;
		while (l <= r) { // 取等号，若数组长度为偶数，能够取到中间两位
			mid = (l + r) / 2;
			if (arr[mid] == target) {
				return mid + 1; // 数组位置从0开始
			} else if (arr[mid] < target) {
				l = mid + 1; // 目标数索引位在mid的右侧
			} else if (arr[mid] > target) { // 目标数索引在mid的左侧
				r = mid - 1;
			}
		}
		return -1; // 没有找到
	}
}


