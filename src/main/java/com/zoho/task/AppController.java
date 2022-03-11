//Controller which maps to the endpoints

package com.zoho.task;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {


    @Autowired
    private UserRepository repo;


    //Mapping for Index page (Initial page)
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }


    //Mapping for Register page
    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }


    //Mapping for taking input data during registration and checking
    // if it's already in database. If it doesn't exist then it will register user or
    // else it will tell the user they are already registered.
    @PostMapping("/process_register")
    public String processRegistration(User user) {

        if (repo.findByEmail(user.getEmail()) != null) {
            return "/user_exists";
        }

        //Encrypting the password before saving in the database
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        repo.save(user);
        return "register_success";

    }


    //Showing all the contacts of that particular user logged in.
    @GetMapping("/home")
    public String viewHome(Model model, Principal principal) {


        String email = principal.getName();
        User user = this.repo.findByEmail(email);


        //retrieving the user's contacts and passing it to the frontend for thymeleaf
        List<Contacts> contactList = user.getContacts();

        model.addAttribute("cont", contactList);
        return "home_page";

    }


    //Saving the contact which user has entered in the form.
    @PostMapping("/save-contact")
    public String saveContact(@ModelAttribute Contacts contacts, Principal principal, Model m) {


        String email = principal.getName();
        User user = this.repo.findByEmail(email);


//		adding user to contact
        contacts.setUser(user);


//		adding contacts to user
        user.getContacts().add(contacts);

        this.repo.save(user);

        return "contact_saved";
    }

}
