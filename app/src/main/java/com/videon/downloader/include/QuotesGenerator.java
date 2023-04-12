package com.videon.downloader.include;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class QuotesGenerator {

    public static class Quote {

        private String text;
        private String author;

        public Quote(String text, String author) {
            this.text = text;
            this.author = author;
        }

        public String getAuthor() {
            return author;
        }

        public String getText() {
            return text;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public ArrayList<Quote> quotes;
    public Random randIndex;
    public int currRandIndex;
    public InputStream quotesFile;

    public QuotesGenerator(InputStream file) {
        this.quotesFile = file;
        quotes = new ArrayList<>();
        readQuotesFile();
        randIndex = new Random();
        currRandIndex = -1;
    }

    public Quote getRandomQuote() {
        int index = randIndex.nextInt(quotes.size());
        while (index == currRandIndex) {
            index = randIndex.nextInt(quotes.size());
        }
        Quote newQuote = quotes.get(index);
        currRandIndex = index;
        return newQuote;
    }

    public void readQuotesFile() {
        Scanner in;
        try {
            in = new Scanner(quotesFile);
            while (in.hasNextLine()) {
                quotes.add(new Quote(in.nextLine(), in.nextLine()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(quotes);
    }

}
