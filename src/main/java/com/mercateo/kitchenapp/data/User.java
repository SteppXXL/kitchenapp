package com.mercateo.kitchenapp.data;

import java.io.Serializable;
import java.util.Set;

import com.mercateo.kitchenapp.sso.roles.UserRole;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class User implements Serializable {

    @NonNull
    private Email email;

    private Password password;

    private Set<UserRole> userRoles;

}
