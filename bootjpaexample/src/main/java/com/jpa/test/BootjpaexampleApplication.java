package com.jpa.test;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.jpa.test.dao.UserRepository;
import com.jpa.test.entities.User;

@SpringBootApplication
public class BootjpaexampleApplication {

	private final UserRepository userRepository;

	BootjpaexampleApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BootjpaexampleApplication.class, args);
		UserRepository userRepository = context.getBean(UserRepository.class);
		User user=new User();
		user.setName("dharmy");
		user.setCity("dehri");
		user.setStatus("i am java programmer");
		
		User user2 = userRepository.save(user);
		System.out.println(user2);
		
		// create object  of  user...
		User user3 =new User();
		user3.setName("user3");
		user3.setCity("city1");
		user3.setStatus("python");
		
		User user4=new User();
		user4.setName("user4");
		user4.setCity("city2");
		user4.setStatus("java programmer");
		
		// saving single user...
		User resultUser = userRepository.save(user3);
		
		// saving multiple users...
		List<User> users = List.of(user3, user4);
		Iterable<User> result = userRepository.saveAll(users);
		result.forEach(userR->{
			System.out.println(userR);
		});
		
		System.out.println("saved user "+resultUser);
		System.out.println("done");
		
	}

}
