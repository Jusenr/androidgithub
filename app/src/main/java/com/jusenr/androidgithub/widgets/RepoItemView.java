package com.jusenr.androidgithub.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flyco.labelview.LabelView;
import com.jusenr.androidgithub.R;
import com.jusenr.androidgithub.home.model.model.Repo;
import com.jusenr.androidgithub.utils.Utils;
import com.jusenr.androidlibrary.widgets.fresco.FrescoImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mingjun on 16/8/12.
 */
public class RepoItemView extends FrameLayout {

    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.desc)
    TextView mDesc;
    @BindView(R.id.image)
    FrescoImageView mUserIcon;
    @BindView(R.id.owner)
    TextView mOwner;
    @BindView(R.id.update_time)
    TextView mUpdateTime;
    @BindView(R.id.star)
    TextView mStarCount;
    @BindView(R.id.star_view)
    LinearLayout mStarView;
    @BindView(R.id.label_view)
    LabelView mLabelView;
    @BindView(R.id.star_icon)
    ImageView mStarIcon;

    private Repo mRepo;

    public RepoItemView(Context context) {
        super(context);
        init();
    }

    public RepoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RepoItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_repo, this);
        ButterKnife.bind(this);
    }

    public void setRepo(Repo repo) {
        if (repo != null) {
            mRepo = repo;
            mName.setText(repo.getName());
            mDesc.setText(repo.getDescription());

            mUserIcon.setImageURL(repo.getOwner().getAvatar_url());
            mOwner.setText(repo.getOwner().getLogin());

            if (!TextUtils.isEmpty(repo.getLanguage())) {
                mLabelView.setVisibility(VISIBLE);
                mLabelView.setText(repo.getLanguage());
            } else {
                mLabelView.setVisibility(GONE);
            }
            mUpdateTime.setText(Utils.formatDateGithub(repo.getUpdated_at()));
            mStarCount.setText(Integer.toString(repo.getStargazers_count()));

            mStarIcon.setImageResource(repo.isStarred() ? R.mipmap.ic_star_selected : R.mipmap.ic_star);
        }
    }

    @OnClick({R.id.image, R.id.star_view})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image:
                if (mRepoActionListener != null && mRepo != null) {
                    mRepoActionListener.onUserAction(mRepo.getOwner().getLogin());
                }
                break;
            case R.id.star_view:
                if (mRepoActionListener != null && mRepo != null) {
                    if (mRepo.isStarred()) {
                        mRepoActionListener.onUnstarAction(mRepo);
                    } else {
                        mRepoActionListener.onStarAction(mRepo);
                    }
                }
                break;
        }
    }

    private RepoActionListener mRepoActionListener;

    public void setRepoActionListener(RepoActionListener listener) {
        this.mRepoActionListener = listener;
    }

    public interface RepoActionListener {

        void onStarAction(Repo repo);

        void onUnstarAction(Repo repo);

        void onUserAction(String username);
    }
}
