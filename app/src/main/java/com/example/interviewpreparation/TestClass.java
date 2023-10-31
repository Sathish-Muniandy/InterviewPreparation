package com.example.interviewpreparation;

public class TestClass {



    public static void main(String s[]) {
        int[] num = new int[5];
        int[] num1 = {1,2,3};
        char c = 'g';
        char[] c1 = {'g','h'};
        char[] c2 = new char[2];
        c2[0] = 'j';
        c2[9] = 'g';
        num[0] = 5;
        num[1] = num1[0];
        num1[0] = num[0];
    }

}
