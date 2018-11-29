package com.lakala.appcomponent.pickermodule;

import android.app.Activity;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lakala.appcomponent.pickermodule.mode.PickerChildren;
import com.lakala.appcomponent.pickermodule.mode.PickerDateMode;
import com.lakala.appcomponent.pickermodule.mode.PickerMode;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 选择器
 * Created by dingqq on 2018/4/24.
 */

public class PickerModule extends WXModule implements IPickerModule {

    private List<String> mList;

    private List<List<String>> mListChild;

    private List<List<List<String>>> mListChildChild;

    @JSMethod
    @Override
    public boolean multistagePicker(String params, final JSCallback callback) {

        Activity activity = (Activity) mWXSDKInstance.getContext();


        if (activity != null && !TextUtils.isEmpty(params)) {

            PickerMode mode = JSON.parseObject(params, PickerMode.class);

            final String[] mValues = mode.getValues();

            //数据源1
            final List<PickerChildren> list = mode.getItems();

//            String textColor = mode.getTextColor();
            //picker中选中item的背景色
//            String selectionColor = mode.getSelectionColor();
            //确认按钮的文案
            String confirmTitle = mode.getConfirmTitle();
            //取消按钮的文案
            String cancelTitle = mode.getCancelTitle();
            //确认按钮的文字颜色
            String confirmTitleColor = mode.getConfirmTitleColor();
            //取消按钮的文字颜色
            String cancelTitleColor = mode.getCancelTitleColor();
            //对话框的标题
            String title = mode.getTitle();
            //对话框标题的文字颜色
            String titleColor = mode.getTitleColor();
            //对话框标题的背景色
            String titleBackgroundColor = mode.getTitleBackgroundColor();

            mList = new ArrayList<>();

            //下标数组
            int[] indexs = new int[mValues.length];

            for (PickerChildren children : list) {
                mList.add(children.getText());
            }

            if (mValues.length > 1) {
                //数据源2
                mListChild = new ArrayList<>();
                for (PickerChildren children : list) {
                    List<String> array = new ArrayList<>();
                    for (PickerChildren children1 : children.getChildrens()) {
                        array.add(children1.getText());
                    }
                    mListChild.add(array);
                }
            }

            if (mValues.length > 2) {
                //数据源3
                mListChildChild = new ArrayList<>();

                for (PickerChildren children : list) {

                    List<List<String>> array = new ArrayList<>();

                    for (PickerChildren children1 : children.getChildrens()) {

                        List<String> array2 = new ArrayList<>();

                        for (PickerChildren children2 : children1.getChildrens()) {
                            array2.add(children2.getText());
                        }

                        array.add(array2);
                    }

                    mListChildChild.add(array);
                }
            }

            OnOptionsSelectListener listener = new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    PickerChildren pickOne = null, pickTwo = null, pickThree = null;

                    //构建返回数组
                    PickerChildren[] result = new PickerChildren[mValues.length];

                    //第一个选项的值
                    if (list.size() > options1) {
                        result[0] = pickOne = list.get(options1);
                    }

                    //第二个选项的值
                    if (mValues.length > 1) {
                        if (pickOne != null) {
                            List<PickerChildren> listTwo = pickOne.getChildrens();
                            if (listTwo != null && listTwo.size() > option2) {
                                result[1] = pickTwo = listTwo.get(option2);
                            }
                        }
                    }

                    //第三个选项的值
                    if (mValues.length > 2) {
                        if (pickTwo != null) {
                            List<PickerChildren> listThree = pickTwo.getChildrens();

                            if (listThree != null && listThree.size() > options3) {
                                result[2] = pickThree = listThree.get(options3);
                            }
                        }
                    }

                    if (pickOne != null) {
                        pickOne.setChildrens(null);
                    }

                    if (pickTwo != null) {
                        pickTwo.setChildrens(null);
                    }
                    if (pickThree != null) {
                        pickThree.setChildrens(null);
                    }

                    //返回的分别是三个级别的选中位置
                    callback.invoke(result);

                    mList = null;
                    if (mListChild != null) {
                        mListChild = null;
                    }

                    if (mListChildChild != null) {
                        mListChildChild = null;
                    }

                }
            };

            OptionsPickerBuilder builder = new OptionsPickerBuilder(activity, listener).isRestoreItem(true);

            if (!TextUtils.isEmpty(cancelTitle)) {
                builder.setCancelText(cancelTitle);
            }

            if (!TextUtils.isEmpty(cancelTitleColor)) {
                builder.setCancelColor(Color.parseColor(cancelTitleColor));
            }

            if (!TextUtils.isEmpty(title)) {
                builder.setTitleText(title);
            }

            if (!TextUtils.isEmpty(titleColor)) {
                builder.setTitleColor(Color.parseColor(titleColor));
            }

            if (!TextUtils.isEmpty(titleBackgroundColor)) {
                builder.setTitleBgColor(Color.parseColor(titleBackgroundColor));
            }

            if (!TextUtils.isEmpty(confirmTitle)) {
                builder.setSubmitText(confirmTitle);
            }

            if (!TextUtils.isEmpty(confirmTitleColor)) {
                builder.setSubmitColor(Color.parseColor(confirmTitleColor));
            }


            OptionsPickerView<String> pvOptions = builder.build();
            pvOptions.setPicker(mList, mListChild, mListChildChild);

            int size;

