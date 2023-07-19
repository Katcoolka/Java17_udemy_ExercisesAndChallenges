package dev.bank;

import dev.dto.Transaction;

import java.util.LinkedHashMap;
import java.util.Map;

public class BankAccount {

    public enum AccountType {CHECKING, SAVINGS}

    private final AccountType accountType;

    //Challenge: Use unmodifiable collections
    //removing final as per challenge requirements, adding Map transactions and a getter fo it
    private  double balance;

    private final Map<Long, Transaction> transactions = new LinkedHashMap<>();

    //removing public
    BankAccount(AccountType accountType, double balance) {
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }
    //Challenge: Use unmodifiable collections
//    public Map<Long, Transaction> getTransactions() {
//        return Map.copyOf(transactions);
//    }
    //created immutable version of method with String  and LinkedHashMap
    public Map<Long, String> getTransactions(){
        Map<Long, String> txMap = new LinkedHashMap<>();
        for (var tx : transactions.entrySet()) {
            txMap.put(tx.getKey(), tx.getValue().toString());
        }
        return txMap;
    }

    @Override
    public String toString() {
        return "%s $%.2f".formatted(accountType, balance);
    }
    //Challenge: Use unmodifiable collections
    void commitTransaction(int routingNumber, long transactionId, String customerId, double amount) {
        balance += amount;
        transactions.put(transactionId, new Transaction(routingNumber, transactionId, Integer.parseInt(customerId), amount));
    }
}
