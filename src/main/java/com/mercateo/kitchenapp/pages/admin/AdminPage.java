package com.mercateo.kitchenapp.pages.admin;

import javax.inject.Inject;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.kitchenapp.db.UserDao;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.NeededRoles;
import com.mercateo.kitchenapp.sso.roles.UserRole;

@NeededRoles(UserRole.ADMIN)
public class AdminPage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserDao userAccess;

    public AdminPage(PageParameters params) {
        super(params);
    }

    @Override
    protected void onBeforeRender() {

        super.onBeforeRender();

        add(new UserTable("userTable", new UserSortableDataProvider(userAccess)));

    }

}
