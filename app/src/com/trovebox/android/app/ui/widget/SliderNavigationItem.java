
package com.trovebox.android.app.ui.widget;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.TextView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.trovebox.android.app.R;
import com.trovebox.android.app.util.CommonUtils;

public class SliderNavigationItem extends LinearLayout {
    private final TextView label;
    private final View selectionHandler;
    private final ImageView icon;

    public SliderNavigationItem(Context context) {
        this(context, null);
    }

    public SliderNavigationItem(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.sliderNavigationItemStyle);
    }

    public SliderNavigationItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SliderNavigationItem,
                defStyleAttr, R.style.trovebox_SliderNavigationItem);
        final int gravity = a
                .getInt(R.styleable.SliderNavigationItem_android_gravity, Gravity.LEFT), layout;
        if ((gravity & Gravity.LEFT) != 0) {
            layout = R.layout.slider_navigation_item_left;
        } else {
            layout = R.layout.slider_navigation_item_bottom;
        }
        LayoutInflater.inflate(context, layout, this, true);
        selectionHandler = findViewById(R.id.selectionHandler);
        label = (TextView) findViewById(android.R.id.text1);
        icon = (ImageView) findViewById(R.id.icon);
        if (a.hasValue(R.styleable.SliderNavigationItem_android_text)) {
            setLabel(a.getText(R.styleable.SliderNavigationItem_android_text));
        }
        if (a.hasValue(R.styleable.SliderNavigationItem_android_textColor)) {
            setTextColor(a.getColorStateList(R.styleable.SliderNavigationItem_android_textColor));
        }
        if (a.hasValue(R.styleable.SliderNavigationItem_selectionHandler)) {
            setSelectionHandlerDrawable(a
                    .getDrawable(R.styleable.SliderNavigationItem_selectionHandler));
        }
        if (a.hasValue(R.styleable.SliderNavigationItem_selectionHandlerVisiblity)) {
            setSelectionHandlerVisiblity(a.getInt(
                    R.styleable.SliderNavigationItem_selectionHandlerVisiblity, 0) == 0);
        }
        if (a.hasValue(R.styleable.SliderNavigationItem_android_background)) {
            Drawable bg = a
                    .getDrawable(R.styleable.SliderNavigationItem_android_background);
            if (CommonUtils.isJellyBeanOrHigher())
            {
                setBackgroundJB(bg);
            }
            else
            {
                setBackgroundPriorJB(bg);
            }
        }
        a.recycle();
    }

    @TargetApi(16)
    void setBackgroundJB(Drawable drawable)
    {
        setBackground(drawable);
    }

    @SuppressWarnings("deprecation")
    void setBackgroundPriorJB(Drawable drawable)
    {
        setBackgroundDrawable(drawable);
    }

    public void setLabel(CharSequence label) {
        this.label.setText(label);
    }

    /**
     * Set the text color
     * 
     * @param color
     */
    public void setTextColor(int color)
    {
        this.label.setTextColor(color);
    }

    /**
     * Set the text color
     * 
     * @param color
     */
    public void setTextColor(ColorStateList color)
    {
        this.label.setTextColor(color);
    }

    /**
     * Set the navigation item icon
     * 
     * @param drawable
     */
    public void setIcon(Drawable drawable)
    {
        this.icon.setImageDrawable(drawable);
    }

    public void setLabel(int resId) {
        setLabel(getResources().getText(resId));
    }

    public void setSelectionHandlerColor(int color) {
        setSelectionHandlerDrawable(new ColorDrawable(color));
    }

    public void setSelectionHandlerColorResource(int resId) {
        setSelectionHandlerColor(getResources().getColor(resId));
    }

    @SuppressWarnings("deprecation")
    public void setSelectionHandlerDrawable(Drawable drawable) {
        selectionHandler.setBackgroundDrawable(drawable);
    }

    public void setSelectionHandlerDrawableResource(int resId) {
        setSelectionHandlerDrawable(getResources().getDrawable(resId));
    }

    public void setSelectionHandlerVisiblity(boolean visible) {
        setSelectionHandlerVisiblity(visible ? VISIBLE : INVISIBLE);
    }

    public void setSelectionHandlerVisiblity(int visiblity) {
        selectionHandler.setVisibility(visiblity);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        setSelectionHandlerVisiblity(selected);
    }
}
