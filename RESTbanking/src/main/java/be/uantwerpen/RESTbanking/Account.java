package be.uantwerpen.RESTbanking;

/**
 * Created by asif on 27/04/2021
 */

public class Account {

    private Person accountHolder;
    private double balance = 0;
    private String typeAccount = "single";
    private String username;
    private String password;

    public Account(Person accountHolder, String username, String password) {
        this.accountHolder = accountHolder;
        this.username = username;
        this.password = password;
    }

    public Account()
    {

    }

    public Person getAccountHolder() {
        return accountHolder;
    }

    public String getTypeAccount() {
        return typeAccount;
    }

    public void setAccountHolder(Person accountHolder) {
        this.accountHolder = accountHolder;
    }

    public void setTypeAccount(String typeAccount) {
        if(typeAccount.compareToIgnoreCase("single") ==0)  //not equal to single account
        {
            this.typeAccount = "single";
        }
        else if(typeAccount.compareToIgnoreCase("joint") ==0)
        {
            this.typeAccount = "joint";
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String deposit(double depositAmount) {
        balance += depositAmount;
        return "amount deposited = " + depositAmount;
    }

    public String withdraw(double withdrawAmount)
    {
        if(balance >= withdrawAmount)
        {
            balance-= withdrawAmount;
            return "amount withdrawn: "+ withdrawAmount;
        }
        else
        {
            return "To withdraw amount "+withdrawAmount+" higher than balance "+balance;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
