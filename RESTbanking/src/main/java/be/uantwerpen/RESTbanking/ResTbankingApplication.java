package be.uantwerpen.RESTbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ResTbankingApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ResTbankingApplication.class, args);

		//test user added
		Account acc1 = new Account(new Person("asif","wasefi"),"user1","pass1");
		acc1.setBalance(100);//set initial balance
		acc1.setTypeAccount("joint");
		Database.getDatabase().put(acc1.getUsername(),acc1);


		ClientThread depositThread = new ClientThread("user1","pass1","deposit",2);
		Thread th1 = new Thread(depositThread);
		Thread th2 = new Thread(depositThread);
		Thread th3 = new Thread(depositThread);
		Thread th4 = new Thread(depositThread);
		Thread th5 = new Thread(depositThread);
		Thread th6 = new Thread(depositThread);
		Thread th7 = new Thread(depositThread);
		Thread th8 = new Thread(depositThread);
		Thread th9 = new Thread(depositThread);
		Thread th10 = new Thread(depositThread);

		ClientThread withdrawThread = new ClientThread("user1","pass1","withdraw",1);
		Thread th11 = new Thread(withdrawThread);
		Thread th12 = new Thread(withdrawThread);
		Thread th13 = new Thread(withdrawThread);
		Thread th14 = new Thread(withdrawThread);
		Thread th15 = new Thread(withdrawThread);
		Thread th16 = new Thread(withdrawThread);
		Thread th17 = new Thread(withdrawThread);
		Thread th18 = new Thread(withdrawThread);
		Thread th19 = new Thread(withdrawThread);
		Thread th20 = new Thread(withdrawThread);

		th1.start();
		th2.start();
		th3.start();
		th4.start();
		th5.start();
		th6.start();
		th7.start();
		th8.start();
		th9.start();
		th10.start();
		th11.start();
		th12.start();
		th13.start();
		th14.start();
		th15.start();
		th16.start();
		th17.start();
		th18.start();
		th19.start();
		th20.start();

	}

}
