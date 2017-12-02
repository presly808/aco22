package ua.artcode.market.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logging extends IOException {

        private static Logging instance;

        private Logging(String stringForLoging) throws IOException  {

            write(stringForLoging);
        }

        public static Logging getInstance() throws IOException {

            if (instance == null) {

                synchronized (Logging.class) {
                    if (instance == null) instance = new Logging("Create LoggingInstance");
                }
            }
            return instance;
        }

        public void write(String messege) throws IOException {

            File file = new File("logging.txt");

            if (!file.exists()) {

                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.write(messege);
            fileWriter.flush();
            fileWriter.close();
        }
}

