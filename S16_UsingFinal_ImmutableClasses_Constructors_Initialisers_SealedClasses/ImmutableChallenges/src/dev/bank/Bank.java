package dev.bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    //Challenge: Use unmodifiable collections
    public final int routingNumber;
    private long lastTransactionId;

    private final Map<String, BankCustomer> customers;

    public Bank(int routingNumber) {
        this.routingNumber = routingNumber;
        customers = new HashMap<>();
    }

    public BankCustomer getCustomer(String id) {
        BankCustomer customer = customers.get(id);
        return customer;
    }

    public void addCustomer(String name, double checkingInitialDeposit, double savingsInitialDeposit) {
        BankCustomer customer = new BankCustomer(name, checkingInitialDeposit, savingsInitialDeposit);
        customers.put(customer.getCustomerId(), customer);
    }

    public boolean doTransaction(String id, BankAccount.AccountType accountType, double amount) {
        BankCustomer customer = customers.get(id);
        if (customer != null) {
            BankAccount account = customer.getAccount(accountType);
            if (account != null) {
                if ((account.getBalance() + amount) < 0) {
                    System.out.println("Insufficient funds");
                } else {
                    account.commitTransaction(routingNumber, lastTransactionId++, id, amount);
                    return true;
                }
            }
        } else {
            System.out.println("Invalid Customer id");
        }
        return false;
    }
}
