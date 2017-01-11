package com.lieve.online.common;

import com.lieve.online.base.common.BaseBean;

public class TreeNode extends BaseBean {

    private int no;

    private int parentNo;

    private int layer;

    private String name;

    private String fullName;

    private boolean hasChildren = false;

    private Object node;

    public TreeNode(int no, int parentNo, int layer, String name,
                    String fullName, Object node) {

        super();
        this.no = no;
        this.parentNo = parentNo;
        this.layer = layer;
        this.name = name;
        this.fullName = fullName;
        this.node = node;
    }

    public int getNo() {

        return no;
    }

    public void setNo(int no) {

        this.no = no;
    }

    public int getParentNo() {

        return parentNo;
    }

    public void setParentNo(int parentNo) {

        this.parentNo = parentNo;
    }

    public int getLayer() {

        return layer;
    }

    public void setLayer(int layer) {

        this.layer = layer;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getFullName() {

        return fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public Object getNode() {

        return node;
    }

    public void setNode(Object node) {

        this.node = node;
    }

    public TreeNode() {

        super();
    }

    public boolean isHasChildren() {

        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {

        this.hasChildren = hasChildren;
    }

}
