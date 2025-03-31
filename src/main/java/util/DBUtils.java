package util;

import entity.Booking;
import entity.CoworkingSpace;
import enums.TypeOfWorkspaces;
import ui.ConsoleOutput;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.Optional;
import java.util.Properties;

public class DBUtils {
    private static final String PROPERTIES_OF_DB_CONNECTION_PATH = "src/main/resources/database.properties";

    public static Optional<HashMap<Long, CoworkingSpace>> loadCoworkingSpacesFromDB() {
        HashMap<Long, CoworkingSpace> dbDataCoworkingSpaces = new HashMap<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.coworking_spaces");
            while(rs.next()) {
                CoworkingSpace coworkingSpace = new CoworkingSpace(
                        rs.getString("name"),
                        TypeOfWorkspaces.getTypeOfWorkspaceFromUserInput(rs.getString("type_of_workspace")),
                        rs.getInt("price_dollars"),
                        rs.getBoolean("availability_status")
                );
                dbDataCoworkingSpaces.put(rs.getLong("id"), coworkingSpace);
            }
            statement.close();
            rs.close();
            return Optional.of(dbDataCoworkingSpaces);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            ConsoleOutput.println("Error: Program can't load information about coworking spaces, program starts without data about it");
            return Optional.of(new HashMap<>());
        }

    }

    public static Optional<HashMap<Long, Booking>> loadBookingsFromDB() {
        HashMap<Long, Booking> dbDataBookings = new HashMap<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM public.bookings");
            while(rs.next()) {
                Booking booking = new Booking(
                        rs.getString("customer_name"),
                        rs.getString("time_interval"),
                        rs.getDate("booking_date").toLocalDate(),
                        rs.getInt("coworking_space_id")
                );
                dbDataBookings.put(rs.getLong("id"), booking);
            }
            statement.close();
            rs.close();
            return Optional.of(dbDataBookings);
        } catch (SQLException | IOException | ClassNotFoundException e) {
            ConsoleOutput.println("Error: Program can't load information about bookings, program starts without data about it");
            return Optional.of(new HashMap<>());
        }

    }
    public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get(PROPERTIES_OF_DB_CONNECTION_PATH))){
            properties.load(in);
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

    public static int getMaxIdFromCoworkingSpaces() {
        try(Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM public.coworking_spaces")){
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getMaxIdFromBookings() {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM public.bookings")){
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
