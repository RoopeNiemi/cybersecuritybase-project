/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Customer;
import sec.project.repository.CustomerRepository;

/**
 *
 * @author User
 */
@Controller
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadForm() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitForm(@RequestParam String username, @RequestParam String password) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null && customer.getUsername().equals(username)
                && customer.getPassword().equals(password)) {
            System.out.println(customer.getPassword());
            return "redirect:/form/" + customer.getUsername();
        }
        return "/login";
    }

}
