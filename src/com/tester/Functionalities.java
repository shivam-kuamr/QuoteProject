package com.tester;

import com.enity.User;
import com.services.QuoteServices;
import com.services.UserServices;

import java.sql.SQLException;
import java.util.Scanner;

public class Functionalities {
    public static Scanner sc = new Scanner(System.in);
    static UserServices userServices;
    static QuoteServices quoteServices;

    static {
        try {
            userServices = new UserServices();
            quoteServices = new QuoteServices();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String mainMenu() {
        String choice;
        System.out.println("\nEnter your choice : ");
        System.out.println("0. Exit");
        System.out.println("1. Sign In");
        System.out.println("2. Sign Up");
        choice = sc.next();
        return choice;
    }

    public static void mainMenuSwitch() throws SQLException {
        String choice;
        do {
            choice = mainMenu();
            switch (choice) {
                case "0":
                    System.out.println("Visit Again");
                    userServices.close();
                    quoteServices.close();
                    System.exit(0);
                    break;
                case "1":
                    User user = userServices.signIn();
                    if (user != null)
                        sunMenuSwitch(user);
                    break;
                case "2":
                    userServices.signUp();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!(choice.equals("0")));
    }

    public static String subMenu() {
        String choice;
        System.out.println("\nEnter your choice : ");
        System.out.println("0. Back to Main menu");
        System.out.println("1. ChangeProﬁle");
        System.out.println("2. ChangePassword");
        System.out.println("3. displayAllQuotes");
        System.out.println("4. displayQuotesOfUser");
        System.out.println("5. addQuote");
        System.out.println("6. editQuote");
        System.out.println("7. deleteQuoteById");
        choice = sc.next();
        return choice;
    }

    public static void sunMenuSwitch(User user) throws SQLException {
        String choice;
        do {
            choice = subMenu();
            switch (choice) {
                case "0":
                    System.out.println("Logging Out " + user.getFirstName() + " " + user.getLastName());
                    break;
                case "1":
                    break;
                case "2":
                    int cnt = userServices.changePassword(user.getId());
                    if (cnt == 1) {
                        System.out.println("Please sign in using new password");
                        choice = "0";
                    }
                    break;
                case "3":
                    System.out.println("------------------------------------------------All quotes------------------------------------------------");
                    quoteServices.displayAllQuotes();
                    break;
                case "4":
                    System.out.println("-------------------------------------------Quotes of " + user.getFirstName() + " " + user.getLastName() + "-------------------------------------------");
                    quoteServices.displayAllQuotesOfUser(user.getId());
                    break;
                case "5":
                    quoteServices.addQuotes(user.getId());
                    break;
                case "6":
                    quoteServices.displayAllQuotesOfUser(user.getId());
                    quoteServices.editQuote(user.getId());
                    break;
                case "7":
                    quoteServices.displayAllQuotesOfUser(user.getId());
                    quoteServices.deleteQuote(user.getId());
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (!(choice.equals("0")));
    }
}
