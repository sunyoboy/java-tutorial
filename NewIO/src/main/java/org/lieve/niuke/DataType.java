package org.lieve.niuke;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/18/17
 * Time: 5:00 PM
 */
public class DataType {
    public static void main(String[] args) {
        System.out.println(Byte.MIN_VALUE  + " " + Byte.MAX_VALUE);
        // Arrays.asList()
        String a = "";
        // a.toCharArray();
        String c = Arrays.toString(new char[] {'a', 'b', 'c', 'd'});
        System.out.println(c instanceof String);
        int[] array = {1,2,0};
        int[] array1 = {3, 4, -1, 1};
        DataType dataType = new DataType();
        dataType.firstMissingPositive(array);
    }

    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0) {
            return 1;
        }

        Arrays.sort(nums);
        int answer = 0;
        int start = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > 0) {
                start = i;
            }

            if(nums[i] > 0 && (nums[i] + 1 == nums[i+1])) {

            } else {
                answer = i;
            }
        }

        /*
        int max = nums[0];
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
            }
        }
        boolean[] bools = new boolean[max + 1];
        for(int i=0; i<nums.length; i++) {
            bools[nums[i]] = true;
        }
        int answer = 0;
        for(int j=1; j < bools.length ; j++) {
            if(bools[j] == false) {
                answer = j;
                break;
            }
        }*/
        return answer;
    }
}
