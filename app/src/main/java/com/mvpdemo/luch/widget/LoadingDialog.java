package com.mvpdemo.luch.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mvpdemo.luch.R;


/**
 * 提示 ,加载控件
 *
 * @author lucky
 */
public class LoadingDialog extends Dialog {
    protected Context context;
    private LinearLayout contentview;
    private LinearLayout lin_load;
    private TextView msg;
    private ProgressBar pb;
    private boolean showLoadImg;
    private int waitTime = 5000;

    //带加载图标
    public LoadingDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
        showLoadImg = true;
        init();
    }

    //选择性 定义 boo为false为提示，true为加载
    public LoadingDialog(Context context, boolean boo) {
        super(context, R.style.dialog);
        this.context = context;
        showLoadImg = boo;
        init();
    }


    protected void init() {

        View v = LayoutInflater.from(context)
                .inflate(R.layout.dialog_loading, null);
        msg = (TextView) v.findViewById(R.id.detail_tv);
        lin_load = (LinearLayout) v.findViewById(R.id.lin_load);

        if (!showLoadImg) {
            pb = (ProgressBar) v.findViewById(R.id.load_probar);
            pb.setVisibility(View.GONE);
            msg.setGravity(Gravity.CENTER);
        }
        contentview = new LinearLayout(context);
        contentview.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        contentview.setBackgroundResource(R.drawable.dialog_background);
        contentview.setGravity(Gravity.CLIP_HORIZONTAL);
        contentview.setOrientation(LinearLayout.VERTICAL);
        setContentView(contentview);
        addView(v);
        setCanceledOnTouchOutside(false);
        setAttributesX(0.8f);
    }

    public void setLoadMsg(String msg) {
        this.msg.setText(msg);
    }


    public void addView(View view) {
        if (null != view) {
            contentview.removeAllViews();
            contentview.addView(view);
            contentview.setVisibility(View.VISIBLE);
        } else {
            contentview.setVisibility(View.GONE);
        }
    }

    public void setAttributesX(float sx) {
        DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.width = (int) (dm2.widthPixels * sx);
        dialogWindow.setAttributes(p);
    }

    public void setAttributesY(float sx) {
        DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.height = (int) (dm2.heightPixels * sx);
        dialogWindow.setAttributes(p);
    }

    public void setFull() {
        DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams p = dialogWindow.getAttributes();
        p.height = (int) (dm2.heightPixels);
        p.width = (int) (dm2.widthPixels);
        dialogWindow.setAttributes(p);
    }

    @Override
    public void dismiss() {
        if (null == context) {
            return;
        } else if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (isShowing()) {
            super.dismiss();
        }
    }

    @Override
    public void show() {
        if (isShowing()) {
            return;
        }
        if (null == context) {
            return;
        } else if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        super.show();
    }

}
