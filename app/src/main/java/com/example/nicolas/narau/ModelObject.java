package com.example.nicolas.narau;

/**
 * Created by Nicolas on 26/3/2017.
 */

public enum ModelObject {

    RED(R.string.red, R.layout.review),
    BLUE(R.string.red, R.layout.review);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }

}

