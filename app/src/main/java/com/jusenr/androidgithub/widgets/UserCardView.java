package com.jusenr.androidgithub.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.user.model.model.UserModel;
import com.jusenr.androidlibrary.widgets.fresco.FrescoImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mingjun on 16/9/5.
 */
public class UserCardView extends FrameLayout {

    @BindView(R.id.iv_user_icon)
    FrescoImageView mUserIcon;
    @BindView(R.id.tv_username)
    TextView mUsername;
    @BindView(R.id.tv_bio)
    TextView mBio;
    @BindView(R.id.tv_company)
    TextView mCompany;
    @BindView(R.id.tv_location)
    TextView mLocation;
    @BindView(R.id.tv_blog)
    TextView mBlog;
    @BindView(R.id.tv_email)
    TextView mEmail;

    public UserCardView(Context context) {
        super(context);
        init();
    }

    public UserCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_card_user, this);
        ButterKnife.bind(this);
    }

    public void setUser(UserModel user) {
        if (user != null) {
            if (!TextUtils.isEmpty(user.getAvatar_url())) {
                mUserIcon.setImageURL(user.getAvatar_url() + ".png");
            }
            String displayName = TextUtils.isEmpty(user.getName()) ? user.getLogin() : user.getName();
            mUsername.setText(displayName);

            if (TextUtils.isEmpty(user.getBio())) {
                mBio.setVisibility(View.GONE);
            } else {
                mBio.setVisibility(View.VISIBLE);
                mBio.setText(user.getBio());
            }

            if (TextUtils.isEmpty(user.getCompany())) {
                mCompany.setVisibility(View.GONE);
            } else {
                mCompany.setVisibility(View.VISIBLE);
                mCompany.setText(user.getCompany());
            }

            if (TextUtils.isEmpty(user.getBlog())) {
                mBlog.setVisibility(View.GONE);
            } else {
                mBlog.setVisibility(View.VISIBLE);
                mBlog.setText(user.getBlog());
            }

            if (TextUtils.isEmpty(user.getLocation())) {
                mLocation.setVisibility(View.GONE);
            } else {
                mLocation.setVisibility(View.VISIBLE);
                mLocation.setText(user.getLocation());
            }

            if (TextUtils.isEmpty(user.getEmail())) {
                mEmail.setVisibility(View.GONE);
            } else {
                mEmail.setVisibility(View.VISIBLE);
                mEmail.setText(user.getEmail());
            }
        }
    }
}
