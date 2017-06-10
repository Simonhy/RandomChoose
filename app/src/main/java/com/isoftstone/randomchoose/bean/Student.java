package com.isoftstone.randomchoose.bean;

/**
 * Created by yonghuangl on 2017/6/9.
 */
public class Student {
    private String iconUrl;//头像地址
    private String name;//用户名称
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
