package model.product;
import model.category.Category;
import utils.ConnectionMysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    Statement statement;
    public boolean register(Product product){

        boolean state = false;
        try{
            con = ConnectionMysql.getConnection();
            String query = "insert into product(name, content, stock, description, cost, idCategory) values(?,?,?,?,?,?);";
            pstm = con.prepareStatement(query);
            pstm.setString(1, product.getName());
            pstm.setString(2, product.getContent());
            pstm.setInt(3, product.getStock());
            pstm.setString(4, product.getDescription());
            pstm.setDouble(5, product.getCost());
            pstm.setInt(6, product.getCategory().getId());
            state = pstm.executeUpdate() == 1;
        }catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return state;
    }

    public boolean update(Product product){

        boolean state = false;
        try {
            con = ConnectionMysql.getConnection();
            String query = "UPDATE PRODUCT SET name = ?, content =?, stock=?, description=?, cost=? WHERE id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setString(1, product.getName());
            pstm.setString(2, product.getContent());
            pstm.setInt(3, product.getStock());
            pstm.setString(4, product.getDescription());
            pstm.setDouble(5, product.getCost());
            pstm.setInt(6, product.getId());
            state = pstm.executeUpdate() == 1;
        }catch (SQLException ex){
            ex.printStackTrace();
        }finally {
            closeConnection();
        }
        return state;
    }
    public boolean delete(Product product){
        boolean state = false;
        try{
            con = ConnectionMysql.getConnection();
            String query = "delete from product where id = ?;";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, product.getId());
            state = pstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return state;
    }

    public List<Product> findAll(){
        List<Product> productList = new ArrayList<>();
        try{
            con = ConnectionMysql.getConnection();
            String query = "select product.id, product.name, product.content,product.stock, product.description, product.cost, product.idCategory from product;";
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setContent(rs.getString("content"));
                product.setStock(rs.getInt("stock"));
                product.setDescription(rs.getString("description"));
                product.setCost(rs.getDouble("cost"));
                Category category = new Category();
                category.setId(rs.getInt("idCategory"));
                product.setCategory(category);
                productList.add(product);

            }
        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }finally{
            closeConnection();
        }
        return productList;
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
