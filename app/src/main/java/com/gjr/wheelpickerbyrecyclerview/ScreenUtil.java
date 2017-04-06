package com.gjr.wheelpickerbyrecyclerview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 手机屏幕工具类
 * @author user
 *
 */
public class ScreenUtil {
	
	/**
	 * 获取手机屏幕宽
	 * @param context
	 * @return
	 * 
	 * 
	 */
    public static int getScreenW(Context context) {
    	WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	DisplayMetrics dm = new DisplayMetrics();
    	windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
    
	/**
	 * 获取手机屏幕高
	 * @param context
	 * @return
	 */
    public static int getScreenH(Context context) {
    	WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    	DisplayMetrics dm = new DisplayMetrics();
    	windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }
   
    /**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static float dip2fpx(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return dpValue * scale + 0.5f;
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	/**
	 * 将px值转换为sp值，保证文字大小不变
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

}
