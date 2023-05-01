package com.services;

import com.doa.QuoteDao;
import com.enity.Quote;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuoteServices {
    QuoteDao quoteDao = new QuoteDao();
    Scanner sc = new Scanner(System.in);

    public QuoteServices() throws SQLException {
    }

    public void close() throws SQLException {
        quoteDao.close();
    }

    public void displayAllQuotes() {
        List<Quote> list = quoteDao.displayAll();
        for (Quote quote : list) {
            System.out.printf("%-5d %-30s %-20s %-5d %-15s\n", quote.getId(), quote.getQuote(), quote.getAuthor(), quote.getUserId(), quote.getCreated_at());
        }
    }


    public void addQuotes(int id) throws SQLException {
        System.out.print("Enter Quote : ");
        String quote = sc.nextLine();
        System.out.print("Enter Author : ");
        String author = sc.nextLine();
        int cnt = quoteDao.add(quote, author, id);
        if (cnt == 1) {
            System.out.println("New Quote added successfully");
        }
    }

    public void displayAllQuotesOfUser(int id) throws SQLException {
        List<Quote> list = new ArrayList<>();
        list = quoteDao.userQuotes(id);
        for (Quote quote : list) {
            System.out.printf("%-5d %-30s %-20s %-5d %-15s\n", quote.getId(), quote.getQuote(), quote.getAuthor(), quote.getUserId(), quote.getCreated_at());
        }
    }

    public void editQuote(int id) throws SQLException {
        System.out.print("Enter Quote id You want to edit : ");
        int quoteId = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Quote : ");
        String quote = sc.nextLine();
        System.out.print("Enter Author : ");
        String author = sc.nextLine();
        int cnt = quoteDao.edit(quote, author, id, quoteId);
        if (cnt == 1) {
            System.out.println("Quote Updated added successfully");
        } else {
            System.out.println("You can edit your own quote only");
        }
    }

    public void deleteQuote(int id) throws SQLException {
        System.out.print("Enter Quote id You want to delete : ");
        int quoteId = sc.nextInt();
        int cnt = quoteDao.delete(id, quoteId);
        if (cnt == 1) {
            System.out.println("Quote deleted added successfully");
        } else {
            System.out.println("You can delete your own quote only");
        }
    }
}
