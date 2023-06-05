package org.java.demo.controller;

import java.util.List;
import java.util.Optional;

import org.java.demo.pojo.Pizza;
import org.java.demo.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

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
	public String getPizzaShow(
			Model model,
			@PathVariable int id
		) {

		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();

		model.addAttribute("pizza", pizza);

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
	public String createPizza(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "pizza-create";
	}
	
	@PostMapping("/pizza/create")
	public String savePizza(Model model, @Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("errors", bindingResult);
			model.addAttribute("pizza", pizza);
			
			return "pizza-create";
		}
		
		pizzaService.save(pizza);
		return "redirect:/";
	}
	
	@GetMapping("/pizza/delete/{id}")
	public String deletePizza(Model model, @PathVariable("id") int id) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		pizzaService.deletePizza(pizza); 
		
	    return "redirect:/";
		
	}
	
	@GetMapping("/pizza/edit/{id}")
	public String editPizza(Model model, @PathVariable("id") int id) {
		
		Optional<Pizza> pizzaOpt = pizzaService.findById(id);
		Pizza pizza = pizzaOpt.get();
		model.addAttribute("pizza", pizza);
		
		return "pizza-edit";
	}
	
	@PostMapping("/pizza/update/{id}")
	public String updatePizza(Model model, @PathVariable("id") int id,@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			for (ObjectError err : bindingResult.getAllErrors()) 
				System.err.println("error: " + err.getDefaultMessage());
			
			model.addAttribute("errors", bindingResult);
			model.addAttribute("pizza", pizza);
			
			return "pizza-edit";
		}
		
		pizzaService.save(pizza);
		
	    return "redirect:/";
	}
}