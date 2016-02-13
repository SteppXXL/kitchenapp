package com.mercateo.kitchenapp.sso.authorization;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;

import com.mercateo.kitchenapp.pages.PagesRegistry;

public class UnauthorizedListenerImpl implements IUnauthorizedComponentInstantiationListener {

    private final PagesRegistry pages;

    @Inject
    UnauthorizedListenerImpl(PagesRegistry pages) {
        this.pages = checkNotNull(pages);
    }

    @Override
    public final void onUnauthorizedInstantiation(Component component) {
        if (component instanceof Page) {
            if (!AbstractAuthenticatedWebSession.get().isSignedIn()) {
                restartResponseAtSignInPage();
            } else {
                onUnauthorizedPage((Page) component);
            }
        } else {
            throw new UnauthorizedInstantiationException(component.getClass());
        }
    }

    public void restartResponseAtSignInPage() {
        throw new RestartResponseAtInterceptPageException(pages.getSignInPage());
    }

    protected void onUnauthorizedPage(Page page) {
        throw new UnauthorizedInstantiationException(page.getClass());
    }

}
