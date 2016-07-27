package ru.disdev.services.google;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.disdev.model.User;
import ru.disdev.services.UserService;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Dislike on 19.07.2016.
 */
@Component
public class GCMServiceImpl implements GCMService {

    private static final Logger LOGGER = LogManager.getLogger(GCMServiceImpl.class);

    private final Sender sender = new Sender("AIzaSyAgm-urnVbhrvtpC7dL4iFoaV5IDyx0e8s");

    @Autowired
    private UserService userService;

    @Override
    public void registerNewDevice(String registrationId, String userId) {
        User user = userService.getById(userId);
        if (user != null) {
            user.setDeviceId(registrationId);
            userService.updateUser(user);
        }
    }

    @Override
    public void sendNotificationToUser(String userId, String text) {
        User user = userService.getById(userId);
        if (user == null)
            return;
        final String deviceId = user.getDeviceId();
        if (deviceId == null)
            return;
        Message message = new Message.Builder().addData("text", text).build();
        try {
            Result result = sender.sendNoRetry(message, deviceId);
            String newDeviceId = result.getCanonicalRegistrationId();
            if (newDeviceId != null && !deviceId.equals(newDeviceId)) {
                user.setDeviceId(newDeviceId);
                userService.updateUser(user);
            }
        } catch (IOException e) {
            LOGGER.error("Error while sending message to GCM Server", e);
        }
    }
}
