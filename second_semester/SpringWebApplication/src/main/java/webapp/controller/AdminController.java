package webapp.controller;

import com.google.gson.Gson;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import webapp.entity.User;
import webapp.entity.UserType;
import webapp.service.UserService;

import java.util.List;

@Controller
public class AdminController {
  @Autowired private UserService userService;

  @GetMapping("/administrator/home")
  public String homePage(KeycloakAuthenticationToken authentication) {
    userService.saveUserFromToken(authentication, UserType.ADMINISTRATOR);
    return "/administrator/home";
  }

  @GetMapping("/administrator/users")
  public String userList() {
    return "/administrator/users";
  }

  @GetMapping(path = "/get-users", produces = "application/json")
  @ResponseBody
  public String getUsers() {
    Gson gson = new Gson();
    List<User> userList = userService.allUsers();
    userList.removeIf(current -> current.getType().equals(UserType.ADMINISTRATOR));
    return gson.toJson(userList.toArray(new User[] {}));
  }

  @GetMapping("/block-user/{id}")
  @ResponseBody
  public void blockUser(@PathVariable Long id) {
    userService.blockUser(id);
  }

  @GetMapping("/unblock-user/{id}")
  @ResponseBody
  public void unblockUser(@PathVariable Long id) {
    userService.unblockUser(id);
  }
}
