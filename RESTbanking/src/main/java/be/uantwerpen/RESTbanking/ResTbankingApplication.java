package be.uantwerpen.RESTbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResTbankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResTbankingApplication.class, args);

		//test user added
		Account acc1 = new Account(new Person("asif","wasefi"),"user1","pass1");
		Database.getDatabase().put(acc1.getUsername(),acc1);
	}

}
