package webapp.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import webapp.entity.Product;
import webapp.entity.ProductType;
import webapp.service.ProductService;

import javax.validation.Valid;

@Controller
public class ProductsController {

  @Autowired
  private ProductService productService;

  @GetMapping("/administrator/product-manager/{id}")
  public String showProduct(@PathVariable(required = false) Integer id, Model model) {
    model.addAttribute("productForm", new Product());
    return "administrator/product-manager";
  }

  @GetMapping("/administrator/product-manager")
  public String addProductPage(Model model) {
    model.addAttribute("productForm", new Product());
    return "administrator/product-manager";
  }

  @PostMapping("/administrator/product-manager")
  public String addProduct(@ModelAttribute("productForm") @Valid Product productForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "administrator/product-manager";
    }

    productForm.setType(productService.getTypeById(productForm.getType().getId()));
    productService.addProduct(productForm);

    return "redirect:/administrator/home";
  }

  @PostMapping("/administrator/product-manager/{id}")
  public String editProduct(@PathVariable(required = false) Integer id, @ModelAttribute("productForm") @Valid Product productForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "administrator/product-manager";
    }

    productForm.setType(productService.getTypeById(productForm.getType().getId()));
    productService.addProduct(productForm);

    return "redirect:/administrator/home";
  }

  @GetMapping(path = "/get-products", produces = "application/json")
  @ResponseBody
  public String getProducts() {
    Gson gson = new Gson();
    return gson.toJson(productService.allProducts().toArray(new Product[] {}));
  }

  @GetMapping(path = "/get-product-types", produces = "application/json")
  @ResponseBody
  public String getProductTypes() {
    Gson gson = new Gson();
    return gson.toJson(productService.allProductTypes().toArray(new ProductType[] {}));
  }

  @GetMapping(path = "/get-product/{id}", produces = "application/json")
  @ResponseBody
  public String getProductWithId(@PathVariable(required = false) int id) {
    Gson gson = new Gson();
    return gson.toJson(productService.getProductById(id));
  }

  @GetMapping( "/remove-product/{id}")
  public void removeProduct(@PathVariable int id) {
    productService.removeProduct(id);
  }

}
