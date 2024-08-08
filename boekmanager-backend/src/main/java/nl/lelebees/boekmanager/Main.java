package nl.lelebees.boekmanager;

import org.glassfish.jersey.logging.LoggingFeature;

import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME);
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}