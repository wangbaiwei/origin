package ??????;

public class ??????? {
	
	
	public static void main(String[] args) {
		
		int[] arr = new int[] {1, 2, 3, 4, 5, 6};
		System.out.println(binarySearch(arr, 2));
	}
	
	
	public static int binarySearch(int[] arr, int target) {
		int l = 0;
		int r = arr.length;
		int mid;
		while (l <= r) { // ??????????�A???????????????��???��
			mid = (l + r) / 2;
			if (arr[mid] == target) {
				return mid + 1; // ????��???0???
			} else if (arr[mid] < target) {
				l = mid + 1; // ?????????��??mid?????
			} else if (arr[mid] > target) { // ???????????mid?????
				r = mid - 1;
			}
		}
		return -1; // ??????
	}
}

