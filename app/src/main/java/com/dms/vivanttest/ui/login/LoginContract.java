package com.dms.vivanttest.ui.login;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface LoginContract {

    interface View {

        void showLoginError(String error);

        void showInvalidFieldErrors(LoginPresenter.ValidationLogin validation);

        void showMainScreen();

        void showProgress(final boolean show);
    }

    interface UserActionsListener{

        void login(String user, String pass);

    }
}
