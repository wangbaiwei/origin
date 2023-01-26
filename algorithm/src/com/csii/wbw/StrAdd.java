package com.csii.wbw;

import java.util.Scanner;
import java.util.Stack;

public class StrAdd {


    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        String str = input.nextLine();


        String test = "heLlo world";
        System.out.println( test.substring(2));

        System.out.println(test.replace("l", ""));

        char c = '5';
        System.out.println((c -'0') * 10);

        System.out.println(Integer.parseInt("AA", 16));

        getPrime(180);


    }

    public static void getPrime(long num) {

        for (int i = 2; i <= num; i++) {
            if (num % i == 0) {
                System.out.print(i + " ");
                getPrime(num / i);
                break;
            }

            if (i == num) {
                System.out.print(i + "");
            }

        }

    }

    public int priority(char c) {
        switch (c) {
            case '+' :
                 return 0;
            case '-':
                return 0;
            case '*':
                return 1;
            case '/':
                return 1;
            default:
                return -1;
        }
    }
}
