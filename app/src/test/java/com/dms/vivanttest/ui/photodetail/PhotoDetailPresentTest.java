package com.dms.vivanttest.ui.photodetail;

import com.dms.vivanttest.core.PhotoPost;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class PhotoDetailPresentTest {

    private PhotoPost photo = new PhotoPost();

    @Mock
    private PhotoDetailContract.View view;

    private PhotoDetailPresenter presenter;


    @Before
    public void setupPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        presenter = new PhotoDetailPresenter(view);
    }

    @Test
    public void showPhotoDetail() {
        presenter.showPhotoDetail(photo);
        verify(view).showPhotoDetail(photo);
    }

    @Test
    public void clickPhotoDetails() {
        presenter.favouritePhoto();
        verify(view).favouritePhoto();
    }
}