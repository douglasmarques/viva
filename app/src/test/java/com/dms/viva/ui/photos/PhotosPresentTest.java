package com.dms.viva.ui.photos;

import com.dms.viva.core.PhotoPost;
import com.dms.viva.data.repository.PhotoRepository;
import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.verify;

public class PhotosPresentTest {

    private static List<PhotoPost> PHOTOS = Lists.newArrayList(new PhotoPost(),
            new PhotoPost());

    @Mock
    private PhotoRepository repository;

    @Mock
    private PhotosContract.View view;

    private PhotosPresenter presenter;

    /**
     * {@link ArgumentCaptor} is a powerful Mockito API to capture argument values and use them to
     * perform further actions or assertions on them.
     */
    @Captor
    private ArgumentCaptor<PhotoRepository.GetPhotosCallback> callbackCaptor;

    @Before
    public void setupPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        presenter = new PhotosPresenter(view, repository);

    }

    @Test
    public void showPhotos() {
        presenter.showPhotos();

        verify(view).showProgress(true);

        verify(repository).getPhotos(callbackCaptor.capture());
        callbackCaptor.getValue().onResultSuccess(PHOTOS);

        // Then progress indicator is hidden and photos are shown in UI
        verify(view).showProgress(false);
        verify(view).showPhotos(PHOTOS);
    }

    @Test
    public void clickPhotoDetails() {
        presenter.clickPhotoDetails(PHOTOS.get(0));
        verify(view).showPhotoDetails(PHOTOS.get(0));
    }

    @Test
    public void clickLogout() {
        presenter.clickLogout();
        verify(view).showLogoutAlert();
    }

}