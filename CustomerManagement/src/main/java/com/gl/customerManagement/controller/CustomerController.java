package com.gl.customerManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.customerManagement.entity.Customer;
import com.gl.customerManagement.service.CustomerService;



@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping("/list")
	public String listCustomer(Model theModel) {


		List<Customer> theCustomer = customerService.findAll();
		
		theModel.addAttribute("Customer", theCustomer);
		
		return "list-Customer";
	}

	@RequestMapping("/showFormForAdd")
	public String  showFormForAdd(Model model) {
		
		Customer theCustomer = new Customer();
		
		model.addAttribute("Customer", theCustomer);
		
		return "Customer-form";
		
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model theModel) {

		// get the Student from the service
		Customer theCustomer = customerService.findById(id);

		// set Student as a model attribute to pre-populate the form
		theModel.addAttribute("Customer", theCustomer);

		// send over to our form
		return "Customer-form";
	}
	
	@PostMapping("/save")
	public String saveBook(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("email") String email) {

		System.out.println(id);
		Customer theCustomer;
		if (id != 0) {
			theCustomer = customerService.findById(id);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
			theCustomer.setEmail(email);
		} else
			theCustomer = new Customer(firstName, lastName, email);
		// save the Book
		customerService.save(theCustomer);

		// use a redirect to prevent duplicate submissions
		return "redirect:/customer/list";

	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("customerId") int id) {

		// delete the student
		customerService.deleteById(id);

		// redirect to /student/list
		return "redirect:/customer/list";

	}
}
