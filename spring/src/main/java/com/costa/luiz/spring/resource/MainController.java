package com.costa.luiz.spring.resource;

import com.costa.luiz.spring.domain.customer.Customer;
import com.costa.luiz.spring.domain.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Example from https://github.com/spring-boot-learning-youtube/spring-boot-plus-spring-security
 */
@Controller
public class MainController {

    @Autowired
    private final CustomerRepository repository;

    public MainController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("customers", repository.findAll());
        model.addAttribute("isManager", request.isUserInRole("ROLE_MANAGER"));
        model.addAttribute("newCustomer", new Customer());
        return "home";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Customer customer) {
        repository.save(customer);
        return "redirect:/";
    }
}
