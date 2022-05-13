package category.model;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

import javax.ws.rs.core.MultivaluedMap;
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


    public String execute() throws Exception {
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

}
