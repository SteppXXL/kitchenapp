package com.mercateo.kitchenapp.pages.subscription;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.string.StringValue;

import com.mercateo.kitchenapp.data.Email;
import com.mercateo.kitchenapp.data.Offer;
import com.mercateo.kitchenapp.db.OffersDao;
import com.mercateo.kitchenapp.pages.general.GeneralPageSignInNeeded;
import com.mercateo.kitchenapp.sso.authorization.UserWebSession;

public class OfferPage extends GeneralPageSignInNeeded {

    private static final long serialVersionUID = 1L;

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    @Inject
    private OffersDao offers;

    private LocalDate from;

    private LocalDate to;

    private Email email;

    public OfferPage(PageParameters params) {
        super(params);
    }

    @Override
    protected void onBeforeRender() {

        super.onBeforeRender();

        PageParameters params = getPageParameters();

        this.from = from(params);
        this.to = to(params);
        this.email = UserWebSession.get().getUser().getEmail();

        add(new Label("date", new Model<>(LocalDateTime.now())));
        add(new OfferForm("subscriptionsForm", from, to));
        // add(new Label("from", new Model<>(from)));
        // add(new Label("to", new Model<>(to)));

        Optional<Offer> optional = offers.get(from);
        if (optional.isPresent()) {
            add(new OfferPanelWithData("offerPanel", optional.get()));
        } else {
            add(new EmptyOfferPanel("offerPanel", from));
        }

    }

    private LocalDate from(PageParameters params) {
        return readParams(params, "from", LocalDate.now());
    }

    private LocalDate to(PageParameters params) {
        return readParams(params, "to", LocalDate.now().plusDays(5));
    }

    private LocalDate readParams(PageParameters params, String name, LocalDate defaultValue) {
        StringValue stringValue = params.get(name);
        if (stringValue.isEmpty()) {
            return defaultValue;
        }
        return LocalDate.parse(stringValue.toString(), FORMATTER);
    }

}
