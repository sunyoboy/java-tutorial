package org.lieve;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/14/17
 * Time: 9:50 PM
 */
public class Solution {

    private static Logger logger = Logger.getLogger(Solution.class.getName());
    public int integerReplacement(int n) {
        int step = 0;
        while (n != 1) {
            if ((n&1) == 0) {
                n >>>= 1;
            } else if (n == 3 || Integer.bitCount(n+1) > Integer.bitCount(n-1)) {
                n--;
            } else {
                n++;
            }
            step++;
        }
        return step;
    }

    public static void main(String[] args) {
        logger.info("hello");
        System.out.println(Integer.bitCount(65535));
        System.out.println(new Solution().integerReplacement(65535));
    }
}