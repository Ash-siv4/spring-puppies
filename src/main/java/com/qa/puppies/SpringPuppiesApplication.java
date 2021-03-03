package com.qa.puppies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.qa.puppies.domain.Puppies;
import com.qa.puppies.rest.PuppiesController;

@SpringBootApplication
public class SpringPuppiesApplication {

	@Bean
	public List<Puppies> makeListBean() {
		List<Puppies> puppies = new ArrayList<>();
		puppies.add(new Puppies("Lucky", 13, "Jack Russell"));
		return puppies;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringPuppiesApplication.class, args);
//		ApplicationContext beanBag = SpringApplication.run(SpringPuppiesApplication.class, args);
//		// VERY DODGEY - DO NOT PUT IN PROJECT!!!!!
//		PuppiesController pc = beanBag.getBean(PuppiesController.class);
//		System.out.println(pc);

//		List<Puppies> arrayList = new ArrayList<>();
//		PuppiesController withAnArrayList = new PuppiesController(arrayList);
//
//		List<Puppies> linkedList = new LinkedList<>();
//		PuppiesController withALinkerList = new PuppiesController(linkedList);

	}

}
