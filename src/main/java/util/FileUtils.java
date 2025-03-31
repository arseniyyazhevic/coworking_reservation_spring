package util;

import entity.Booking;
import entity.CoworkingSpace;
import ui.ConsoleOutput;

import java.io.*;
import java.util.HashMap;
import java.util.Optional;

public class FileUtils {
    public static void saveBookingsToFile(String fileName, HashMap<Integer, Booking> allBookings) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(allBookings);
        } catch (IOException e) {
            ConsoleOutput.println("Error: could not enter booking data into the database");
        }
    }

    public static void saveCoworkingSpacesToFile(String fileName, HashMap<Integer, CoworkingSpace> coworkingSpaces) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(coworkingSpaces);
        } catch (IOException e) {
            ConsoleOutput.println("Error: could not enter coworking data into the database");
        }
    }

    public static Optional<HashMap<Integer, CoworkingSpace>> loadCoworkingSpacesFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return Optional.ofNullable((HashMap<Integer, CoworkingSpace>) ois.readObject());
        } catch (ClassNotFoundException | IOException e) {
            ConsoleOutput.println("Error: Program can't load information about coworking spaces, program starts without data about it");
            return Optional.of(new HashMap<>());
        }

    }

    public static Optional<HashMap<Integer, Booking>> loadBookingsFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return Optional.ofNullable((HashMap<Integer, Booking>) ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            ConsoleOutput.println("Error: Program can't load information about bookings, program starts without data about it");
            return Optional.of(new HashMap<>());
        }

    }

}
