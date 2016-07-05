package com.dms.vivanttest.ui.login;

import com.dms.vivanttest.core.User;
import com.dms.vivanttest.data.repository.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

public class LoginPresentTest {

    private User user = new User(UserRepository.TEST_USER);

    private static final String WRONG_USER = "wrong_user@eh.com";
    private static final String WRONG_PASS = "omg_pass";

    @Mock
    private UserRepository repository;

    @Mock
    private LoginContract.View view;

    private LoginPresenter presenter;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<UserRepository.LoginCallback> callbackCaptor;

    @Before
    public void setupPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.



        MockitoAnnotations.initMocks(this);

        presenter = new LoginPresenter(view, repository);
    }

    @Test
    public void loginSuccess() {
        presenter.login(UserRepository.TEST_USER, UserRepository.TEST_PASSWORD);

        verify(view).showProgress(true);

        verify(repository).login(eq(UserRepository.TEST_USER),
                eq(UserRepository.TEST_PASSWORD),
                callbackCaptor.capture());
        callbackCaptor.getValue().onLoginSuccess(user);

        verify(view).showMainScreen();
    }

    @Test
    public void loginFail_wrongUser() {
        presenter.login(WRONG_USER, UserRepository.TEST_PASSWORD);

        verify(view).showProgress(true);

        verify(repository).login(eq(WRONG_USER),
                eq(UserRepository.TEST_PASSWORD),
                callbackCaptor.capture());
        callbackCaptor.getValue().onLoginFail(UserRepository.ERROR_WRONG_PASS_OR_USER);

        verify(view).showProgress(false);
        verify(view).showLoginErrors(LoginPresenter.ValidationLogin.WRONG_CREDENTIALS);
    }

    @Test
    public void loginFail_wrongPassword() {
        presenter.login(UserRepository.TEST_USER, WRONG_PASS);

        verify(view).showProgress(true);

        verify(repository).login(eq(UserRepository.TEST_USER),
                eq(WRONG_PASS),
                callbackCaptor.capture());
        callbackCaptor.getValue().onLoginFail(UserRepository.ERROR_WRONG_PASS_OR_USER);

        verify(view).showProgress(false);
        verify(view).showLoginErrors(LoginPresenter.ValidationLogin.WRONG_CREDENTIALS);
    }

}