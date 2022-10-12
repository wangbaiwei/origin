package ţ����;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * ����һЩͬѧ����Ϣ�����֣��ɼ������У����㽫���ǵ���Ϣ���ճɼ��Ӹߵ��ͻ�ӵ͵��ߵ�����,��ͬ�ɼ� ������¼��������ǰ�Ĺ����� ���룺 3 0 fang
 * 90 yang 50 ning 70
 * 
 * ����� fang 90 ning 70 yang 50
 */
public class �ɼ����� {

	public static void main(String[] args) {
		int[][] arr = new int[][] {{1, 6}, {2, 8}, {3, 9}};
		Arrays.parallelSort(arr, (o1, o2) -> {
			return o2[1] - o1[1];
		});
		
		Arrays.asList(arr).forEach(e -> {
			System.out.println(e[0] + " " + e[1]);
		});

	}

	public void nameAndGradeSort() {
		Scanner input = new Scanner(System.in);
		while (input.hasNextLine()) {

			int totalLine = Integer.parseInt(input.nextLine());
			int flag = Integer.parseInt(input.nextLine());

			// ����n * 2����
			int[][] grade = new int[totalLine][2];
			// ���� ���+����
			Map<Integer, String> map = new HashMap<>();
			for (int i = 0; i < totalLine; i++) {
				String[] line = input.nextLine().split("\\s");
				grade[i][0] = i;
				grade[i][1] = Integer.parseInt(line[1]);
				map.put(i, line[0]);
			}

			// ��λ���鰴�ڶ�������
			Arrays.sort(grade, (o1, o2) -> {
				if (flag == 0) {
					return o2[1] - o1[1];
				} else {
					return o1[1] - o2[1];
				}
			});

			for (int i = 0; i < totalLine; i++) {
				System.out.println(map.get(grade[i][0]) + " " + grade[i][1]);
			}
		}
	}

}
