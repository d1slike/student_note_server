package ru.disdev.network.packets.components;

import org.springframework.data.util.ReflectionUtils;
import ru.disdev.network.packets.ClientPacket;
import ru.disdev.network.packets.in.AuthRequest;
import ru.disdev.utils.JavaReflectionUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Dislike on 18.07.2016.
 */
public class InboundPacketsFactory {

    private static final Constructor<?>[] clientPacketsConstructors = new Constructor[Byte.MAX_VALUE];

    public static ClientPacket create(final byte key) throws IllegalAccessException, InvocationTargetException, InstantiationException {
            return (ClientPacket) clientPacketsConstructors[key].newInstance();
    }

    public static void init() {
        JavaReflectionUtil
                .getClassesForPackage(AuthRequest.class.getPackage())
                .stream()
                .filter(aClass -> aClass.isAnnotationPresent(ClientPacketIdentifier.class) && ClientPacket.class.isAssignableFrom(aClass))
                .forEach(aClass -> {
                    ClientPacketIdentifier identifier = aClass.getAnnotation(ClientPacketIdentifier.class);
                    clientPacketsConstructors[identifier.value()] = aClass.getConstructors()[0];
                });
    }


}
