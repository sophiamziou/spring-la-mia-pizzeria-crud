package org.java.demo;

import java.util.List;

import org.java.demo.pojo.Pizza;
import org.java.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Autowired
	private PizzaService pizzaService;
	
	@Override
	public void run(String... args) throws Exception {

		Pizza p1 = new Pizza("margherita", "mozzarella e pomodoro", "https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028", 7);
		Pizza p2 = new Pizza("vegetariana", "tante verdure", "https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028", 10);
		Pizza p3 = new Pizza("tonno", "tonno e olive", "https://cdn.shopify.com/s/files/1/0586/6795/8427/articles/Margherita-9920.jpg?v=1644590028", 9);

		pizzaService.save(p1);
		pizzaService.save(p2);
		pizzaService.save(p3);
		
		List<Pizza> pizze = pizzaService.findAll();
        System.out.println(pizze);
        
	}
}
