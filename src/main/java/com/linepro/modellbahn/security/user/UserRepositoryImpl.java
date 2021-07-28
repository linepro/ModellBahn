package com.linepro.modellbahn.security.user;

import javax.persistence.EntityManager;

import com.linepro.modellbahn.repository.base.RepositorySearch;
import com.linepro.modellbahn.repository.base.SearchableRepositoryImpl;

public class UserRepositoryImpl extends SearchableRepositoryImpl<User> implements RepositorySearch<User> {

    public UserRepositoryImpl(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
