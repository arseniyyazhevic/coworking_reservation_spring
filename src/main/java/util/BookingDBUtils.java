package util;

import entity.Booking;
import entity.CoworkingSpace;
import enums.TypeOfWorkspaces;
import service.BookingService;
import ui.ConsoleOutput;

import java.io.IOException;
import java.sql.*;

public class BookingDBUtils {
    public static void createBooking(Booking booking) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement("INSERT INTO public.bookings (id, customer_name, booking_date, time_interval, coworking_space_id) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setLong(1, booking.getId());
            preparedStatement.setString(2, booking.getCustomerName());
            preparedStatement.setDate(3, Date.valueOf(booking.getDate()));
            preparedStatement.setString(4, booking.getStartAndEndOfBookingTime());
            preparedStatement.setLong(5, booking.getCoworkingSpace().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            ConsoleOutput.println("SQL Error: could not enter bookings data into the database");
            e.printStackTrace();
            System.err.println("SQLException: " + e.getMessage());
        } catch (IOException e) {
            ConsoleOutput.println("IO Error: could not load properties file");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ConsoleOutput.println("Error: PostgreSQL driver not found");
            e.printStackTrace();
        }
    }


    public static void updateBooking(int id, Booking booking) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement("UPDATE public.bookings SET customer_name = ?, booking_date = ?, time_interval = ?, coworking_space_id = ? WHERE id = ?")) {
            preparedStatement.setString(1, booking.getCustomerName());
            preparedStatement.setDate(2, Date.valueOf(booking.getDate()));
            preparedStatement.setString(3, booking.getStartAndEndOfBookingTime());
            preparedStatement.setLong(4, booking.getCoworkingSpace().getId());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            ConsoleOutput.println("SQL Error: could not enter bookings data into the database");
            e.printStackTrace();
            System.err.println("SQLException: " + e.getMessage());
        } catch (IOException e) {
            ConsoleOutput.println("IO Error: could not load properties file");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ConsoleOutput.println("Error: PostgreSQL driver not found");
            e.printStackTrace();
        }
    }

    public static void deleteBooking(int id) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement("DELETE FROM public.bookings WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            ConsoleOutput.println("SQL Error: could not enter bookings data into the database");
            e.printStackTrace();
            System.err.println("SQLException: " + e.getMessage());
        } catch (IOException e) {
            ConsoleOutput.println("IO Error: could not load properties file");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ConsoleOutput.println("Error: PostgreSQL driver not found");
            e.printStackTrace();
        }
    }

    public static void readBookings() {
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM public.bookings")) {
            System.out.printf("%-5s | %-20s | %-12s | %-15s | %-16s%n",
                    "ID", "Customer Name", "Date", "Time Interval", "Coworking Space ID");
            System.out.println("-----------------------------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String customerName = rs.getString("customer_name");
                Date date = rs.getDate("booking_date");
                String timeInterval = rs.getString("time_interval");
                int coworkingSpaceId = rs.getInt("coworking_space_id");

                System.out.printf("%-5d | %-20s | %-12s | %-15s | %-16d%n",
                        id, customerName, date, timeInterval, coworkingSpaceId);
            }
        } catch (SQLException e) {
            ConsoleOutput.println("SQL Error: could not enter bookings data into the database");
            e.printStackTrace();
            System.err.println("SQLException: " + e.getMessage());
        } catch (IOException e) {
            ConsoleOutput.println("IO Error: could not load properties file");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ConsoleOutput.println("Error: PostgreSQL driver not found");
            e.printStackTrace();
        }
    }

    public static Booking getBooking(int id) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.bookings WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return new Booking(
                    rs.getString("customer_name"),
                    rs.getString("time_interval"),
                    rs.getDate("booking_date").toLocalDate(),
                    rs.getInt("coworking_space_id")
            );
        } catch (SQLException e) {
            ConsoleOutput.println("SQL Error: could not enter coworking space data into the database");
            e.printStackTrace();
            System.err.println("SQLException: " + e.getMessage());
        } catch (IOException e) {
            ConsoleOutput.println("IO Error: could not load properties file");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            ConsoleOutput.println("Error: PostgreSQL driver not found");
            e.printStackTrace();
        }
        return null;
    }


}
