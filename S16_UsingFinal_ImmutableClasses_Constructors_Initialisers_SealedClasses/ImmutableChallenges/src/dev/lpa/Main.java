package dev.lpa;

import dev.bank.Bank;
import dev.bank.BankAccount;
import dev.bank.BankCustomer;
import dev.dto.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //Challenge: Write your own immutable classes

//        BankAccount account = new BankAccount(BankAccount.AccountType.CHECKING, 500);
//        System.out.println(account);

//        BankCustomer joe = new BankCustomer("Joe", 500.00, 10000.00);
//        System.out.println(joe);

        //Challenge: Use unmodifiable collections
        System.out.println("------Challenge: Use unmodifiable collections------>>>");

        Bank bank = new Bank(3214567);
        bank.addCustomer("Joe", 500.00, 1000.00);
        BankCustomer joe = bank.getCustomer("000000010000000");
        System.out.println(joe);

        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, 35)) {
            System.out.println(joe);
        }

        if (bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING, -535)) {
            System.out.println(joe);
        }

        BankAccount checking = joe.getAccount(BankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k, v) -> System.out.println(k + ": " + v));
        //console output: transactions are listed, but they are not in order

        //transactions.put(3L, new Transaction(1,1,Integer.parseInt(joe.getCustomerId()), 500));
        //console output: exception unsupported operation
//        System.out.println("-----------------");
//        for (var tx : transactions.values()) {
//            tx.setCustomerId(2);
//            tx.setAmount(10000.00);
//        }
//        transactions.forEach((k, v) -> System.out.println(k + ": " + v));
        //after modifying Bank Account getTransactions method code doesn't compile

        System.out.println("-----------------");
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().forEach((k, v) -> System.out.println(k + ": " + v));
        //console output => client change data - Not OK
        //we could create deep copy of the account

        System.out.println("-----------------");
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().forEach((k, v) -> System.out.println(k + ": " + v));
        //code compiles and runs, no changes in client accounts amounts
        System.out.println("<<<------Challenge: Use unmodifiable collections------");

        //Challenge: Write your own immutable classes
//        List<BankAccount> accounts = joe.getAccounts();
//        accounts.clear();
//        System.out.println(joe);

//test code for adding
//        accounts.add(new BankAccount(BankAccount.AccountType.CHECKING, 150000));
//        System.out.println(joe);
    }
}