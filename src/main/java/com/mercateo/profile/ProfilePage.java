package com.mercateo.profile;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.WicketConstants;
import com.mercateo.layout.SignInNeededTemplate;

public class ProfilePage extends SignInNeededTemplate {

    private static final long serialVersionUID = 1L;

    public ProfilePage(PageParameters parameters) {

        StringValue email = parameters.get(WicketConstants.EMAIL);
        if (email != null) {
            add(new Label(WicketConstants.EMAIL, email));
        }

        StringValue password = parameters.get(WicketConstants.PASSWORD);
        if (password != null) {
            add(new Label(WicketConstants.PASSWORD, password));
        }

    }

}
