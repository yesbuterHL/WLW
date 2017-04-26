package net.tzsoft.wlw.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 解决Toast重复弹出，Toast单例模式
 * Created by hl on 2017/4/26.
 */

public class ToastUtils {
    /**
     * 之前显示的内容
     */
    private static String oldMsg;
    /**
     * Toast对象
     */
    private static Toast toast = null;
    /**
     * 第一次时间
     */
    private static long oneTime = 0;
    /**
     * 第二次时间
     */
    private static long twoTime = 0;
    /**
     * 是否显示toast,默认为true,设置为false则不显示toast
     */
    public static boolean isShow = true;

    /**
     * 显示Toast 默认显示短时间Toast.LENGTH_SHORT
     *
     * @param context context必须传application，否则内存泄漏
     * @param message
     */
    public static void showToast(Context context, String message) {
        if (!isShow) return;
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (message.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = message;
                toast.setText(message);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

    public static void showToast(Context context, String message, int duration) {
        if (!isShow) return;
        if (toast == null) {
            toast = Toast.makeText(context, message, duration);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (message.equals(oldMsg)) {
                if (twoTime - oneTime > duration) {
                    toast.show();
                }
            } else {
                oldMsg = message;
                toast.setText(message);
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}