            if (TextUtils.isEmpty(mValues[0])) {
                indexs[0] = 0;
            } else {
                size = list.size();

                for (int i = 0; i < size; i++) {
                    PickerChildren children = list.get(i);
                    //计算第一列下标
                    if (children.getValue().equals(mValues[0])) {
                        indexs[0] = i;
                        break;
                    }
                }
            }

            //计算第二列下标
            if (mValues.length > 1) {

                if (TextUtils.isEmpty(mValues[1])) {
                    indexs[1] = 0;
                } else {
                    List<PickerChildren> listChild = list.get(indexs[0]).getChildrens();
                    size = listChild.size();

                    for (int i = 0; i < size; i++) {
                        PickerChildren children = listChild.get(i);
                        if (children.getValue().equals(mValues[1])) {
                            indexs[1] = i;
                            break;
                        }
                    }
                }
            }

            //计算第二列下标
            if (mValues.length > 2) {

                if (TextUtils.isEmpty(mValues[2])) {
                    indexs[2] = 0;
                } else {
                    List<PickerChildren> listChild = list.get(indexs[0]).getChildrens().get(indexs[1]).getChildrens();
                    size = listChild.size();

                    for (int i = 0; i < size; i++) {
                        PickerChildren children = listChild.get(i);
                        if (children.getValue().equals(mValues[2])) {
                            indexs[2] = i;
                            break;
                        }
                    }
                }
            }

            if (indexs.length > 2) {
                pvOptions.setSelectOptions(indexs[0], indexs[1], indexs[2]);
            } else if (indexs.length > 1) {
                pvOptions.setSelectOptions(indexs[0], indexs[1]);
            } else if (indexs.length > 0) {
                pvOptions.setSelectOptions(indexs[0]);
            }

            pvOptions.show();
        }

        return false;
    }

    @JSMethod
    @Override
    public boolean pickDate(String params, final JSCallback callback) {

        Activity activity = (Activity) mWXSDKInstance.getContext();


        if (activity != null && !TextUtils.isEmpty(params)) {

            PickerDateMode mode = JSON.parseObject(params, PickerDateMode.class);

            String format;
            boolean[] type;
            //只展示年月
            if ("y-m".equals(mode.getFormat())) {
                format = "yyyy-MM";
                type = new boolean[]{true, true, false, false, false, false};
            } else {
                format = "yyyy-MM-dd";
                type = new boolean[]{true, true, true, false, false, false};
            }

            //选中时间
            String value = mode.getValue();

            final DateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());

            if (TextUtils.isEmpty(value)) {
                value = dateFormat.format(new Date());
            }

            //最大日期
            String max = mode.getMax();

            //最小日期
            String min = mode.getMin();

//            String textColor = mode.getTextColor();
//            //picker中选中item的背景色
//            String selectionColor = mode.getSelectionColor();
            //确认按钮的文案
            String confirmTitle = mode.getConfirmTitle();
            //取消按钮的文案
            String cancelTitle = mode.getCancelTitle();
            //确认按钮的文字颜色
            String confirmTitleColor = mode.getConfirmTitleColor();
            //取消按钮的文字颜色
            String cancelTitleColor = mode.getCancelTitleColor();
            //对话框的标题
            String title = mode.getTitle();
            //对话框标题的文字颜色
            String titleColor = mode.getTitleColor();
            //对话框标题的背景色
            String titleBackgroundColor = mode.getTitleBackgroundColor();

            OnTimeSelectListener listener = new OnTimeSelectListener() {
                @Override
                public void onTimeSelect(Date date, View v) {//选中事件回调
                    String time = dateFormat.format(new Date(date.getTime()));
                    callback.invoke(time);
                }
            };

            TimePickerBuilder builder = new TimePickerBuilder(activity, listener).setType(type)
                    .setLabel("年", "月", "日", "时", "分", "秒")
                    .isCenterLabel(false);

            //当前时间转化为calendar
            Calendar selectedDate = Calendar.getInstance();

            try {
                selectedDate.setTime(dateFormat.parse(value));
                //设置选中时间
                builder.setDate(selectedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //最大最小时间
            if (!TextUtils.isEmpty(min) && !TextUtils.isEmpty(max)) {
                Calendar startDate = Calendar.getInstance();
                Calendar endDate = Calendar.getInstance();

                try {
                    startDate.setTime(dateFormat.parse(min));
                    endDate.setTime(dateFormat.parse(max));

                    builder.setRangDate(startDate, endDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            if (!TextUtils.isEmpty(cancelTitle)) {
                builder.setCancelText(cancelTitle);
            }

            if (!TextUtils.isEmpty(cancelTitleColor)) {
                builder.setCancelColor(Color.parseColor(cancelTitleColor));
            }

            if (!TextUtils.isEmpty(title)) {
                builder.setTitleText(title);
            }

            if (!TextUtils.isEmpty(titleColor)) {
                builder.setTitleColor(Color.parseColor(titleColor));
            }

            if (!TextUtils.isEmpty(titleBackgroundColor)) {
                builder.setTitleBgColor(Color.parseColor(titleBackgroundColor));
            }

            if (!TextUtils.isEmpty(confirmTitle)) {
                builder.setSubmitText(confirmTitle);
            }

            if (!TextUtils.isEmpty(confirmTitleColor)) {
                builder.setSubmitColor(Color.parseColor(confirmTitleColor));
            }

            builder.build().show();
        }


        return false;
    }
}
