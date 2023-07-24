package com.wbw.algorithm.concur;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Semaphore;

@Slf4j
public class Fool {

    class Foo {


        private Semaphore s2;
        private Semaphore s3;

        public Foo() {

            s2 = new Semaphore(0);
            s3 = new Semaphore(0);

        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            s2.acquire();
            printFirst.run();


        }

        public void second(Runnable printSecond) throws InterruptedException {

            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
        }

        public void third(Runnable printThird) throws InterruptedException {

            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }


    }

    public static int ans(int a, int b) {

        int max = Math.max(a, b); // 最大值
        int min = Math.min(a, b); // 最小值
        int ans = max - min; // 最大值减去最小值
        if (ans == min) { // 差等于减数结束递归
            return ans;
        }
        return ans(ans, min);
    }


    public static void main(String[] args) {

        int a = 10;
        int b = 20;
        a = a ^ b;
        b = a ^ b; // b = a ^ b ^ b;
        a = a ^ b;
        log.info("a:{}, b:{}", a, b); // a:20, b:10

        int[] arr = new int[]{1, 1, 2, 2, 3, 3, 3};
        int num = arr[0];
        for (int i = 1; i < arr.length; i++) {
            num ^= arr[i];
        }
        log.info("num:{}", num); // num:3


        printOddTiemsNumber(new int[]{1, 1, 1, 2, 2, 2, 3, 3, 4, 4});


        System.out.println(calcStepSum(754));
    }


    public static void printOddTiemsNumber(int[] arr) {
        // new int[]{1, 1, 1, 2, 2, 2, 3, 3, 4, 4}
        int eor = 0;
        for (int i : arr) {
            eor ^= i;
        }
        int rightBit = eor ^ (-eor); // 1 ^ 2
        int one = 0;
        for (int i : arr) {
            if ((i & rightBit) == 0) {
                one ^= i;
            }
        }
        int another = eor ^ one;
        log.info("one:{}, another:{}", one, another);


        System.out.println(nearLeftNumber(new int[]{1, 3, 5, 6, 7, 8, 9}, 5));


    }


    public static int nearLeftNumber(int[] arr, int value) {

        int l = 0;
        int r = arr.length - 1;
        int ans = -1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return ans;
    }


