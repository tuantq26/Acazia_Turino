package com.tuantq.turino.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.tuantq.problem01.R;


public class FontTextView extends androidx.appcompat.widget.AppCompatTextView {
    //public class FontTextView extends TextView {
    private static final int DEFAULT_FONT_TYPE = -1;
    private static final int BOLD_FONT_TYPE = 0;
    private static final int THIN_FONT_TYPE = 1;
    private static final int LIGHT_FONT_TYPE = 2;

    public FontTextView(Context context) {
        super(context);
        initView();
    }

    private void initView() {

    }

    public FontTextView(Context context, @Nullable AttributeSet attrs) {
        //Gọi phương thức khởi tạo ngay trong môi trường class
        this(context, attrs, 0);
    }

    public FontTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //Lấy mảng giá trị của attribute trong styles
        TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs,
                R.styleable.Font_TextView,
                defStyleAttr,
                0
        );

        //Lấy giá trị của enum được gọi (bold, light, thin)
        int fontType = typedArray.getInteger(
                R.styleable.Font_TextView_fontType,
                DEFAULT_FONT_TYPE);

        typedArray.recycle();

        //set font cho FontTextView
        initFont(fontType);
    }

    private void initFont(int fontType) {
        Typeface tf;
        switch (fontType) {
            case BOLD_FONT_TYPE:
                tf = Typeface.createFromAsset(
                        getContext().getAssets(),
                        "font/Roboto-Bold.ttf");
                break;
            case THIN_FONT_TYPE:
                tf = Typeface.createFromAsset(
                        getContext().getAssets(),
                        "font/Roboto-Thin.ttf");
                break;
            case LIGHT_FONT_TYPE:
                tf = Typeface.createFromAsset(
                        getContext().getAssets(),
                        "font/Roboto-Light.ttf");
                break;
            default:
                tf = Typeface.createFromAsset(getContext().getAssets(), "font/Roboto-Regular.ttf");
                break;
        }
        //sử dụng phương thức setTypeFace để set font cho FontTextView
        setTypeface(tf);
    }

}
