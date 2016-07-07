package com.dms.viva.data.repository;

import android.content.Context;

public class UserRepositories {

    private UserRepositories() {
        // no instance
    }

    private static UserRepository repository = null;

    public synchronized static UserRepository getInMemoryRepoInstance(Context context) {
        if (null == repository) {
            repository = new UserRepositoryImpl(context);
        }
        return repository;
    }
}
