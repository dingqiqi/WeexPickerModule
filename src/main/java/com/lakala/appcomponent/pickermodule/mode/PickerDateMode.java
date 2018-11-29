package com.lakala.appcomponent.pickermodule.mode;

/**
 * Created by dingqq on 2018/4/25.
 */

public class PickerDateMode{

    //默认选中的选项
    private int[] indexs;
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

    //date picker 选中的值，date 的字符串格式为yyyy-MM-dd 必选
    private String value;
    //date 的最大值
    private String max;
    //date 的最小值
    private String min;

    //时间选择器格式  y-m 年月 或者 y-m-d 年月日
    private String format;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public int[] getIndexs() {
        return indexs;
    }

    public void setIndexs(int[] indexs) {
        this.indexs = indexs;
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
