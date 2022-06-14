package com.example.crm.controller;

import com.example.crm.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.crm.service.CustomerService;

import java.util.List;

@Controller
public class CustomerController {

    //need to inject our customer com.example.crm.service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String listCustomers(Model theModel) {
        //get customers from the com.example.crm.service
        List<Customer> customers = customerService.getCustomers();
        //add the customers to the model
        theModel.addAttribute("customers", customers);
        return "listCustomers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        //create model attribute to bind form data
        Customer customer = new Customer();
        model.addAttribute("customer", customer);
        return "customerForm";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        //save customer using our service
        customerService.saveOrUpdateCustomer(customer);

        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {

        //get the customer from the database
        Customer customer = customerService.getCustomer(id);
        //set the customer as a model attribute to pre-populate the form
        model.addAttribute("customer", customer);
        //send over to our form
        return "customerForm";
    }

}
