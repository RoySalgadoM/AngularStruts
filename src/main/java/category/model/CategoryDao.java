package category.model;
import com.opensymphony.xwork2.ActionContext;
import utils.ConnectionMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    Statement statement;
    public boolean register(Category category){

        boolean state = false;
        try{
            con = ConnectionMysql.getConnection();
            String query = "insert into category(nombre, descripcion) values(?,?);";
            pstm = con.prepareStatement(query);
            pstm.setString(1, category.getNombre());
            pstm.setString(2, category.getDescripcion());
            state = pstm.executeUpdate() == 1;
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return state;
    }

    public boolean update(Category category){

        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            String query = "UPDATE CATEGORY SET CATEGORY.descripcion = ? WHERE CATEGORY.Id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setString(1, category.getDescripcion());
            pstm.setInt(2, category.getId());
            state = pstm.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public boolean delete(Category category){
        boolean state = false;
        try{
            con = ConnectionMysql.getConnection();
            String query = "delete from category where id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, category.getId());
            state = pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return state;
    }

    public List<Category> findAll(){
        List<Category> categoryList = new ArrayList<>();
        try{
            con = ConnectionMysql.getConnection();
            String query = "select category.id, category.nombre, category.descripcion from category;";
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setNombre(rs.getString("nombre"));
                category.setDescripcion(rs.getString("descripcion"));
                categoryList.add(category);

            }
            System.out.println(categoryList);
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }finally{
            closeConnection();
        }
        return categoryList;
    }
    public void closeConnection(){
        try{
            if(con != null){
                con.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
