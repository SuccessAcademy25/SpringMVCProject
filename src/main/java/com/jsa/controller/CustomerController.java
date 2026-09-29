package com.jsa.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jsa.model.Customer;
import com.jsa.model.SearchCriteria;
import com.jsa.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Controller
public class CustomerController {
	private final InternalResourceViewResolver viewResolver;
	private static final Random random = new Random();

	// @Autowired
	// EcomServiceImpl ecomService;

	@RequestMapping("/") 
	public String home() {
		return "index";
	   }

	@Autowired
	CustomerService ecomService;

	CustomerController(InternalResourceViewResolver viewResolver) {
		this.viewResolver = viewResolver;
	}

	@RequestMapping("/register.jsa")
	public String getRegisterPage() {
		return "register";
	}
	
	@RequestMapping("/login.jsa")
	public String getLoginPage() {
		return "login";
	}
	
	@RequestMapping("/search.jsa")
	public String getSearchPage() {
		return "searchPage";
	}

	@RequestMapping("/addCustomers.jsa")
	public String addCustomer(@ModelAttribute("customer") Customer customer, Model model) {
		customer.setCustId(generateCustId());
	    boolean isSaved = ecomService.addCustomerDetails(customer);
	    
	 // 2. Fetch all updated records from the database
	    List<Customer> custList = ecomService.getAllCustomers();
	    
	    // 3. Pass the customer list to the model so search.jsp can iterate over it
	    model.addAttribute("LIST", custList);
	    
	    return "searchPage";

		/*
		 * if (isSaved) { // Redirects to search page upon success return
		 * "redirect:/search.jsa"; } else { model.addAttribute("MSG",
		 * "Registration Failed! Please try again."); return "register"; // Returns back
		 * to registration page on failure }
		 */
	}
	
	
	
	@RequestMapping(value = "/searchCustomers.htm", method = RequestMethod.GET)
	public ModelAndView searchCustomer(HttpServletRequest request) {

	    String page = "searchPage";

	    System.out.println("--------- Search Customer ------------");

	    SearchCriteria searchCriteria = new SearchCriteria();

	    // Get Customer ID
	    String id = request.getParameter("id");

	    if (id != null && !id.trim().isEmpty()) {
	        try {
	            searchCriteria.setId(Integer.valueOf(id.trim()));
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid Customer ID: " + id);
	        }
	    }

	    // Get Customer Email
	    String email = request.getParameter("email");

	    if (email != null && !email.trim().isEmpty()) {
	        searchCriteria.setEmail(email.trim());
	    }

	    // Get Customer Phone
	    String phone = request.getParameter("phone");

	    if (phone != null && !phone.trim().isEmpty()) {
	        try {
	            searchCriteria.setPhone(phone.trim());
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid Phone Number: " + phone);
	        }
	    }

	    // Call Service Layer
	    List<Customer> custList =ecomService.searchCustomerDetails(searchCriteria);

	    System.out.println("Customer List Size >>>>> " + custList.size());

	    // Send result to JSP
	    return new ModelAndView(page, "LIST", custList);
	}

	
	@RequestMapping(value = "/deleteCustomers.htm", method = RequestMethod.GET)
	public String deleteCustomer(@RequestParam("CustId") String custId, Model model) {
	    // 1. Execute DB delete query
		ecomService.deleteCustomer(custId);

	    // 2. Fetch fresh list of remaining customers
	    List<Customer> list = ecomService.getAllCustomers();
	    model.addAttribute("LIST", list);

	    // 3. Return the JSP fragment so AJAX updates #content
	    return "searchPage"; // or "search.jsp" depending on ViewResolver
	}
	
	@RequestMapping(value = "/updateCustomers.htm", method = RequestMethod.POST)
	public String updateCustomer(@ModelAttribute("customer") Customer customer, Model model) {
	    // 1. Update customer details in DB
		ecomService.updateCustomer(customer);

	    // 2. Refresh customer list
	    List<Customer> list = ecomService.getAllCustomers();
	    model.addAttribute("LIST", list);

	    // 3. Return fragment to update #content dynamically
	    return "searchPage";
	}
	
	@PostMapping("/loginCustomers.jsa")
	public String loginCustomer(@RequestParam("custEmail") String email, 
	                            @RequestParam("phone") String phone, 
	                            HttpSession session, 
	                            Model model) {
		System.out.println("**********loginCustomer***************"+email +" :::::: "+phone);
	    
	    // 1. Authenticate user against Database
	    boolean isValidUser = ecomService.validateUser(email, phone);
	    
	     if(isValidUser) {
	    	    System.out.println("*******************Login success");
	     }else {
	    	 System.out.println("*******************Login Failed");
	     }
	    
	    if (isValidUser) {
	        // Store user details in session
	        session.setAttribute("userEmail", email);
	        
	        // 2. Load all customers to display on the search page
	        List<Customer> custlist = ecomService.getAllCustomers();
	        model.addAttribute("LIST", custlist);
	        
	        // 3. Forward to search view fragment
	        return "searchPage";
	    } else {
	        // Return login error view fragment
	        model.addAttribute("MSG", "Invalid Email or Phone Number!");
	        return "login";
	    }
	}
	

