package ru.disdev.services.google;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Dislike on 19.07.2016.
 */
@Component
public class GCMServiceImpl implements GCMService {

    private static final Logger LOGGER = LogManager.getLogger(GCMServiceImpl.class);

    private final Map<Long, String> registeredUsers = new ConcurrentHashMap<>();
    private final Sender sender = new Sender("AIzaSyAgm-urnVbhrvtpC7dL4iFoaV5IDyx0e8s");

    @Override
    public void registerNewDevice(String registrationId, long userId) {
        registeredUsers.put(userId, registrationId);
    }

    @Override
    public void sendNotificationToUser(long userId, String text) {
        final String deviceId = registeredUsers.get(userId);
        if (deviceId == null)
            return;
        Message message = new Message.Builder().addData("text", text).build();
        try {
            Result result = sender.sendNoRetry(message, deviceId);
            String newDeviceId = result.getCanonicalRegistrationId();
            if (newDeviceId != null && !deviceId.equals(newDeviceId))
                registeredUsers.put(userId, newDeviceId);
        } catch (IOException e) {
            LOGGER.error("Error while sending message to GCM Server", e);
        }
    }
}
