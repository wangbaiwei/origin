package 牛客网;

import java.util.Scanner;

public class 合唱队 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// 注意 hasNext 和 hasNextLine 的区别
		while (in.hasNextInt()) { // 注意 while 处理多个 case
			int n = in.nextInt();

			int[] left = new int[n];
			int[] right = new int[n];

			// 读取输入数据
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = in.nextInt();
			}

			// 每个数字左侧递增序列长度
			for (int i = 0; i < n; i++) {
				left[i] = 1;
				for (int j = 0; j < i; j++) {
					/** j < i */
					if (arr[i] > arr[j]) { /** arr[i] > arr[j] */
						left[i] = Math.max(left[j] + 1, left[i]);
					}
				}
			}

			// 每个数右侧递减序列长度
			for (int i = n - 1; i >= 0; i--) {
				right[i] = 1;
				for (int j = n - 1; j > i; j--) {
					/** j > i */
					if (arr[i] > arr[j]) { /** arr[i] > arr[j] */
						right[i] = Math.max(right[j] + 1, right[i]);
					}
				}

			}

			// 每个数左侧递增序列+右侧递减序列
			int maxLen = 0;
			for (int i = 0; i < n; i++) {
				int tmp = left[i] + right[i];
				if (tmp > maxLen) {
					maxLen = tmp;
				}
			}
			maxLen -= 1;

			// 原序列长度减去上一步的最长序列
			System.out.println(n - maxLen);
		}
	}

}
