package com.example.Jamskob_Ventures;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	@Autowired
	private CustomerRepository repo;
	@Autowired
	private CustomerService service;
	@Autowired
	private ProductRepository repos;
	@Autowired
	private ProductService serve;

	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("customer", new Customers());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(@ModelAttribute Customers customer) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
		
		repo.save(customer);
		return "register_success";
	}
	
	@GetMapping("/list_customers")
	public String viewCustomerslist(Model model) {
		List<Customers> listCustomers = repo.findAll();
		model.addAttribute("listCustomers", listCustomers);
		return "customers";
	}
	
	@GetMapping("/list_products")
	public String viewProductList(Model model) {
		List<Products> listProducts = repos.findAll();
		model.addAttribute("listProducts", listProducts);
		return "products";
	}
	
	@RequestMapping("/new")
	public String showNewCustomerForm(Model model) {
		Customers customer = new Customers();
		model.addAttribute("customer", customer);
		return "new_customer";
	}
	
	@RequestMapping("/newProduct")
	public String showNewProductForm(Model model) {
		Products product = new Products();
		model.addAttribute("product", product);
		return "new_product";
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer") Customers customers) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(customers.getPassword());
		customers.setPassword(encodedPassword);
		repo.save(customers);
		
		return "redirect:/list_customers";  
	}
	
	@RequestMapping(value="/saveProduct", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Products products) {
		repos.save(products);
		
		return "redirect:/list_products";  
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditCustomerForm(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edit_customer");
		
		Customers customer = service.get(id);
		mav.addObject("customer", customer);
		
		return mav;
	}
	
	@RequestMapping("/editProduct/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name="id") Long id) {
		ModelAndView mav = new ModelAndView("edit_product");
		
		Products product = serve.get(id);
		mav.addObject("product", product);
		
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable(name="id") Long id) {
		service.delete(id);
		
		return "redirect:/list_customers";
		
	}
	
	@RequestMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id) {
		serve.delete(id);
		
		return "redirect:/list_products";
		
	}

	

}
