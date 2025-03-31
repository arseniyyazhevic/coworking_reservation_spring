package ui;

import entity.CoworkingSpace;
import entity.roles.User;
import enums.TypeOfWorkspaces;
import exception.ValidationException;
import service.BookingService;
import service.CoworkingSpaceService;
import validation.BookingValidator;
import validation.CoworkingSpaceValidator;
import validation.UserValidator;

import java.security.cert.CertPath;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    private final BookingValidator bookingValidator;
    private final CoworkingSpaceValidator coworkingSpaceValidator;
    private final UserValidator userValidator;

    public ConsoleInput(CoworkingSpaceValidator coworkingSpaceValidator, BookingValidator bookingValidator, UserValidator userValidator) {
        this.coworkingSpaceValidator = coworkingSpaceValidator;
        this.bookingValidator = bookingValidator;
        this.userValidator = userValidator;
    }



    public String getString(String prompt) {
        ConsoleOutput.print(prompt);
        String str = scanner.next();
        scanner.nextLine();
        return str;
    }

    public int getInt(String prompt) {
        ConsoleOutput.print(prompt);
        while (!scanner.hasNextInt()) {
            ConsoleOutput.print("Please enter a number: ");
            scanner.next();
        }
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public Long getLong(String prompt) {
        ConsoleOutput.print(prompt);
        while (!scanner.hasNextLong()) {
            ConsoleOutput.print("Please enter a number: ");
            scanner.next();
        }
        Long number = scanner.nextLong();
        scanner.nextLine();
        return number;
    }

    public String getLine(String prompt) {
        ConsoleOutput.print(prompt);
        return scanner.nextLine();
    }

    public TypeOfWorkspaces getTypeOfCoworkingSpace(String prompt) {
        while (true) {
            String strTypeOfWorkspaces = (getLine(prompt));
            try {
                return coworkingSpaceValidator.validateTypeOfWorkspaceUserInput(strTypeOfWorkspaces);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }

    public int getPrice(String prompt) {
        while (true) {
            int price = getInt(prompt);
            try {
                return coworkingSpaceValidator.validatePriceOfCoworkingFromUser(price);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }

    public Boolean getAvailableStatus(String prompt) {
        while (true) {
            String strAvailableStatus = getString(prompt);
            try {
                return coworkingSpaceValidator.validateAvailableStatus(strAvailableStatus);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }



    public LocalDate getDate(String prompt) {
        while (true) {
            String strLocalDate = getString(prompt);
            try {
                return bookingValidator.validateDate(strLocalDate);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }

    public String getStartAndEndOfBookingTime(String prompt) {
        while (true) {
            String startAndEndOfBookingTime = getString(prompt);
            try {
                return bookingValidator.validateStartAndEndOfBookingTime(startAndEndOfBookingTime);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }
    public Long getIdBook(String prompt) {
        while (true) {
            Long id = getLong(prompt);
            try {
                return bookingValidator.validateIdBook(id);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }

    public Long getIdCoworkingSpace(String prompt){
        while (true) {
            Long id = getLong(prompt);
            try {
                return coworkingSpaceValidator.validateIdCoworkingSpace(id);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }


    public Long getId(String prompt) {
        while (true) {
            Long id = getLong(prompt);
            try {
                return coworkingSpaceValidator.validateId(id);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }

    public String getName(String prompt) {
        while (true) {
            String name = getLine(prompt);
            try {
                return bookingValidator.validateName(name);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }

    public User getUserRoleInput(String prompt) {
        while (true) {
            String userInput = getString(prompt);
            try {
                return userValidator.validateRoleInput(userInput);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }

    public String getUserLoginInput(String prompt) {
        while (true) {
            String userInput = getString(prompt);
            try {
                return userValidator.validateUserLogin(userInput);
            } catch (ValidationException e) {
                ConsoleOutput.println(e.getMessage());
            }
        }
    }
}
