/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.mercateo.kitchenapp.panels.menu;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import com.mercateo.kitchenapp.WicketGuiceHelper;
import com.mercateo.kitchenapp.pages.PagesRegistry;
import com.mercateo.kitchenapp.sso.authorization.AuthenticatedWebSession;

public class MenuPanel extends Panel {

    public MenuPanel(String id) {
        super(id);

        PagesRegistry pages = WicketGuiceHelper.get().getInstance(PagesRegistry.class);

        add(link("profile", pages.getProfilePage()));
        add(link("admin", pages.getAdminPage()));
        add(link("editor", pages.getEditorPage()));
        if (AuthenticatedWebSession.get().isSignedIn()) {
            Class<? extends WebPage> page = pages.getHomePage();
            add(new Link("logOut") {
                @Override
                public void onClick() {
                    AuthenticatedWebSession.get().invalidate();
                    setResponsePage(page);
                }
            });
        } else {
            add(new Label("logOut"));
        }

    }

    private Link link(String id, Class<? extends WebPage> page) {
        return new Link(id) {
            @Override
            public void onClick() {
                setResponsePage(page);
            }
        };
    }

}