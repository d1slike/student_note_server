package ru.disdev.services.google;

import ru.disdev.model.User;

/**
 * Created by Dislike on 20.07.2016.
 */
public interface AuthenticationService {
    User authenticateUserWithToken(String tokenId);
}
