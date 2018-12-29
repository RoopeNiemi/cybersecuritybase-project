package sec.project.controller;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.CustomerRepository;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/form/{account}", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address, @PathVariable("account") String account) {
        signupRepository.save(new Signup(name, address));
        return "redirect:/form/" + account;
    }

    @RequestMapping(value = "/form/{account}", method = RequestMethod.GET)
    public String getAccount(@PathVariable("account") String account, Model model) {
        model.addAttribute("username", customerRepository.findByUsername(account).getUsername());
        List<Signup> signed = signupRepository.findAll();
        if (signed == null) {
            signed = new ArrayList<>();
        }
        model.addAttribute("all", signed);
        return "form";
    }

    @Transactional
    @RequestMapping(value = "/form/{account}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("account") String account) {
        customerRepository.deleteByUsername(account);
        return "redirect:/login";
    }

}
