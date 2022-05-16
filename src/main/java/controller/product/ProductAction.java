package controller.product;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import model.category.Category;
import model.category.CategoryDao;
import model.product.Product;
import model.product.ProductDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAction extends ActionSupport {
    private static final long serialVersionUID = 1L;
    Product product = new Product();
    String params;
    Map<String,Object> result = new HashMap<>();
    ProductDao productDao = new ProductDao();
    List<Product> productList = new ArrayList<>();

    public String findAll() throws Exception {
        productList = productDao.findAll();
        return SUCCESS;
    }
    public String register() throws Exception {
        Gson gs = new Gson();
        product = gs.fromJson(params,Product.class);
        result.put("registered",productDao.register(product));
        return SUCCESS;
    }
    public String update() throws Exception {
        Gson gs = new Gson();
        product = gs.fromJson(params,Product.class);
        result.put("updated",productDao.update(product));
        return SUCCESS;
    }
    public String delete() throws Exception {
        Gson gs = new Gson();
        product = gs.fromJson(params,Product.class);
        result.put("deleted",productDao.delete(product));
        return SUCCESS;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

}
