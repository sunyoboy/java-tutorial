package org.lieve.leetcode.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: sunyoboy@gmail.com
 * Date: 2/18/17
 * Time: 9:41 AM
 */

// Node: 1, tree: 1
// Node: 2, tree: 2
// Node: 3, tree: 5

public class Solution {
    public static void main(String[] args) {
       Solution solution = new Solution();
        /*
        for(int i=0; i<10; i++) {
            System.out.println(i + " " + solution.numTrees(i));
        }*/

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println("depth : " + solution.minDepth(root));;
    }

    public List<TreeNode> generateTrees(int n) {
        if(n == 0) {
            return new ArrayList<TreeNode>();
        }

        return helper(1, n);
    }

    public List<TreeNode> helper(int m, int n) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if(m > n) {
            result.add(null);
            return result;
        }

        for(int i =m ; i<=n; i++) {
            List<TreeNode> ls = helper(m, i-1);
            List<TreeNode> rs = helper(i+1, n);
            for(TreeNode l: ls) {
                for(TreeNode r: rs) {
                    TreeNode currentNode = new TreeNode(i);
                    currentNode.left = l;
                    currentNode.right = r;
                    result.add(currentNode);
                }
            }
        }
        return result;
    }

    public int numTrees(int n) {
        int[] count = new int[n+1];

        if(n ==0) {
            count[0] = 1;
        }

        if(n == 1) {
            count[1] = 1;
        }

        for(int i=2; i <=n; i++) {
            count[0] = 1;
            count[1] = 1;
            for(int j = 0; j<=i-1; j++) {
                count[i] = count[i] + count[j] * count[i-j-1];
            }
        }
        return count[n];
    }


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;
        }else if(p==null || q==null){
            return false;
        }

        if(p.val==q.val){
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }else{
            return false;
        }
    }

    public int minDepth(TreeNode root) {

        if(root == null) {
            return 0;
        }

        if(root != null)
            return minDepth(root.right) + 1;

        return 0;
    }

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public boolean isValidBST(TreeNode p, double min, double max) {
        if(p == null) {
            return true;
        }

        if(p.val <= min && p.val>=max) {
            return false;
        }

        return isValidBST(p.left, min, p.val) && isValidBST(p.right, p.val, max);
    }

    public int calculate(String s) {
        // delete white spaces
        s = s.replaceAll(" ", "");
        Stack<String> stack = new Stack<String>();
        char[] chars = s.toCharArray();

        StringBuilder sb = new StringBuilder();
        for(int i=0; i< chars.length; i++){
            if(chars[i] == ' ')
                continue;
            if(chars[i] >= '0' && chars[i] <= '9') {
                sb.append(chars[i]);
                if( i== chars.length -1 ) {
                    stack.push(sb.toString());
                }
            } else {
                if(sb.length() > 0) {
                    stack.push(sb.toString());
                    sb = new StringBuilder();
                }

            }

            i++;
        }
        return 0;
    }

}



