package 牛客网;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 给定一些同学的信息（名字，成绩）序列，请你将他们的信息按照成绩从高到低或从低到高的排列,相同成绩 都按先录入排列在前的规则处理。 输入： 3 0 fang
 * 90 yang 50 ning 70
 * 
 * 输出： fang 90 ning 70 yang 50
 */
public class 成绩排序 {

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

			// 创建n * 2数组
			int[][] grade = new int[totalLine][2];
			// 保存 序号+名字
			Map<Integer, String> map = new HashMap<>();
			for (int i = 0; i < totalLine; i++) {
				String[] line = input.nextLine().split("\\s");
				grade[i][0] = i;
				grade[i][1] = Integer.parseInt(line[1]);
				map.put(i, line[0]);
			}

			// 二位数组按第二列排序
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
