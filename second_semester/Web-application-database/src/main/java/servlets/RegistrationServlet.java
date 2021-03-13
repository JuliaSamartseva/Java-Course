package servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {
  private static final int MIN_USERNAME_LENGTH = 5;
  private static final int MAX_USERNAME_LENGTH = 20;
  public static final String USER_TABLE_NAME = "UserInfo";
  public static final String ISOLATE_HOME_PAGE = "/client/home.html";
  public static final String VOLUNTEER_HOME_PAGE = "/administrator/home.html";

  public RegistrationServlet() {
    super();
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {

  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) {

  }
}
