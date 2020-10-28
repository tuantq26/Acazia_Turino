package com.tuantq.turino.presenter;

import com.tuantq.turino.event.F002LoginOnCallBack;
import com.tuantq.turino.model.Login;
import com.tuantq.turino.model.User;
import com.tuantq.turino.presenter.base_presenter.BasePresenter;
import com.tuantq.turino.view.OnActionCallBack;

import java.util.List;

public class F002LoginPresenter extends BasePresenter<F002LoginOnCallBack> {
    public static final String KEY_CHECK_USERNAME = "KEY_CHECK_USERNAME";
    public static final String KEY_LOGIN = "KEY_LOGIN";

    public F002LoginPresenter(F002LoginOnCallBack event) {
        super(event);
    }

    public void checkValidPassword(String username) {
        boolean rs = username.length() >= 8;
        mListener.getResult(KEY_CHECK_USERNAME, rs);
    }

    public void login(String username, String password, List<User> listUser) {
        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        User user = new User();
        user.setLogin(login);
        if (listUser.contains(user)) {
            mListener.getResult(KEY_LOGIN, true);
        } else {
            mListener.getResult(KEY_LOGIN, false);
        }
    }

}