	/*
	 * @RequestMapping(value = "/addCustomers.jsa", method = RequestMethod.GET)
	 * public ModelAndView addCutomers(HttpServletRequest request) {
	 * System.out.println(
	 * "************CustomerController-addCutomers()****************"); ModelAndView
	 * model =new ModelAndView("sumbitRegister"); String page="register"; String
	 * message=""; Customer cust1=new Customer(); System.out.println(cust1); boolean
	 * status=false; //cust1.setCustId(123);
	 * 
	 * //cust1.setCustId(Integer.parseInt(request.getParameter("custId")));
	 * cust1.setCustName(request.getParameter("custName"));
	 * cust1.setCustEmail(request.getParameter("email"));
	 * cust1.setPhone(request.getParameter("phone"));
	 * cust1.setCustCity(request.getParameter("city"));
	 * cust1.setCustsalary(request.getParameter("salary"));
	 * cust1.setGender(request.getParameter("gender"));
	 * cust1.setDate(request.getParameter("date")); System.out.println(cust1);
	 * 
	 * //boolean checkValidation= CustomerDataValidation.validateRequired(cust1);
	 * boolean checkValidation= true; if(checkValidation){
	 * status=ecomService.addCustomerDetails(cust1);
	 * 
	 * } if(status){ //other depended serveice you may call here
	 * //ecomService.emailSend("") } if(status){
	 * System.out.println("Records has been submitted successfully...");
	 * message="Records has been submitted successfully..."; //page="success"; page
	 * = "sumbitRegister";
	 * 
	 * } else{ message="There is a problem to sumbit the Records..."; }
	 * 
	 * model.addObject("MSG", message); model.addObject("CUST", cust1);
	 * 
	 * return model;
	 * 
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/searchCustomers.htm", method = RequestMethod.GET)
	 * public ModelAndView searchCustomer(HttpServletRequest request) { String
	 * page="searchPage"; System.out.println("---------Search------------");
	 * Serachcriteria serachcriteria=new Serachcriteria();
	 * 
	 * if(request.getParameter("id")!=null &&
	 * !request.getParameter("id").trim().isEmpty()){ serachcriteria.setId(new
	 * Integer(request.getParameter("id"))); }
	 * if(request.getParameter("email")!=null &&
	 * !request.getParameter("email").isEmpty()){
	 * serachcriteria.setEmail(request.getParameter("email")); }
	 * if(request.getParameter("phone")!=null &&
	 * !request.getParameter("phone").isEmpty()){ serachcriteria.setPhone(new
	 * Long(request.getParameter("phone"))); }
	 * 
	 * 
	 * //System.out.println(serachcriteria); List<Customer>
	 * custList=ecomService.searchCustomerDetails(serachcriteria);
	 * System.out.println("custList>>>>>"+custList.size()); Iterator<Customer >
	 * it=custList.iterator(); while(it.hasNext()){
	 * System.out.println((Customer)it.next()); } return new ModelAndView(page,
	 * "LIST", custList); }
	 * 
	 * 
	 * @RequestMapping(value = "/updateCustomers.htm", method = RequestMethod.GET)
	 * public ModelAndView updateCutomers(HttpServletRequest
	 * request,@RequestParam("CustId")String CustId) {
	 * 
	 * 
	 * String page="update"; String message=""; int id = Integer.parseInt(CustId);
	 * boolean status=true; Customer cust=ecomService.getCustomerDataById(id);
	 * 
	 * if(status){ System.out.println("Records has been updated successfully...");
	 * message="Records has been updated successfully..."; //page="success"; page =
	 * "update"; } else{ message="There is a problem to update the Records..."; }
	 * return new ModelAndView(page, "UPDATEMSG", message); } //@PathVariable String
	 * CustId //@RequestParam("age")String age
	 * 
	 * @RequestMapping(value = "/deleteCustomers.htm", method = RequestMethod.GET)
	 * public ModelAndView deleteCutomers(HttpServletRequest
	 * request,@RequestParam("CustId")String CustId) { String page="searchPage";
	 * String message=""; boolean DeleteStatus=false; int id =
	 * Integer.parseInt(CustId); DeleteStatus=
	 * ecomService.deleteCustomerDetails(id); if(DeleteStatus){
	 * System.out.println("Records has been delete successfully...");
	 * message="Customer Records has been delete successfully with id :"+id;
	 * //page="success"; page = "searchPage";
	 * 
	 * } else{ message="There is a problem to delete the Records..."; }
	 * 
	 * return new ModelAndView(page, "DELETEMSG", message);
	 * 
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/updateCustomers.htm", method = RequestMethod.GET)
	 * public ModelAndView CustomerDataById(HttpServletRequest
	 * request,@RequestParam("CustId")String CustId) {
	 * 
	 * String page="submitUpdate"; String message=""; Customer cust1=new Customer();
	 * 
	 * boolean CustUpdateStatus=false; int id = Integer.parseInt(CustId);
	 * 
	 * cust1.setCustName(request.getParameter("custName"));
	 * cust1.setCustEmail(request.getParameter("email"));
	 * cust1.setPhone(request.getParameter("phone"));
	 * cust1.setCustCity(request.getParameter("city"));
	 * cust1.setCustsalary(request.getParameter("salary"));
	 * cust1.setGender(request.getParameter("gender"));
	 * cust1.setDate(request.getParameter("date")); System.out.println(cust1);
	 * 
	 * //boolean checkValidation= CustomerDataValidation.validateRequired(cust1);
	 * boolean checkValidation= true; if(checkValidation){
	 * CustUpdateStatus=ecomService.updateCustomerDetail(cust1); }
	 * 
	 * if(CustUpdateStatus){
	 * System.out.println("Records has been update successfully...");
	 * message="Records has been update successfully...";
	 * 
	 * 
	 * } else{ message="There is a problem to update the Records..."; }
	 * 
	 * return new ModelAndView(page, "CUST", message);
	 */

	public static int generateCustId() {
		return 1000 + random.nextInt(9000);
	}

}
