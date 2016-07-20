package ru.disdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.disdev.services.google.GCMService;

import javax.annotation.PostConstruct;

/**
 * Created by Dislike on 19.07.2016.
 */
@Component
public class ServiceHolder {

    public static GCMService gcmService;

    @Autowired
    private GCMService gcmServiceLocal;

    @PostConstruct
    private void init() {
        ServiceHolder.gcmService = gcmServiceLocal;
    };

}
