package com.jsa.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jsa.model.Customer;
import com.jsa.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {
	private static final Random random = new Random();

	// @Autowired
	// EcomServiceImpl ecomService;

	@RequestMapping("/") 
	public String home() {
		return "index";
	   }

	@Autowired
	CustomerService ecomService;

	@RequestMapping("/register.jsa")
	public String getRegisterPage() {
		return "register";
	}

	@RequestMapping(value = "/addCustomers.jsa", method = RequestMethod.GET)
	public ModelAndView addCutomers(HttpServletRequest request) {
		System.out.println("************CustomerController-addCutomers()****************");
		ModelAndView model = new ModelAndView("sumbitRegister");
		String page = "register";
		String message = "";
		Customer cust1 = new Customer();
		System.out.println(cust1);
		boolean status = false; //
		cust1.setCustId(generateCustId());

		// cust1.setCustId(Integer.parseInt(request.getParameter("custId")));
		cust1.setCustName(request.getParameter("custName"));
		cust1.setCustEmail(request.getParameter("email"));
		cust1.setPhone(request.getParameter("phone"));
		cust1.setCustCity(request.getParameter("city"));
		cust1.setCustsalary(request.getParameter("salary"));
		cust1.setGender(request.getParameter("gender"));
		cust1.setDate(request.getParameter("date"));
		System.out.println(cust1);

		// boolean checkValidation= CustomerDataValidation.validateRequired(cust1);
		boolean checkValidation = true;
		if (checkValidation) {
			status = ecomService.addCustomerDetails(cust1);

		}
		if (status) { // other depended serveice you may call here
			// ecomService.emailSend("")
		}
		if (status) {
			System.out.println("Records has been submitted successfully...");
			message = "Records has been submitted successfully..."; //
			page = "success";
			// page= "sumbitRegister";

		} else {
			message = "There is a problem to sumbit the Records...";
		}

		model.addObject("MSG", message);
		model.addObject("CUST", cust1);
		return model;

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
