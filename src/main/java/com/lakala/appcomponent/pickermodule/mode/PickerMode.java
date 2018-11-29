package com.lakala.appcomponent.pickermodule.mode;

import java.util.List;

/**
 * 弹框插件mode
 * Created by dingqq on 2018/4/25.
 */

public class PickerMode {

    //默认选中的值
    private String[] values;
    //picker 数据源
    private List<PickerChildren> items;
    //picker中文字的颜色
    private String textColor;
    //picker中选中item的背景色
    private String selectionColor;
    //确认按钮的文案
    private String confirmTitle;
    //取消按钮的文案
    private String cancelTitle;
    //确认按钮的文字颜色
    private String confirmTitleColor;
    //取消按钮的文字颜色
    private String cancelTitleColor;
    //对话框的标题
    private String title;
    //对话框标题的文字颜色
    private String titleColor;
    //对话框标题的背景色
    private String titleBackgroundColor;

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public List<PickerChildren> getItems() {
        return items;
    }

    public void setItems(List<PickerChildren> items) {
        this.items = items;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getSelectionColor() {
        return selectionColor;
    }

    public void setSelectionColor(String selectionColor) {
        this.selectionColor = selectionColor;
    }

    public String getConfirmTitle() {
        return confirmTitle;
    }

    public void setConfirmTitle(String confirmTitle) {
        this.confirmTitle = confirmTitle;
    }

    public String getCancelTitle() {
        return cancelTitle;
    }

    public void setCancelTitle(String cancelTitle) {
        this.cancelTitle = cancelTitle;
    }

    public String getConfirmTitleColor() {
        return confirmTitleColor;
    }

    public void setConfirmTitleColor(String confirmTitleColor) {
        this.confirmTitleColor = confirmTitleColor;
    }

    public String getCancelTitleColor() {
        return cancelTitleColor;
    }

    public void setCancelTitleColor(String cancelTitleColor) {
        this.cancelTitleColor = cancelTitleColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getTitleBackgroundColor() {
        return titleBackgroundColor;
    }

    public void setTitleBackgroundColor(String titleBackgroundColor) {
        this.titleBackgroundColor = titleBackgroundColor;
    }
}
