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
        getDatabase().put(userDetails.getUsername(),acc1);

        return "entry added successfully";
    }

    @GetMapping(path = "/getEntry",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Account> getEntry(@RequestBody String usernameANDpassword) {
        String [] temp = usernameANDpassword.split(" ",2);//split string into two by spaces ie username space password
       return getDatabase().values().stream().filter(account -> account.getUsername().compareToIgnoreCase(temp[0])==0 && account.getPassword().equals(temp[1])).collect(Collectors.toList());
    }

    @PostMapping(path = "/deposit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String deposit(@RequestBody String usernameANDpasswordANDdepositAmount)
    {
        String [] usrPassDep = usernameANDpasswordANDdepositAmount.split(" ",3);//split into username password and deposit amount
        List<Account> accountList= getDatabase().values().stream().filter(account -> account.getUsername().compareToIgnoreCase(usrPassDep[0])==0 && account.getPassword().equals(usrPassDep[1])).collect(Collectors.toList());
        if (accountList.isEmpty())
            return "Account not found with given credentials";
        else
            return accountList.get(0).deposit(Long.parseLong(usrPassDep[2]));
    }

    @PostMapping(path = "/withdraw",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String withdraw(@RequestBody String usernameANDpasswordANDwithdrawAmount)
    {
        String [] usrPassWithd = usernameANDpasswordANDwithdrawAmount.split(" ",3);//split into username password and deposit amount
        List<Account> accountList= getDatabase().values().stream().filter(account -> account.getUsername().compareToIgnoreCase(usrPassWithd[0])==0 && account.getPassword().equals(usrPassWithd[1])).collect(Collectors.toList());
        if (accountList.isEmpty())
            return "Account not found with given credentials";
        else
            return accountList.get(0).withdraw(Long.parseLong(usrPassWithd[2]));
    }

    @GetMapping(path = "/getBalance",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String getBalance(@RequestBody String usernameANDpassword)
    {
        String [] usrPass = usernameANDpassword.split(" ",2);//split into username password
        List<Account> accountList= getDatabase().values().stream().filter(account -> account.getUsername().compareToIgnoreCase(usrPass[0])==0 && account.getPassword().equals(usrPass[1])).collect(Collectors.toList());
        if (accountList.isEmpty())
            return "Account not found with given credentials";
        else
            return "balance is: "+accountList.get(0).getBalance();
    }






}
