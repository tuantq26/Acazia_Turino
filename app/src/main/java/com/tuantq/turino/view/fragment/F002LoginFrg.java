package com.tuantq.turino.view.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.tuantq.problem01.R;
import com.tuantq.turino.App;
import com.tuantq.turino.constant.Constant;
import com.tuantq.turino.event.F002LoginOnCallBack;
import com.tuantq.turino.model.User;
import com.tuantq.turino.presenter.F002LoginPresenter;
import com.tuantq.turino.utils.Utils;
import com.tuantq.turino.view.base.BaseFragment;

import java.util.List;

public class F002LoginFrg extends BaseFragment<F002LoginPresenter> implements F002LoginOnCallBack {
    public static final String KEY_SHOW_HOME_FRG = "KEY_SHOW_CAROUSEL_FRG";
    public static final String TAG = F002LoginFrg.class.getName();

    private EditText edtUsername, edtPassword;
    private TextView tvLogin, tvForgotPassword;
    private TableRow trRememberMe;
    private ImageView imvRememberMe;
    private boolean isRememberMe;
    private String username;
    private String password;

    @Override
    protected void initPresenter() {
        mPresenter = new F002LoginPresenter(this);
    }

    @Override
    protected void initView() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);

        tvLogin = findViewById(R.id.tv_login);
        tvLogin.setOnClickListener(this);

        trRememberMe = findViewById(R.id.tr_remember_me);
        trRememberMe.setOnClickListener(this);

        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        tvForgotPassword.setOnClickListener(this);

        imvRememberMe = findViewById(R.id.imv_remember_me);

        findViewById(R.id.imv_facebook).setOnClickListener(this);
        findViewById(R.id.imv_google).setOnClickListener(this);
        findViewById(R.id.imv_twitter).setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_002_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                checkValidPassword();
                break;
            case R.id.tr_remember_me:
                rememberMe();
                break;
            case R.id.tv_forgot_password:
            case R.id.imv_facebook:
            case R.id.imv_google:
            case R.id.imv_twitter:
                Toast.makeText(App.getInstance(), "Nothing happen!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void checkValidPassword() {
        mPresenter.checkValidPassword(edtPassword.getText().toString());
    }

    private void rememberMe() {
        isRememberMe = !isRememberMe;
        int levelVerified = isRememberMe ?
                Constant.REMEMBER_ME : Constant.NOT_REMEMBER_ME;
        imvRememberMe.setImageLevel(levelVerified);
    }

    @Override
    public void getResult(String key, Object... data) {
        switch (key) {
            case F002LoginPresenter.KEY_CHECK_USERNAME:
                boolean rs = (boolean) data[0];
                if (rs) {
                    login();
                } else {
                    Toast.makeText(App.getInstance(), "Password must be over 8 characters!",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case F002LoginPresenter.KEY_LOGIN:
                rs = (boolean) data[0];
                if (!rs) {
                    Toast.makeText(App.getInstance(), "Username or Password wrong!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    saveStrToSharePref(Constant.KEY_USERNAME, username);
                    saveStrToSharePref(Constant.KEY_PASSWORD, password);

                    mCallBack.onActionCallBack(KEY_SHOW_HOME_FRG, null);
                }
                break;
            default:
                break;
        }
    }

    private void login() {
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();
        List<User> mListUser = Utils.loadUsers(App.getInstance());
        mPresenter.login(username, password, mListUser);

        saveBooToSharePref(Constant.KEY_REMEMBER_ME, isRememberMe);
    }


}
