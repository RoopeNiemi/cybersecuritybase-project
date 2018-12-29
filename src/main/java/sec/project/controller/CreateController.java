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
public class CreateController {
    @Autowired
    public CustomerRepository customerRepository;
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String loadForm() {
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitForm(@RequestParam String username, @RequestParam String password) {
        customerRepository.save(new Customer(username, password));
        return "redirect:/login";
    }

}
