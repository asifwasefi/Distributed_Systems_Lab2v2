package be.uantwerpen.RESTbanking;

import java.util.concurrent.Semaphore;

/**
 * Created by asif on 27/04/2021
 */

public class Account {

    private Person accountHolder;
    private double balance = 0;
    private String typeAccount = "single";
    private String username;
    private String password;
    private Semaphore semaphore = new Semaphore(1,true);

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
//        enforce a delay so other threads get blocked until this delay is over
        for (int i=0;i<1000;i++)
        {
            for (int j=0;j<1000;j++)
            {
                int k = j+i;
            }
        }

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String deposit(double depositAmount) {
        if (semaphore.availablePermits()==0)
            System.out.println("Account in use, cannot deposit");
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setBalance(this.getBalance()+depositAmount);
        semaphore.release();
        return "\namount deposited = " + depositAmount;
    }

    public String withdraw(double withdrawAmount)
    {
        if (semaphore.availablePermits()==0)
            System.out.println("Account in use, cannot withdraw");
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(this.getBalance() >= withdrawAmount)

        {
            this.setBalance(this.getBalance()-withdrawAmount);
            semaphore.release();
            return "\namount withdrawn: "+ withdrawAmount;
        }
        else
        {
            semaphore.release();
            return "To withdraw amount "+withdrawAmount+" higher than balance "+this.getBalance();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
