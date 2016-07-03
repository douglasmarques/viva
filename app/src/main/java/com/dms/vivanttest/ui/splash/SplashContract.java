package com.dms.vivanttest.ui.splash;

import com.dms.vivanttest.ui.login.LoginPresenter;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface SplashContract {

    interface View {

        void showIntroScreen();

        void showMainScreen();

    }

}
