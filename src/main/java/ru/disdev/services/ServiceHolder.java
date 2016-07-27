package ru.disdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.disdev.services.google.AuthenticationService;
import ru.disdev.services.google.GCMService;

import javax.annotation.PostConstruct;

/**
 * Created by Dislike on 19.07.2016.
 */
@Component
public class ServiceHolder {

    private static GCMService gcmService;
    private static AuthenticationService authenticationService;
    private static GroupService groupService;

    @Autowired
    private GCMService gcmServiceLocal;
    @Autowired
    private AuthenticationService authenticationServiceLocale;
    @Autowired
    private GroupService groupServiceLocal;

    public static GCMService getGcmService() {
        return gcmService;
    }

    public static AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public static GroupService getGroupService() {
        return groupService;
    }

    @PostConstruct
    private void init() {
        gcmService = gcmServiceLocal;
        authenticationService = authenticationServiceLocale;
        groupService = groupServiceLocal;
    };

}
