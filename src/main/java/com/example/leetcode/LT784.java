package com.example.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Add class description here.
 *
 * @author Ren Qiang
 */
public class LT784 {
    public List<String> letterCasePermutation(String S) {
        return letterCasePermutationHelper(S, Collections.singletonList(""));
    }

    private List<String> letterCasePermutationHelper(String S, List<String> permutations) {
        if (S.isEmpty()) {
            return permutations;
        }
        List<String> curPerms = new ArrayList<>();
        char c = S.charAt(S.length() - 1);
        if ((c >= 'a' && c <= 'z' )) {
            if (permutations.size() > 0) {
                for (String p : permutations) {
                    curPerms.add(String.valueOf(c + p));
                    curPerms.add(String.valueOf((char)(c + ('A' - 'a')) + p));
                }
            } else {
                curPerms.add(String.valueOf(c));
                curPerms.add(String.valueOf((char)(c + ('A' - 'a'))));
            }
        } else if (c >= 'A' && c <= 'Z') {
            if (permutations.size() > 0) {
                for (String p : permutations) {
                    curPerms.add(String.valueOf(c + p));
                    curPerms.add(String.valueOf((char)(c + ('a' - 'A')) + p));
                }
            } else {
                curPerms.add(String.valueOf(c));
                curPerms.add(String.valueOf((char)(c + ('a' - 'A'))));
            }
        } else {
            if (permutations.size() > 0) {
                for (String p : permutations) {
                    curPerms.add(String.valueOf(c + p));
                }
            } else {
                curPerms.add(String.valueOf(c));
            }
        }
        return letterCasePermutationHelper(S.substring(0, S.length() - 1), curPerms);
    }

    public static void main(String[] args) {
        LT784 answer = new LT784();
        System.out.println(answer.letterCasePermutation(""));
    }
}
