package pl.sda.final_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.sda.final_project.model.user.Countries;
import pl.sda.final_project.dto.RegistrationDto;
import pl.sda.final_project.service.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("countries", Countries.values());
        model.addAttribute("registrationObject", registrationDto);
        return "registrationPage";
    }

    @PostMapping("/register")
    public String registrationEffect(RegistrationDto registrationDto  /*, BindingResult bindingResult*/) {
  /*      if (bindingResult.hasErrors()) {
            return "registrationPage";
        }*/
        userService.registerUser(registrationDto);
        return "redirect:/login";

    }


}
