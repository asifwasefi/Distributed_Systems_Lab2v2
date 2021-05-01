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
		acc1.setBalance(100);
		Database.getDatabase().put(acc1.getUsername(),acc1);


		ClientThread clientThread = new ClientThread("user1","pass1");
		Thread th1 = new Thread(clientThread);
		Thread th2 = new Thread(clientThread);
		Thread th3 = new Thread(clientThread);
		Thread th4 = new Thread(clientThread);
		Thread th5 = new Thread(clientThread);
		Thread th6 = new Thread(clientThread);
		Thread th7 = new Thread(clientThread);
		Thread th8 = new Thread(clientThread);
		Thread th9 = new Thread(clientThread);
		Thread th10 = new Thread(clientThread);
		Thread th11 = new Thread(clientThread);
		Thread th12 = new Thread(clientThread);
		Thread th13 = new Thread(clientThread);
		Thread th14 = new Thread(clientThread);
		Thread th15 = new Thread(clientThread);
		Thread th16 = new Thread(clientThread);

		th1.start(); System.out.println(acc1.getBalance());
		th2.start(); System.out.println(acc1.getBalance());
		th3.start(); System.out.println(acc1.getBalance());
		th4.start(); System.out.println(acc1.getBalance());
		th5.start(); System.out.println(acc1.getBalance());
		th6.start(); System.out.println(acc1.getBalance());
		th7.start(); System.out.println(acc1.getBalance());
		th8.start(); System.out.println(acc1.getBalance());
		th9.start(); System.out.println(acc1.getBalance());
		th10.start();System.out.println(acc1.getBalance());
		th11.start();System.out.println(acc1.getBalance());
		th12.start();System.out.println(acc1.getBalance());
		th13.start();System.out.println(acc1.getBalance());
		th14.start();System.out.println(acc1.getBalance());
		th15.start();System.out.println(acc1.getBalance());
		th16.start();System.out.println(acc1.getBalance());
		System.out.println(acc1.getBalance());

	}

}
