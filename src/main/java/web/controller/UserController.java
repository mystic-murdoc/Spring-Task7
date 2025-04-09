package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "users";
    }

    @GetMapping("/new")
    public String showAddUserPage(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/{id}/update")
    public String showUserUpdatePage(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        user.setId(id);
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String showDeletePage(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        model.addAttribute("user", user);
        return "delete";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(RuntimeException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}