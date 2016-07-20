package ru.disdev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.disdev.network.StudentNoteServer;

/**
 * Created by Dislike on 18.07.2016.
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        applicationContext.getBean(StudentNoteServer.class).start();
    }
}
