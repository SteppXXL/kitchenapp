package com.mercateo.kitchenapp.db.dummy;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.mercateo.kitchenapp.data.User;
import com.mercateo.kitchenapp.db.UserAccess;
import com.mercateo.kitchenapp.db.UserDoesNotExistException;
import com.mercateo.kitchenapp.sso.roles.UserRole;

public class DummyUserAccess implements UserAccess {

    static final DummyUserAccess INSTANCE = new DummyUserAccess();

    private DummyUserAccess() {
        // hide constructor
    }

    @Override
    public boolean existsUser(User user) {
        return false;
    }

    @Override
    public void addUser(User user) {
        // nothing
    }

    @Override
    public List<User> listAllUsers() {
        return Collections.emptyList();
    }

    @Override
    public Set<UserRole> getUserRoles(User user) throws UserDoesNotExistException {
        return Collections.emptySet();
    }

}