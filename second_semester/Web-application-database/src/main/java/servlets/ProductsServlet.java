package servlets;

import com.google.gson.Gson;
import data.Product;
import service.ProductsService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/administrator/fetch-products")
public class ProductsServlet extends HttpServlet {
  private static final Logger log = Logger.getLogger(ProductsServlet.class.getName());

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    log.info("Fetching products from the database.");
    if (response == null) {
      throw new IllegalArgumentException("Response must not be null.");
    }

    if (request == null) {
      throw new IllegalArgumentException("Request must not be null.");
    }

    Gson gson = new Gson();
    List<Product> products = ProductsService.getProducts();
    response.getWriter().println(gson.toJson(getProductData(products).toArray(new ProductData[] {})));
  }
  
  private List<ProductData> getProductData(List<Product> products) {
    List<ProductData> result = new ArrayList<>();
    for (Product product : products) {
      result.add(new ProductData(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getType().getName()));
    }
    return result;
  }

  private static class ProductData {
    private final int productId;
    private final String name;
    private final int price;
    private final String description;
    private final String productType;

    public ProductData(int productId, String name, int price, String description, String productType) {
      this.productId = productId;
      this.name = name;
      this.price = price;
      this.description = description;
      this.productType = productType;
    }
  }
}
