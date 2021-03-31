package webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapp.service.UserService;

@Controller
public class AdminController {
  @Autowired
  private UserService userService;

  @GetMapping("/administrator/home")
  public String homePage(Model model) {
    return "/administrator/home";
  }

  @GetMapping("/administrator/users")
  public String userList(Model model) {
    model.addAttribute("allUsers", userService.allUsers());
    return "/administrator/users";
  }

  @PostMapping("/administrator/users")
  public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                            @RequestParam(required = true, defaultValue = "" ) String action,
                            Model model) {
    if (action.equals("delete")){
      userService.deleteUser(userId);
    }
    return "/administrator/users";
  }

  @GetMapping("/administrator/users/gt/{userId}")
  public String  gtUser(@PathVariable("userId") Long userId, Model model) {
    model.addAttribute("allUsers", userService.usergtList(userId));
    return "/administrator/users";
  }
}
