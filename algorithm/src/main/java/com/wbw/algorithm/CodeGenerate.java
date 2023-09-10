package com.wbw.algorithm;

import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

@Slf4j
public class CodeGenerate {

    private static final int LEN = 7;
    private static final String PREFIX = "WZGL";
    private static final String PLACE = "00000";
    private static final Pattern CODE_REGEX = Pattern.compile(PREFIX + "[0-9]{8}([0-9]{" + LEN + "}|[A-Z]{1," + (LEN - 1) + "}[0-9]{1," + (LEN - 1) + "})");
    private volatile String letter = "";
    private AtomicInteger letterLen = new AtomicInteger(0);
    private volatile int num;
    private AtomicInteger numLen = new AtomicInteger(5);

    public CodeGenerate() {
    }

    // 当前日期
    public String getNowDate() {
        return DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now());
    }

    public synchronized String next(String code) {
        check(code); // 检查编号格式
        split(code); // 拆分
        if ("".equals(this.letter)) {
            if (this.num + 1 <= this.numMax()) {
                String curNum = PLACE + (this.num + 1);
                return PREFIX + getNowDate() + curNum.substring(curNum.length() - LEN);
            } else {
                this.numLen.getAndDecrement();
                String curNum = PLACE + 1;
                return PREFIX + getNowDate() + "A" + curNum.substring(curNum.length() - this.numLen.get());
            }

        } else {
            if (this.num + 1 <= numMax()) {
                String curNum = PLACE + (this.num + 1);
                return PREFIX + getNowDate() + this.letter + curNum.substring(curNum.length() - this.numLen.get());
            } else {
                String curNum = PLACE + 1;
                addLetter();
                return PREFIX + getNowDate() + this.letter + curNum.substring(curNum.length() - this.numLen.get());
            }
        }
    }

    // 校验当前编号格式
    public boolean check(String code) {

        int len = LEN + PREFIX.length() + 8;
        if (!CODE_REGEX.matcher(code).matches() || (code.length() != len)) {
            log.info("编号格式应为【{}】+【{}】+【{}】", PREFIX, getNowDate(), LEN + "位大写字母和数字");
            throw new RuntimeException("当前编码[" + (len == code.length() ? "格式" : "长度[应为" + len + ", 实际为" + code.length() + "]") + "]错误！" + code);
        }
        String suffix = code.substring(code.length() - LEN);
        if (!suffix.matches("([A-Z]{1}\\d+|Z+\\d+|Z+[A-Y]{1}+\\d+)")) {
            throw new RuntimeException("后缀格式错误：" + suffix);
        }
        return true;
    }

    // 将编号从尾部截取LEN长度，拆分为字符串和数字
    public void split(String code) {
        String suffix = code.substring(code.length() - LEN);
        if (suffix.matches("^[A-Z].*")) {
            suffix = suffix.replaceFirst("([A-Z]*)(\\d*)", "$1-$2");
        }
        String[] split = suffix.split("-");
        if (split.length > 1) {
            this.letter = split[0];
            this.num = Integer.parseInt(split[1]);
            this.letterLen.set(split[0].length());
            this.numLen.set(split[1].length());
        } else {
            this.letter = "";
            this.num = Integer.parseInt(split[0]);
            this.letterLen.set(0);
            this.numLen.set(split[0].length());
        }
    }

    // 获取当前长度数字的最大值
    public int numMax() {
        int ans = 0;
        int index = 0;
        while (index < this.numLen.get()) {
            ans += 9 * Math.pow(10, index);
            index++;
        }
        return ans;
    }

    // 处理字母
    public void addLetter() {
        if (this.letter == "") {
            this.letter = "A";
        } else {
            char endLetter = this.letter.charAt(letterLen.get() - 1);
            if (endLetter - 'A' >= 25) {
                this.letter += "A";
                this.numLen.getAndDecrement();
            } else {
                this.letter = this.letter.substring(0, letterLen.get() - 1) + (char) (endLetter + 1);
            }
        }
    }

    public static void main(String[] args) {
        CodeGenerate codeUtil = new CodeGenerate();
        String next = codeUtil.next("WZGL20220505ZZZ9900");
        System.out.println(next);
        int i = 0;
        while (i < 10000) {
            next = codeUtil.next(next);
            System.out.print(next + "  ");
            if (i % 10 == 0) {
                System.out.println();
            }
            i++;
        }
    }
}
