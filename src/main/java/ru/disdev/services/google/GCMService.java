package ru.disdev.services.google;

/**
 * Created by Dislike on 19.07.2016.
 */
public interface GCMService {
    void registerNewDevice(String registrationId, long userId);
    void sendNotificationToUser(long userId, String message);

}
