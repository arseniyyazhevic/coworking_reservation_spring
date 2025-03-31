package util;

import entity.Booking;
import entity.CoworkingSpace;
import enums.TypeOfWorkspaces;
import ui.ConsoleOutput;

import java.io.IOException;
import java.sql.*;

public class CoworkingSpaceDBUtils {
    public static void createCoworkingSpace(CoworkingSpace coworkingSpace) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement("INSERT INTO public.coworking_spaces (id, name, type_of_workspace, price_dollars, availability_status) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setLong(1, coworkingSpace.getId());
            preparedStatement.setString(2, coworkingSpace.getName());
            preparedStatement.setString(3, TypeOfWorkspaces.getStringFromTypeOfWorkspace(coworkingSpace.getTypeOfWorkspaces()));
            preparedStatement.setInt(4, coworkingSpace.getPriceInDollars());
            preparedStatement.setBoolean(5, coworkingSpace.isAvailabilityStatus());
            preparedStatement.executeUpdate();
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
    }


    public static void updateCoworkingSpace(int id, CoworkingSpace coworkingSpace) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement("UPDATE public.coworking_spaces SET  name = ? , type_of_workspace = ?, price_dollars = ?, availability_status = ? WHERE id = ?")) {
            preparedStatement.setString(1, coworkingSpace.getName());
            preparedStatement.setString(2, TypeOfWorkspaces.getStringFromTypeOfWorkspace(coworkingSpace.getTypeOfWorkspaces()));
            preparedStatement.setInt(3, coworkingSpace.getPriceInDollars());
            preparedStatement.setBoolean(4, coworkingSpace.isAvailabilityStatus());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
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
    }


    public static void deleteCoworkingSpace(int id) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.
                     prepareStatement("DELETE FROM public.coworking_spaces WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
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
    }

    public static void readCoworkingSpaces() {
        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM public.coworking_spaces")) {
            System.out.printf("%-5s | %-20s | %-15s | %-10s | %-15s%n",
                    "ID", "Name", "Type", "Price ($)", "Status");
            System.out.println("--------------------------------------------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type_of_workspace");
                int price = rs.getInt("price_in_dollars");
                Boolean status = rs.getBoolean("availability_status");

                System.out.printf("%-5d | %-20s | %-15s | %-10d | %-15s%n",
                        id, name, type, price, status);
            }
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
    }

    public static CoworkingSpace getCoworkingSpace(int id) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM public.coworking_spaces WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return new CoworkingSpace(
                    rs.getString("name"),
                    TypeOfWorkspaces.getTypeOfWorkspaceFromUserInput(rs.getString("type_of_workspace")),
                    rs.getInt("price_dollars"),
                    rs.getBoolean("availability_status")
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
