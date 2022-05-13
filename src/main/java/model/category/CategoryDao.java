package model.category;
import model.category.Category;
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
            String query = "insert into category(name, description) values(?,?);";
            pstm = con.prepareStatement(query);
            pstm.setString(1, category.getName());
            pstm.setString(2, category.getDescription());
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
            String query = "UPDATE CATEGORY SET CATEGORY.description = ? WHERE CATEGORY.Id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setString(1, category.getDescription());
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
            String query = "select category.id, category.name, category.description from category;";
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
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
