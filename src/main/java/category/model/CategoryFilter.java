package category.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CategoryFilter extends HttpServlet implements Filter{
    private static final long serialVersionUID = 1L;
    List<Category> categoryList = new ArrayList<>();
    public void doFilter(ServletRequest Request, ServletResponse Response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) Request;
        HttpServletResponse response = (HttpServletResponse) Response;
        HttpSession session = request.getSession();
        String url = request.getServletPath();
        String contextPath = request.getContextPath();

        if (url.equals(""))
            url += "/";
        if(!url.startsWith("/register.jsp"))
        {
            CategoryDao categoryDao = new CategoryDao();
            categoryList = categoryDao.findAll();

        }
        filterChain.doFilter(Request, Response);
    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}
