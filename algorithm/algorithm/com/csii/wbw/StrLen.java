package com.csii.wbw;

import java.util.Scanner;

public class StrLen {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String[] split = str.split("\\s+", -1);
        System.out.println(split[split.length - 1].length());
    }
}
