package com.mercateo.sso;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mercateo.HomePage;
import com.mercateo.WicketConstants;
import com.mercateo.db.UserAccess;
import com.mercateo.db.UserAccessCreationException;
import com.mercateo.db.UserAccessFactory;
import com.mercateo.profile.Email;
import com.mercateo.profile.Password;
import com.mercateo.profile.User;

public class LoginForm extends Form<Object> {

    private final TextField<String> emailField;

    private final PasswordTextField passwordField;

    private final UserAccessFactory userAccessFactory;

    public LoginForm(String id, UserAccessFactory userAccessFactory) {
        super(id);
        this.userAccessFactory = userAccessFactory;

        this.emailField = new TextField<>(WicketConstants.EMAIL, Model.of(""));
        this.passwordField = new PasswordTextField(WicketConstants.PASSWORD, Model.of(""));

        add(emailField);
        add(passwordField);
    }

    @Override
    public final void onSubmit() {

        PageParameters pageParameters = new PageParameters();

        try {

            UserAccess userAccess = userAccessFactory.create();

            if (!logInByEmail(pageParameters, userAccess)) {
                couldNotLogIn(pageParameters);
            }

        } catch (UserAccessCreationException e) {
            pageParameters.add(WicketConstants.STATUS, "internal error: '" + e.getMessage() + "'");
        }

        setResponsePage(HomePage.class, pageParameters);

    }

    private boolean logInByEmail(PageParameters pageParameters, UserAccess userAccess) {
        String email = emailField.getModelObject();
        String password = passwordField.getModelObject();
        User user = User.of(Email.of(email), Password.of(password));
        if (userAccess.existsUser(user)) {
            pageParameters.add(WicketConstants.STATUS, "logged in by email '" + email + "'");
            return true;
        }
        return false;
    }

    private void couldNotLogIn(PageParameters pageParameters) {
        pageParameters.add(WicketConstants.STATUS, "could not log in");
    }

}
