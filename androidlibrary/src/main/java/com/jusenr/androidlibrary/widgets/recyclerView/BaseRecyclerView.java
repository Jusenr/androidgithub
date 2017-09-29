package com.jusenr.androidlibrary.widgets.recyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.jusenr.androidlibrary.R;

/**
 * Created by riven_chris on 2017/4/2.
 */

public class BaseRecyclerView extends RecyclerView {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int INVALID_OFFSET = 2;
    public static final int GRID = 3;

    private int layoutMode;
    private int spanCount;
    private boolean rowDivider;
    private boolean spanDivider;
    private boolean inner;
    private int dividerWidth;
    private int dividerHeight;
    private int marginLeft, marginTop, marginRight, marginBottom;
    private int dividerColor;

    private LayoutManager layoutManager;

    public BaseRecyclerView(Context context) {
        this(context, null);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initStyleable(context, attrs);
        initView(context);
    }

    private void initStyleable(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BaseRecyclerView);
        layoutMode = array.getInt(R.styleable.BaseRecyclerView_layoutMode, VERTICAL);
        spanCount = array.getInt(R.styleable.BaseRecyclerView_columnCount, 3);
        rowDivider = array.getBoolean(R.styleable.BaseRecyclerView_row_divider, false);
        spanDivider = array.getBoolean(R.styleable.BaseRecyclerView_span_divider, false);

        inner = array.getBoolean(R.styleable.BaseRecyclerView_inner, false);
        dividerWidth = (int) array.getDimension(R.styleable.BaseRecyclerView_divider_width, 1f);
        dividerHeight = (int) array.getDimension(R.styleable.BaseRecyclerView_divider_height, 1f);
        marginLeft = (int) array.getDimension(R.styleable.BaseRecyclerView_divider_marginLeft, 0f);
        marginRight = (int) array.getDimension(R.styleable.BaseRecyclerView_divider_marginRight, 0f);
        marginTop = (int) array.getDimension(R.styleable.BaseRecyclerView_divider_marginTop, 0f);
        marginBottom = (int) array.getDimension(R.styleable.BaseRecyclerView_divider_marginBottom, 0f);
        dividerColor = array.getColor(R.styleable.BaseRecyclerView_divider_color, Color.parseColor("#00000000"));
        array.recycle();
    }

    private void initView(Context context) {
        setOverScrollMode(OVER_SCROLL_NEVER);
        switch (layoutMode) {
            case BaseRecyclerView.HORIZONTAL:
                if (!inner) {
                    layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
                } else {

                }
                break;
            case BaseRecyclerView.VERTICAL:
                if (!inner) {
                    layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
                } else {

                }
                break;
            case BaseRecyclerView.INVALID_OFFSET:
                if (!inner)
                    layoutManager = new LinearLayoutManager(context, LinearLayoutManager.INVALID_OFFSET, false);
                else {

                }
                break;
            case BaseRecyclerView.GRID:
                if (!inner) {
                    layoutManager = new GridLayoutManager(context, spanCount);
                } else {
                }
                break;
        }
        setLayoutManager(layoutManager);
        if (rowDivider) {
            HorizontalDividerItemDecoration decoration = new HorizontalDividerItemDecoration.Builder(context)
                    .color(dividerColor)
                    .size(dividerHeight)
                    .margin(marginLeft, marginRight)
                    .build();
            addItemDecoration(decoration);
        }
        if (spanDivider) {
            VerticalDividerItemDecoration decoration = new VerticalDividerItemDecoration.Builder(context)
                    .color(dividerColor)
                    .size(dividerWidth)
                    .margin(marginTop, marginBottom)
                    .build();
            addItemDecoration(decoration);
        }
        setHasFixedSize(true);
    }
}
