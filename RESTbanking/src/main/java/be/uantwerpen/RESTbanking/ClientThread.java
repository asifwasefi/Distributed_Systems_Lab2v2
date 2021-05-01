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
    int i = 0;

    public ClientThread(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        List<Account> accountList = db.values().stream().filter(account -> account.getUsername().compareToIgnoreCase(username)==0 && account.getPassword().equals(password)).collect(Collectors.toList());
        if( ! accountList.isEmpty())
        {
            i++;
            if(i%2==0)
                accountList.get(0).deposit(1);
            else
                accountList.get(0).withdraw(1);
        }
        else System.out.println("ClientThread: Account not found with given credentials");
        System.out.println(currentThread().getName()+" ended");


    }
}
