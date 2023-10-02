package dev.lpa;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        double policyAmount = 100_000_000;
        int beneficiaries = 3;
        float percentageFloat = 1.0f / beneficiaries;
        double percentage = 1.0 / beneficiaries;

        System.out.printf("Payout = %,.2f%n", policyAmount * percentageFloat);
        System.out.printf("Payout = %,.2f%n", policyAmount * percentage);

        double totalUsingFloat = policyAmount - ((policyAmount * percentageFloat) * beneficiaries);
        System.out.printf("totalUsingFloat: %,.2f%n", totalUsingFloat);

        double total = policyAmount - ((policyAmount * percentage) * beneficiaries);
        System.out.printf("total: %,.2f%n", total);

        System.out.println("<<<------------ BigDecimal ---------------->>>\n");

        //BigDecimal is recommended for financial tasks
        //using strings
        String[] tests = {"15.456", "8", "10000.000001", ".123"};
        BigDecimal[] bds = new BigDecimal[tests.length];
        Arrays.setAll(bds, i -> new BigDecimal(tests[i]));

        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        for (var bd : bds) {
            System.out.printf("%-15s %-15d %-8d %d %n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        }

        //using doubles
        double[] doubles = {15.456, 8, 10000.000001, .123};
        Arrays.setAll(bds, i -> BigDecimal.valueOf(doubles[i]));
        System.out.println("-------------------------------------");
        System.out.printf("%-14s %-15s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
        for (var bd : bds) {
            System.out.printf("%-15s %-15d %-8d %d %n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
            //almost every method you use on BigDecimal, will need to be assigned to some variable.
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            System.out.printf("%-15s %-15d %-8d %d %n", bd, bd.unscaledValue(), bd.scale(), bd.precision());
        }

        //showing precision of BigDecimal
//        System.out.println("-------------------------------------");
//        BigDecimal test1 = new BigDecimal("1.1111122222333334444455555");
//        BigDecimal test2 = BigDecimal.valueOf(1.1111122222333334444455555);
//        System.out.println("-------------------------------------");
//        System.out.printf("%-30s %-30s %-8s %s%n", "Value", "Unscaled Value", "Scale", "Precision");
//        System.out.printf("%-30s %-30d %-8d %d %n", test1, test1.unscaledValue(), test1.scale(), test1.precision());
//        System.out.printf("%-30s %-30d %-8d %d %n", test2, test2.unscaledValue(), test2.scale(), test2.precision());

        BigDecimal policyPayout = new BigDecimal("100000000.00");
        System.out.printf("%-15s %-15d %-8d %d %n", policyPayout, policyPayout.unscaledValue(), policyPayout.scale(), policyPayout.precision());

        //negative scale
        BigDecimal policyPayout1 = new BigDecimal("100e6");
        System.out.printf("%-15s %-15d %-8d %d %n", policyPayout1, policyPayout1.unscaledValue(), policyPayout1.scale()
                , policyPayout1.precision());

        //percentage value
        BigDecimal percent = BigDecimal.ONE.divide(BigDecimal.valueOf(beneficiaries),
                //MathContext.UNLIMITED is the default math context used, when you don't specify anything
                //MathContext.UNLIMITED);
                //DECIMAL128 gives me a precision of 34 digits, the zero before the decimal is not counted.
                //MathContext.DECIMAL128);
                //to round up the last digit, and I want a precision of 60.
                new MathContext(60, RoundingMode.UP));
        System.out.println(percent);

        //amount, for each beneficiary, using more BigDecimal math
        BigDecimal checkAmount = policyPayout.multiply(percent);
        System.out.printf("%.2f%n", checkAmount);
        checkAmount = checkAmount.setScale(2, RoundingMode.HALF_UP);
        System.out.printf("%-15s %-15d %-8d %d %n", checkAmount, checkAmount.unscaledValue(), checkAmount.scale(), checkAmount.precision());

        //total amount of the checks
        BigDecimal totalChecksAmount = checkAmount.multiply(BigDecimal.valueOf(beneficiaries));
        System.out.printf("Combined: %.2f%n", totalChecksAmount);
        System.out.println("Remaining = " + policyPayout.subtract(totalChecksAmount));
        System.out.printf("%-15s %-15d %-8d %d %n", totalChecksAmount, totalChecksAmount.unscaledValue(), totalChecksAmount.scale(), totalChecksAmount.precision());
    }
}
