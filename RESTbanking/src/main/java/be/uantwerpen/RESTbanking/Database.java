package be.uantwerpen.RESTbanking;



import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by asif on 27/04/2021
 */

@RestController
@RequestMapping("/db")
public class Database {

    private static HashMap<String,Account> database = null;

    @GetMapping("/usersList")
    public static HashMap<String,Account> getDatabase()
    {
        if (database == null)
            database = new HashMap<>();

        return database;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            path = "/addEntry")
    public String addEntry(@RequestBody UserDetails userDetails)
    {
        Person p1 = new Person(userDetails.getAccountHolder().getFirstName(),userDetails.getAccountHolder().getLastName());
        Account acc1 = new Account(p1,userDetails.getUsername(),userDetails.getPassword());
        acc1.setTypeAccount(userDetails.getTypeAccount());
        acc1.setBalance(userDetails.getBalance());
        getDatabase().put(userDetails.getUsername(),acc1);

        return "entry added successfully";
    }

    @GetMapping(path = "/getEntry",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Account> getEntry(@RequestParam String username, String password) {
       return getDatabase().values().stream().filter(account -> account.getUsername().compareToIgnoreCase(username)==0 && account.getPassword().equals(password)).collect(Collectors.toList());
    }


    @DeleteMapping(path = "/deleteEntry",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String deleteEntry(@RequestParam String username, String password) {
        List<Account> accountList= getEntry(username,password);
        if (accountList.isEmpty())
            return "Account not found with given credentials";
        else
        {
            getDatabase().remove(username);
            return "removed successfully";
        }
    }

    @PutMapping(path = "/deposit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String deposit(@RequestParam String username, String password, Double depositAmount)
    {
       List<Account> accountList= getEntry(username,password);
        if (accountList.isEmpty())
            return "Account not found with given credentials";
        else
            return accountList.get(0).deposit(depositAmount);
    }

    @PutMapping(path = "/withdraw",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String withdraw(@RequestParam String username, String password, Double withdrawAmount)
    {
        List<Account> accountList= getEntry(username,password);
        if (accountList.isEmpty())
            return "Account not found with given credentials";
        else
            return accountList.get(0).withdraw(withdrawAmount);
    }

    @GetMapping(path = "/getBalance",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String getBalance(@RequestParam String username, String password)
    {
        List<Account> accountList= getEntry(username,password);
        if (accountList.isEmpty())
            return "Account not found with given credentials";
        else
            return "balance is: "+accountList.get(0).getBalance();
    }






}
