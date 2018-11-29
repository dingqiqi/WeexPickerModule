package com.lakala.appcomponent.pickermodule.mode;

import java.util.List;

/**
 * Created by dingqq on 2018/4/28.
 */

public class PickerChildren {

    private String value;
    private String text;
    private List<PickerChildren> childrens;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<PickerChildren> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<PickerChildren> childrens) {
        this.childrens = childrens;
    }
}
