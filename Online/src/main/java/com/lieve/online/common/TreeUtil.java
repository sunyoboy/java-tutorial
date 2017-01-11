package com.lieve.online.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DD240 on 2016/2/29.
 */
public class TreeUtil {
    private String separator = "&gt;&gt;" ;

    public String getSeparator() {

        return separator ;
    }

    public TreeUtil() {

        super() ;
        // TODO Auto-generated constructor stub
    }

    public TreeUtil(String separator) {

        super() ;
        this.separator = separator ;
    }

    public void setSeparator(String separator) {

        this.separator = separator ;
    }

    public List createTree(List<TreeNode> list) {

        return createTree(list, 0, null, 0) ;
    }

    public List createTree(List<TreeNode> list, int topNo) {

        TreeNode treeNode = null ;

        List result = new ArrayList() ;

        List temp = null ;

        String topFullName = null ;

        for (int i = 0, j = list == null ? 0 : list.size(); i < j; i++) {
            treeNode = list.get(i) ;

            if (treeNode.getNo() == topNo) {
                if (Util.isNull(treeNode.getFullName())) {
                    topFullName = treeNode.getName() ;
                } else {
                    topFullName = treeNode.getFullName() ;
                }
            }
        }

        return createTree(list, topNo, topFullName, 0) ;

    }

    public List createTree(List<TreeNode> list, int topNo, String topFullName, int layer) {

        TreeNode treeNode = null ;

        List result = new ArrayList() ;

        List temp = null ;

        String fullName = null ;

        for (int i = 0, j = list == null ? 0 : list.size(); i < j; i++) {
            treeNode = list.get(i) ;

            if (treeNode.getParentNo() == topNo) {
                treeNode.setLayer(layer + 1) ;// =layer+1;

                if (Util.isNull(topFullName)) {
                    fullName = treeNode.getName() ;
                } else {
                    fullName = topFullName + ">>" + treeNode.getName() ;
                }

                treeNode.setFullName(fullName) ;// .fullName=fullName;

                temp = createTree(list, treeNode.getNo(), fullName, treeNode.getLayer()) ;

                treeNode.setHasChildren( temp != null && temp.size() > 0 );

                result.add(treeNode) ;

                if (temp != null && temp.size() > 0) {
                    result.addAll(temp) ;
                }

            }
        }

        return result ;
    }

    public List getTree(List<BaseTreeNode> list) {

        return getTree(list, 0, null, 0) ;
    }

    public List getTree(List<BaseTreeNode> list, int topNo) {

        BaseTreeNode treeNode = null ;

        List result = new ArrayList() ;

        List temp = null ;

        String topFullName = null ;

        for (int i = 0, j = list == null ? 0 : list.size(); i < j; i++) {
            treeNode = list.get(i) ;

            if (treeNode.getNo() == topNo) {
                if (Util.isNull(treeNode.getFullName())) {
                    topFullName = treeNode.getName() ;
                } else {
                    topFullName = treeNode.getFullName() ;
                }
            }
        }

        return getTree(list, topNo, topFullName, 0) ;

    }
    public List getTree(List<BaseTreeNode> list, int topNo, String topFullName, int layer) {


        BaseTreeNode treeNode = null ;

        List result = new ArrayList() ;

        List temp = null ;

        String fullName = null ;

        for (int i = 0, j = list == null ? 0 : list.size(); i < j; i++) {

            treeNode = list.get(i) ;

            if (treeNode.getParentNo() == topNo) {
                treeNode.setLayer(layer + 1) ;// =layer+1;

                if (Util.isNull(topFullName)) {
                    fullName = treeNode.getName() ;
                } else {
                    fullName = topFullName + ">>" + treeNode.getName() ;
                }

                treeNode.setFullName(fullName) ;// .fullName=fullName;

                temp = getTree(list, treeNode.getNo(), fullName, treeNode.getLayer()) ;

                treeNode.setHasChildren( temp != null && temp.size() > 0 );

                result.add(treeNode) ;

                if (temp != null && temp.size() > 0) {
                    result.addAll(temp) ;
                }

            }
        }

        return result ;

    }
}

