package com.tuantq.turino.view.fragment;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.tuantq.problem01.R;
import com.tuantq.turino.App;
import com.tuantq.turino.event.F003HomeOnCallBack;
import com.tuantq.turino.model.User;
import com.tuantq.turino.presenter.F003HomePresenter;
import com.tuantq.turino.utils.Utils;
import com.tuantq.turino.view.adapter.CarouselCard;
import com.tuantq.turino.view.base.BaseFragment;

import java.util.Objects;

public class F003HomeFrg extends BaseFragment<F003HomePresenter> implements F003HomeOnCallBack {
    public static final String TAG = F003HomeFrg.class.getName();
    public static final String KEY_SHOW_LOGIN_FRG = "KEY_SHOW_LOGIN_FRG";
    private TextView tvLogout;
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;


    @Override
    protected void initPresenter() {
        mPresenter = new F003HomePresenter(this);
    }

    @Override
    protected void initView() {
        mSwipeView = findViewById(R.id.swipeView);
        mContext = App.getInstance();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_right_carousel)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_left_carousel));


        for (User user : Objects.requireNonNull(Utils.loadUsers(App.getInstance()))) {
            mSwipeView.addView(new CarouselCard(mContext, user, mSwipeView));
        }

        findViewById(R.id.rejectBtn).setOnClickListener(this);
        findViewById(R.id.acceptBtn).setOnClickListener(this);
        findViewById(R.id.tv_log_out).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rejectBtn:
                mSwipeView.doSwipe(false);
                break;
            case R.id.acceptBtn:
                mSwipeView.doSwipe(true);
                break;
            case R.id.tv_log_out:
                mCallBack.onActionCallBack(KEY_SHOW_LOGIN_FRG, null);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_003_home;
    }

    @Override
    public void getResult(String key, Object... data) {

    }
}
