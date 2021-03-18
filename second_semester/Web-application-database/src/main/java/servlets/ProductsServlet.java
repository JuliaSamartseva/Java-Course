package servlets;

import com.google.gson.Gson;
import data.Product;
import data.ProductType;
import data.UserType;
import service.ProductsService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@WebServlet("/servlets/products/*")
public class ProductsServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(ProductsServlet.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    log.info("Received get request");
    if (response == null) {
      throw new IllegalArgumentException("Response must not be null.");
    }

    if (request == null) {
      throw new IllegalArgumentException("Request must not be null.");
    }

    Gson gson = new Gson();

    if (request.getPathInfo() == null || request.getPathInfo().equals("/")) {
      log.info("Received request to get all products");
      List<Product> products = ProductsService.getProducts();
      response
              .getWriter()
              .println(gson.toJson(getProductData(products).toArray(new ProductData[] {})));
      return;
    }

    String[] urls = request.getPathInfo().split("/");
     if (urls.length == 2) {
      if (urls[1].equals("product-types")) {
        log.info("Received request to get all product types");
        List<ProductType> productTypes = ProductsService.getProductTypes();
        response
                .getWriter()
                .println(gson.toJson(productTypes.toArray(new ProductType[] {})));
      }
    }

  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    log.info("Received data from the product editor. Adding the product to the database.");
    Map<String, String[]> parameterMap = request.getParameterMap();
    String name = parameterMap.get("product-name")[0];
    int price = Integer.parseInt(parameterMap.get("price")[0]);
    String description = parameterMap.get("description")[0];
    ProductType productType = ProductsService.getProductType(Integer.parseInt(parameterMap.get("type")[0]));
    Product product = new Product(name, price, description, productType);
    ProductsService.addProduct(product);
    response.setStatus(HttpServletResponse.SC_OK);
  }

  private List<ProductData> getProductData(List<Product> products) {
    List<ProductData> result = new ArrayList<>();
    for (Product product : products) {
      result.add(
          new ProductData(
              product.getId(),
              product.getName(),
              product.getPrice(),
              product.getDescription(),
              product.getType().getName()));
    }
    return result;
  }

  private static class ProductData {
    private final int productId;
    private final String name;
    private final int price;
    private final String description;
    private final String productType;

    public ProductData(
        int productId, String name, int price, String description, String productType) {
      this.productId = productId;
      this.name = name;
      this.price = price;
      this.description = description;
      this.productType = productType;
    }
  }
}
