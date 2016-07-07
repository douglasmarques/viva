package com.dms.viva.data.repository;

import com.dms.viva.data.remote.RemoteService;

public class PhotoRepositories {

    private PhotoRepositories() {
        // no instance
    }

    private static PhotoRepository repository = null;

    public synchronized static PhotoRepository getInMemoryRepoInstance(RemoteService service) {
        if (null == repository) {
            repository = new PhotoRepositoryImpl(service);
        }
        return repository;
    }
}
