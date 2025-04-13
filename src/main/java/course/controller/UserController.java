package course.controller;

import course.entity.UserEntity;
import course.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

    @Controller
    public class UserController {

        @Autowired
        private UserService userService;

        @GetMapping("/register")
        public String showRegistrationForm(Model model) {
            model.addAttribute("user", new UserEntity());
            return "auth/register";
        }

        @PostMapping("/register")
        public String registerUser(@ModelAttribute("user") UserEntity userEntity) {
            userService.createUser(userEntity);
            return "redirect:/login";
        }

        @GetMapping("/dashboard")
        public String dashboard(Authentication auth) {
            if (auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
                return "redirect:/admin";
            else
                return "redirect:/customer";
        }

        @GetMapping("/login")
        public String showLoginPage() {
            return "auth/login";
        }

        @GetMapping("/home")
        public String showHomePage() {
            return "auth/home";
        }
    }