    public static int calcStepSum(int stepSum) {
        int l = 0;
        int r = stepSum;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (stepSum(mid) == stepSum) {
                return mid;
            } else if (stepSum(mid) < stepSum) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public static int stepSum(int number) {
        int stepSum = 0;
        int i = 0;
        double tmp = number;
        while (tmp != 0) {
            tmp = tmp / Math.pow(10, i++);
            stepSum += tmp;
        }
        return stepSum;


    }

    @Test
    public void test() {

        int[] arr = new int[]{3, 3, 3};
        System.out.println(Arrays.stream(arr).map(e -> e + 2).sum());

        System.out.println(Arrays.toString(arr));

        System.out.println(minEatingSpeed(new int[]{1, 3, 6, 2, 8}, 7));

        System.out.println(Arrays.stream(arr).max().getAsInt());
    }


    public int minEatingSpeed(int[] banana, int hour) {
        // 香蕉最多堆的香蕉个数
        int maxSpeed = Arrays.stream(banana).max().getAsInt();
        // 最小速度从1开始，不能为0
        int minSpeed = 1;
        int ans = -1;
        while (minSpeed <= maxSpeed) {
            int midSpd = minSpeed + ((maxSpeed - minSpeed) >> 1);
            if (hours(banana, midSpd) <= hour) { // 吃的太快
                ans = midSpd;
                maxSpeed = midSpd - 1;
            } else { // 吃的太慢
                minSpeed = midSpd + 1;
            }
        }
        return ans;
    }

    public static int hours(int[] banana, int speed) {
        // e -> (e + speed - 1) / speed 向上进位
        return Arrays.stream(banana).map(e -> (e + speed - 1) / speed).sum();
    }

    @Test
    public void test2() {
        System.out.println(minEatingSpeed2(new int[]{805306368, 805306368, 805306368}, 1000000000));
    }

    public int minEatingSpeed2(int[] piles, int H) {
        // 速度最小的时候，耗时最长
        int left = 1;
        // 速度最大的时候，耗时最短
        int right = Arrays.stream(piles).max().getAsInt();

        int ans = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (calculateSum(piles, mid) > H) {
                // 耗时太多，说明速度太慢了，下一轮搜索区间是 [mid + 1..right]
                ans = mid;

                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 如果返回的小时数严格大于 H，就不符合题意
     *
     * @param piles
     * @param speed
     * @return 需要的小时数
     */
    private int calculateSum(int[] piles, int speed) {
        return Arrays.stream(piles).map(e -> (e + speed - 1) / speed).sum();
    }


    @Test
    public void test3() {
        System.out.println(isPalindrome("0P"));
    }

    public boolean isPalindrome(String s) {

        String str = s.toLowerCase();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetterOrDigit(str.charAt(i))) {
                stringBuffer.append(str.charAt(i));
            }
        }

        str = stringBuffer.toString();
        int l = 0, r = str.length() - 1;
        while (l <= r) {
            if (str.charAt(l) == str.charAt(r)) {
                r--;
                l++;
            } else {
                return false;
            }
        }

        return true;

    }


    @Test
    public void test4() {
        Character c = '8';

        System.out.println(Character.isAlphabetic(c));
        System.out.println(Character.digit(c, 2));

        System.out.println(Character.reverseBytes(c));

    }


    public int minSubArrayLen(int target, int[] nums) {

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if ((tmp += nums[j]) >= target) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }

        return ans;

    }


    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> target = new ArrayList<>();
        int len = nums.length;
        // 日常判断
        if (nums == null || len < 3) {
            return target;
        }
        // 从小到大排序
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            // 若当前元素和前一元素相同，后续处理场景与前一元素一致，跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 左指针为当前元素的后一位开始
            int l = i + 1;
            int r = len - 1;
            while (l < r) {
                // 求和
                int sum = nums[l] + nums[r] + nums[i];
                if (sum == 0) {
                    target.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                }
            }
        }
        return target;
    }


    @Test
    public void test5() {
        int i = removeDuplicates(new int[]{1, 1, 1, 2, 2, 3});
        System.out.println(i);

    }

    public int removeDuplicates(int[] nums) {

        int len = nums.length;
        int times = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i - 1]) {
                times++;
            } else {
                times = 1;
            }
            if (times == 3) {
                int tmp = i;
                while (tmp + 1 < len) {
                    nums[tmp] = nums[tmp + 1];
                    tmp++;
                }
                times--;
                len--;
                i--;
            }
        }

        return len;

    }

    @org.junit.jupiter.api.Test
    public void test6() {

        List<List<Integer>> ans = new ArrayList<>();
        int[] arr = new int[]{2, 3, 4, 8, 0};
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                for (int k = i; k < j; k++) {
                    List<Integer> list = new ArrayList<>();
                    for (int l = 0; l + k < arr.length; l++) {
                        list.add(arr[k + l]);
                    }
                    ans.add(list);
                }

            }

        }


        ans.forEach(e -> System.out.println(Arrays.toString(e.toArray())));
    }


    @org.junit.jupiter.api.Test
    public void test7() {
        System.out.println(getLucky("iiii", 2));

    }

    public int getLucky(String s, int k) {
        char[] chrs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : chrs) {
            // 因为a从1开始，所以加1
            sb.append(c - 'a' + 1);
        }
        return convert(sb.toString(), k);
    }

    // 按照转换的次数k进行处理
    public int convert(String str, int k) {
        String tmp = str;
        while (--k >= 0) { // 先减减后比较，确保k的值最小为0，不会为负数
            tmp = StringToNum(tmp);
        }
        return Integer.parseInt("".equals(tmp) ? "0" : tmp);
    }

    // 数字串转数字求和，返回结果值字符串
    public String StringToNum(String str) {

        int len = str.length();
        int sum = 0;
        while (--len >= 0) {
            char chr = str.charAt(len);
            sum += chr - '0';

        }
        return String.valueOf(sum);

    }


}
