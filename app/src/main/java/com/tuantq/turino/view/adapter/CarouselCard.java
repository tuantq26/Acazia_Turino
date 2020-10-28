package com.tuantq.turino.view.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.widget.ImageViewCompat;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.tuantq.problem01.R;
import com.tuantq.turino.App;
import com.tuantq.turino.model.User;

@Layout(R.layout.item_carousel)
public class CarouselCard implements android.view.View.OnClickListener {

    @View(R.id.profileImageView)
    private CircularImageView profileImageView;

    @View(R.id.tv_info_01)
    private TextView tvInfo01;

    @View(R.id.tv_info_02)
    private TextView tvInfo02;

    @View(R.id.imv_user)
    private ImageView imvUser;

    @View(R.id.imv_info)
    private ImageView imvInfo;

    @View(R.id.imv_location)
    private ImageView imvLocation;

    @View(R.id.imv_phone)
    private ImageView imvPhone;

    @View(R.id.imv_lock)
    private ImageView imvLock;

    private User mUser;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;

    public CarouselCard(Context context, User user, SwipePlaceHolderView swipeView) {
        mContext = context;
        mUser = user;
        mSwipeView = swipeView;
    }

    @Resolve
    private void onResolved() {
        Glide.with(mContext).load(mUser.getPicture().getMedium()).into(profileImageView);
        tvInfo01.setText(mUser.getName().toString());
        tvInfo02.setText(mUser.getLocation().toString());

        imvUser.setOnClickListener(this);
        imvInfo.setOnClickListener(this);
        imvLocation.setOnClickListener(this);
        imvPhone.setOnClickListener(this);
        imvLock.setOnClickListener(this);

    }

    private void tintImv(ImageView imv) {
        ImageViewCompat.setImageTintList(imvUser,
                ColorStateList.valueOf(App.getInstance().getColor(R.color.colorLightGray)));
        ImageViewCompat.setImageTintList(imvInfo,
                ColorStateList.valueOf(App.getInstance().getColor(R.color.colorLightGray)));
        ImageViewCompat.setImageTintList(imvLocation,
                ColorStateList.valueOf(App.getInstance().getColor(R.color.colorLightGray)));
        ImageViewCompat.setImageTintList(imvLock,
                ColorStateList.valueOf(App.getInstance().getColor(R.color.colorLightGray)));
        ImageViewCompat.setImageTintList(imvPhone,
                ColorStateList.valueOf(App.getInstance().getColor(R.color.colorLightGray)));

        ImageViewCompat.setImageTintList(imv,
                ColorStateList.valueOf(App.getInstance().getColor(R.color.colorGreen)));
    }

    @SwipeOut
    private void onSwipedOut() {
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState() {
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn() {
        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState() {
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState() {
        Log.d("EVENT", "onSwipeOutState");
    }

    @Override
    public void onClick(android.view.View v) {
        switch (v.getId()) {
            case R.id.imv_user:
                tintImv(imvUser);
                tvInfo02.setText(mUser.getId().toString());
                break;
            case R.id.imv_info:
                tintImv(imvInfo);
                tvInfo02.setText(mUser.getGender());
                break;
            case R.id.imv_location:
                tvInfo02.setText(mUser.getLocation().toString());
                tintImv(imvLocation);
                break;
            case R.id.imv_phone:
                tvInfo02.setText(mUser.getPhone());
                tintImv(imvPhone);
                break;
            case R.id.imv_lock:
                tvInfo02.setText(mUser.getEmail());
                tintImv(imvLock);
                break;
        }
    }
}