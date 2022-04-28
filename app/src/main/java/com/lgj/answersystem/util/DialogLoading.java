package com.lgj.answersystem.util;

import android.app.Activity;
import android.app.Dialog;
import android.widget.TextView;

import com.lgj.answersystem.R;


public class DialogLoading {

    private Dialog dialog;
    private DialogLoading(){}
    private static volatile DialogLoading instance;
    public static DialogLoading getInstance(){
        if (instance == null){
            synchronized (DialogLoading.class){
                if (instance == null){
                    instance = new DialogLoading();
                }
            }
        }
        return  instance;
    }
    public void showLoading(Activity activity, String message) {
        if (dialog != null) {
            boolean showing = dialog.isShowing();
            if (showing) {
                return;
            }
        }
        dialog = new Dialog(activity, R.style.progress_dialog);
        dialog.setContentView(R.layout.dialog_loading);
//        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText(message);
        //点击空白处不消失
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
//        Window window = dialog.getWindow();
//        window.setGravity(Gravity.CENTER);

        //此方法是点击外围和点击返回键都不会消失
//        dialog.setCancelable(false);
        //此方法是点击外围不会消失，但是点击返回键一样消失
//        dialog.setCanceledOnTouchOutside(false);
        if(activity != null && !activity.isFinishing()){
            dialog.show();
        }
    }
    // Android 中dismiss 和hide的区别在于，dismiss释放dialog中的内存，而hide不释放内存，Activity退出之前必须调用dialog的dismiss方法
    public void dismissLoading(){
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
    public void hideLoading(){
        if (dialog != null){
            dialog.hide();
        }
    }
}
