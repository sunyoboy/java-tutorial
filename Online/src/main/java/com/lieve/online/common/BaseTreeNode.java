package com.lieve.online.common;

import com.lieve.online.base.common.BaseBean;

public abstract class BaseTreeNode extends BaseBean {

    private int no;

    private int parentNo;

    private int layer;

    private String name;

    private String fullName;

    private boolean hasChildren = false;

    public abstract int getNo();

    public void setNo(int no) {

        this.no = no;
    }

    public abstract int getParentNo();

    public void setParentNo(int parentNo) {

        this.parentNo = parentNo;
    }

    public abstract String getName();

    public void setName(String name) {

        this.name = name;
    }

    public int getLayer() {

        return this.layer;
    }

    public void setLayer(int layer) {

        this.layer = layer;
    }

    public String getFullName() {

        return fullName;
    }

    public void setFullName(String fullName) {

        this.fullName = fullName;
    }

    public boolean isHasChildren() {

        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {

        this.hasChildren = hasChildren;
    }

}
