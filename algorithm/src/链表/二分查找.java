package ����;

public class ���ֲ��� {
	
	
	public static void main(String[] args) {
		
		int[] arr = new int[] {1, 2, 3, 4, 5, 6};
		System.out.println(binarySearch(arr, 2));
	}
	
	
	public static int binarySearch(int[] arr, int target) {
		int l = 0;
		int r = arr.length;
		int mid;
		while (l <= r) { // ȡ�Ⱥţ������鳤��Ϊż�����ܹ�ȡ���м���λ
			mid = (l + r) / 2;
			if (arr[mid] == target) {
				return mid + 1; // ����λ�ô�0��ʼ
			} else if (arr[mid] < target) {
				l = mid + 1; // Ŀ��������λ��mid���Ҳ�
			} else if (arr[mid] > target) { // Ŀ����������mid�����
				r = mid - 1;
			}
		}
		return -1; // û���ҵ�
	}
}


