package org.java.demo.controller;

import java.util.List;

import org.java.demo.pojo.Pizza;
import org.java.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;

	@GetMapping("/")
	public String getPizzaIndex(Model model) {

		List<Pizza> pizze = pizzaService.findAll();
		model.addAttribute("pizze", pizze);

		return "index";
	}
	
	@GetMapping("/pizza/{id}")
	public String getPizza(Model model, @PathVariable("id") int id) {
		
		Pizza selectedPizza = null;
	    for (Pizza p : pizzaService.findAll()) {
	        if (p.getId() == id) {
	            selectedPizza = p;
	            break;
	        }
	    }

	    if (selectedPizza != null) {
	        model.addAttribute("pizza", selectedPizza);
	    } else {
	        model.addAttribute("pizza", "nessuna pizza disponibile");
	    }
		
		return "show";
		
	}
	
	@PostMapping("/pizze/search")
	public String searchPizza(Model model, @RequestParam(required = false) String name) {
		List<Pizza> pizze = pizzaService.findByName(name);
		model.addAttribute("name", name);
		model.addAttribute("pizze", pizze);
		
		return "index";
	}
	
	@GetMapping("/pizza/create")
	public String createPizza() {
		return "pizza-create";
	}
	
	@PostMapping("/pizza/create")
	public String savePizza(@ModelAttribute Pizza pizza) {
		
		pizzaService.save(pizza);
		return "redirect:/";
	}
}