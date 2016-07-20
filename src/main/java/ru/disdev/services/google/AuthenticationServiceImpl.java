package ru.disdev.services.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.disdev.model.User;
import ru.disdev.services.UserService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

/**
 * Created by Dislike on 20.07.2016.
 */
@Component
public class AuthenticationServiceImpl implements AuthenticationService {

    private final GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
            .setAudience(Collections.singletonList("608114583618-kls0v57s34tnrd7shfntlaptm0tqnbcr.apps.googleusercontent.com"))
            .setIssuer("https://accounts.google.com")
            .build();


    @Autowired
    private UserService service;

    @Override
    public User authenticateUserWithToken(String tokenId) {
        User user = null;
        try {
           GoogleIdToken googleIdToken = verifier.verify(tokenId);
            if (googleIdToken != null) {
                GoogleIdToken.Payload payload = googleIdToken.getPayload();
                String userId = payload.getSubject();
                if (service.checkExist(userId))
                    user = service.getById(userId);
                else {
                    user = new User(userId, payload.getEmail(), (String) payload.get("name"));
                    service.storeNewUser(user);
                }
            }
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }
}
