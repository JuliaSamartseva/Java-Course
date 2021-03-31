package webapp.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webapp.entity.User;
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
    return "/administrator/users";
  }

  @GetMapping(path = "/get-users", produces = "application/json")
  @ResponseBody
  public String getUsers(Model model) {
    Gson gson = new Gson();
    return gson.toJson(userService.allUsers().toArray(new User[] {}));
  }

  @GetMapping( "/block-user/{id}")
  @ResponseBody
  public void blockUser(@PathVariable Long id) {
    userService.blockUser(id);
  }

  @GetMapping("/unblock-user/{id}")
  @ResponseBody
  public void unblockUser(@PathVariable Long id) {
    userService.unblockUser(id);
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

}
