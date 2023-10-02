package dev.lpa;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println("Default Locale = " + Locale.getDefault());

        //creation of new Locale
        Locale en = new Locale("en");
        Locale enAU = new Locale("en", "AU");
        Locale enCA = new Locale("en", "CA");

        //setting new language and region
        Locale enIN = new Locale.Builder().setLanguage("en").setRegion("IN").build();
        Locale enNZ = new Locale.Builder().setLanguage("en").setRegion("NZ").build();

        //formatting DateTime to see the differences in english speaking countries and their DateTime preferences
        var dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        for (var locale : List.of(
                Locale.getDefault(), Locale.US, en, enAU, enCA, Locale.UK, enNZ, enIN)) {
            System.out.println(locale.getDisplayName() + "= " + LocalDateTime.now().format(dtf.withLocale(locale)));
        }

        //using non-english Locale
        DateTimeFormatter wdayMonth = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy"); //characters found in 
        //the  javadoc (hover over DateTimeFormatter)

        LocalDate May5 = LocalDate.of(2020, 5, 5);
        System.out.println("-----------------");
        //List of selected locale versions with local formats of Date
        for (var locale : List.of(
                Locale.CANADA, Locale.CANADA_FRENCH, Locale.FRANCE, Locale.GERMANY, Locale.TAIWAN, Locale.JAPAN,
                Locale.ITALY)) {
            System.out.println(locale.getDisplayName() + ": " + locale.getDisplayName(locale) +
                    "=\n\t" + May5.format(wdayMonth.withLocale(locale)));
            System.out.printf(locale, "\t%1$tA, %1$tB %1$te, %1$tY %n", May5);//same result as above
            System.out.print(String.format(locale, "\t%1$tA, %1$tB %1$te, %1$tY %n", May5)); //same result as above

            //what a decimal number looks like in multiple languages.
            NumberFormat decimalInfo = NumberFormat.getNumberInstance(locale);
            decimalInfo.setMaximumFractionDigits(6);
            System.out.println(decimalInfo.format(123456789.123456));

            //what currency looks like in multiple languages.
            NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
            Currency localCurrency = Currency.getInstance(locale);
            System.out.println(currency.format(555.555) + " [" +
                    localCurrency.getCurrencyCode() + "] " +
                    localCurrency.getDisplayName(locale) + "/" +
                    localCurrency.getDisplayName());
        }
        System.out.println("*".repeat(30));

        //considering keyboard input, and the Scanner class, and locales
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the loan amount: ");
        //BigDecimal myLoan = new BigDecimal("1,000.50");
        scanner.useLocale(Locale.ITALY); //changing locale used by scanner
        BigDecimal myLoan = scanner.nextBigDecimal();
        NumberFormat decimalInfo = NumberFormat.getNumberInstance(Locale.ITALY); //to show the info in Locale.ITALY
        // format
        System.out.println("My Loan " + decimalInfo.format(myLoan));
    }
}
