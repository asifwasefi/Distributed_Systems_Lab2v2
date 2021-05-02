package be.uantwerpen.RESTbanking;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by asif on 01/05/2021
 */
public class ClientThread extends Thread {

    HashMap<String,Account> db = Database.getDatabase();
    private String username,password;
    private String addOrMinus;private double amount;

    public ClientThread(String username, String password, String addOrMinus, double amount) {
        this.username = username;
        this.password = password;
        this.addOrMinus = addOrMinus;
        this.amount = amount;
    }

    @Override
    public void run() {
        List<Account> accountList = db.values().stream().filter(account -> account.getUsername().compareToIgnoreCase(username)==0 && account.getPassword().equals(password)).collect(Collectors.toList());
        if( ! accountList.isEmpty())
        {
            System.out.println(currentThread().getName()+ " depositing/withdrawing");
            if (addOrMinus.compareToIgnoreCase("deposit")==0)
                accountList.get(0).deposit(amount);
            else if(addOrMinus.compareToIgnoreCase("withdraw")==0)
                accountList.get(0).withdraw(amount);
            System.out.println(accountList.get(0).getBalance());
        }
        else System.out.println("ClientThread: Account not found with given credentials");
        System.out.println(currentThread().getName()+" ended");


    }
}
