package com.shockn745.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Kempenich Florian
 */
public class User {

    private String username;

    public User(String username) {
        setUsername(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        checkNotNull(username);
        checkArgument(!username.isEmpty());
        this.username = username;
    }
}
