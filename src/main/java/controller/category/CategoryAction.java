package controller.category;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import model.category.Category;
import model.category.CategoryDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    String params;
    Map<String,Object> result = new HashMap<>();
    private Category category = new Category();
    CategoryDao categoryDao = new CategoryDao();
    List<Category> categoryList = new ArrayList<>();
    public String findAll() throws Exception {
        categoryList = categoryDao.findAll();
        return SUCCESS;
    }
    public String register() throws Exception {
        Gson gs = new Gson();
        category = gs.fromJson(params,Category.class);
        result.put("registered",categoryDao.register(category));
        return SUCCESS;
    }
    public String update() throws Exception {
        Gson gs = new Gson();
        category = gs.fromJson(params,Category.class);
        result.put("updated",categoryDao.update(category));
        return SUCCESS;
    }
    public String delete() throws Exception {
        Gson gs = new Gson();
        category = gs.fromJson(params,Category.class);
        result.put("deleted",categoryDao.delete(category));
        return SUCCESS;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Map<String, Object> getResult() {
        return result;
    }
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
