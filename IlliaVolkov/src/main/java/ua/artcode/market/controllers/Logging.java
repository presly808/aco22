package ua.artcode.market.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logging extends IOException {

    private static Logging entity;

    private Logging(String messageForLoging) throws IOException  {

        fixEvent(messageForLoging);
    }

    public void fixEvent(String messege) throws IOException {

        File file = new File("logging.txt");

        if (!file.exists()) {

            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file,true);

        System.out.println(messege);

        fileWriter.write("" + new Date() + "--- " + messege + "\n");
        fileWriter.flush();
        fileWriter.close();
    }

    public static Logging getEntity() throws IOException {

        if (entity == null) {

            synchronized (Logging.class) {
                if (entity == null) {
                    entity = new Logging("Create LoggingInstance");
                }
            }
        }
        return entity;
    }
}

