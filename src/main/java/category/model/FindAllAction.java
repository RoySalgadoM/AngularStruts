package category.model;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class FindAllAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    CategoryDao categoryDao = new CategoryDao();
    List<Category> categoryList = new ArrayList<>();


    public String execute() throws Exception {
        categoryList = categoryDao.findAll();
        return SUCCESS;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
