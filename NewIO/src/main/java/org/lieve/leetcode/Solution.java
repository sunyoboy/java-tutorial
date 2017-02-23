package org.lieve.leetcode;

import org.junit.Assert;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/17/17
 * Time: 10:45 PM
 */
public class Solution {
    public String shortestPalindrome(String s) {
        if (isPalindrome(s))
            return s;
        StringBuilder sb = new StringBuilder(s);
        int len = s.length();
        for(int i = len/2 - 1, j=1; i<len && j<len-i; j++) {
            boolean flag = false; // 是否增加字符,
            if(i-j < 0) {
                sb.insert(0, sb.charAt(i+j));
                i++;
                len++;
                flag = true;
                if(isPalindrome(sb.toString())) {
                    return sb.toString();
                }

            }
            // 如果增加, i-j, i+j都加1
            //
            int index1 = i-j;// flag == false ? (i-j) : (i-j) + 1;
            int index2 = i+j;//flag == false ? (i+j) : (i+j) + 1;
            flag = false;
            if(sb.charAt(index1) != sb.charAt(index2) || (sb.charAt(i) != sb.charAt(i+1) )) {
                System.out.println("not equal");
                i--;
                j=0;
            } else {
                System.out.println("equal");
            }
        }
        return sb.toString();
    }

    public int calculate(String s) {
        int i = 0;
        Stack<String> stack = new Stack<String>();
        while(i<s.length()) {
            if(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                stack.push(s.substring(i,i+1));
            } else {

            }

            i++;
        }
        return 0;
    }

    public String shortestPalindrome2(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (j >= 0) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
            }
            j--;
        }

        if (i == s.length())
            return s;

        String suffix = s.substring(i);
        String prefix = new StringBuilder(suffix).reverse().toString();
        String mid = shortestPalindrome2(s.substring(0, i));
        return prefix + mid + suffix;
    }

    public boolean isPalindrome(String s) {
        for(int i=0; i<s.length()/2; i++) {
            if(s.charAt(i) != s.charAt(s.length()-1-i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String a1 = "aacecaaa";
        String a2 = "aaacecaaa";
        String b1 = "abcd";
        String b2 = "dcbabcd";
        String c1 = "abbacd";
        String c2 = "dcabbacd";
        Solution solution = new Solution();
        Assert.assertFalse(solution.isPalindrome(a1));
        Assert.assertTrue(solution.isPalindrome(a2));
        Assert.assertFalse(solution.isPalindrome(b1));
        Assert.assertTrue(solution.isPalindrome(b2));

        // Assert.assertEquals("equal", a2, solution.shortestPalindrome(a1));
        // Assert.assertEquals("equal", b2, solution.shortestPalindrome(b1));
        Assert.assertEquals("equal", c2, solution.shortestPalindrome2(c1));
    }
}
