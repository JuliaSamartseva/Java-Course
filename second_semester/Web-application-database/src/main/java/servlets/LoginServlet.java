package servlets;

import com.google.gson.Gson;
import data.User;
import data.UserType;
import service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/servlets/login")
public class LoginServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(LoginServlet.class.getName());

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    log.info("Received data from logging in.");
    Map<String, String[]> parameterMap = request.getParameterMap();
    String name = parameterMap.get("username")[0];
    String password = parameterMap.get("password")[0];
    UserType userType = UserService.findUser(name, password);

    Gson gson = new Gson();
    if (userType == null) response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    else {
      log.info("Received info about the user in the servlet.");
      Cookie loginCookie = new Cookie("user", name);
      Cookie typeCookie = new Cookie("type", userType.toString());
      loginCookie.setPath("/");
      typeCookie.setPath("/");
      response.addCookie(loginCookie);
      response.addCookie(typeCookie);

      log.info("Redirecting to " + userType.toString());
      response
              .getWriter()
              .println(gson.toJson(userType));
    }
  }
}
