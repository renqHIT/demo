package com.example.leetcode;

import java.util.Arrays;

/**
 * Add class description here.
 *
 * @author Ren Qiang
 */
public class LT786 {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int[] result = new int[2];
        // 生成A的分数全排列
        // 排序
        // 输出第k小的分数
        result[0] = 1;
        result[1] = 2;
        return result;
    }

    public static void main(String[] args) {
        LT786 answer = new LT786();
        int[] case1 = new int[]{1, 2, 3, 5};
        System.out.println(Arrays.toString(answer.kthSmallestPrimeFraction(case1, 3)));
        int[] case2 = new int[]{1, 7};
        System.out.println(Arrays.toString(answer.kthSmallestPrimeFraction(case2, 1)));
    }
}
