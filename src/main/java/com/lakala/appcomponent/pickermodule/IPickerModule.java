package com.lakala.appcomponent.pickermodule;

import com.taobao.weex.bridge.JSCallback;

/**
 * Created by dingqq on 2018/4/24.
 */

public interface IPickerModule {

    boolean multistagePicker(String params, JSCallback callback);

    boolean pickDate(String params, JSCallback callback);

}
