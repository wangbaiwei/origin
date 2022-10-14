package ţ����;

import java.util.Scanner;

public class �ϳ��� {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// ע�� hasNext �� hasNextLine ������
		while (in.hasNextInt()) { // ע�� while ������ case
			int n = in.nextInt();

			int[] left = new int[n];
			int[] right = new int[n];

			// ��ȡ��������
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = in.nextInt();
			}

			// ÿ���������������г���
			for (int i = 0; i < n; i++) {
				left[i] = 1;
				for (int j = 0; j < i; j++) {
					/** j < i */
					if (arr[i] > arr[j]) { /** arr[i] > arr[j] */
						left[i] = Math.max(left[j] + 1, left[i]);
					}
				}
			}

			// ÿ�����Ҳ�ݼ����г���
			for (int i = n - 1; i >= 0; i--) {
				right[i] = 1;
				for (int j = n - 1; j > i; j--) {
					/** j > i */
					if (arr[i] > arr[j]) { /** arr[i] > arr[j] */
						right[i] = Math.max(right[j] + 1, right[i]);
					}
				}

			}

			// ÿ��������������+�Ҳ�ݼ�����
			int maxLen = 0;
			for (int i = 0; i < n; i++) {
				int tmp = left[i] + right[i];
				if (tmp > maxLen) {
					maxLen = tmp;
				}
			}
			maxLen -= 1;

			// ԭ���г��ȼ�ȥ��һ���������
			System.out.println(n - maxLen);
		}
	}

}
