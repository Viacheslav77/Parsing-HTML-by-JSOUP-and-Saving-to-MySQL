package parsinghtml.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import parsinghtml.entity.Product;

public class JavaToMySQL {

    private static final String connectionUrl = "jdbc:mysql://localhost:3306/for?useUnicode=true&characterEncoding=utf8";
    
    private static Properties getProperties (){
        
        Properties properties = new Properties();
        
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");
        return properties;
        }
    
    public static void insertThisProductAllField(Product product) { 
        
        String sql = "insert into dir_name (id, name_ru, name_en)" + " values (?,?,?)";
       
        try (Connection conn = DriverManager.getConnection(connectionUrl, getProperties ());

            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setLong  (1, product.getId());
                ps.setString(2, product.getNameRu());
                ps.setString(3, product.getNameEn());
                
                ps.addBatch();
   
            ps.executeBatch();
      
        } catch (SQLException e) {
            System.out.println(e);
        }
  
       sql = "insert into dir_description (id, short_descr_ru, short_descr_en, descr_ru, descr_en)" + " values (?,?,?,?,?)";
       
        try (Connection conn = DriverManager.getConnection(connectionUrl, getProperties ());

            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setLong  (1, product.getId());
                ps.setString(2, product.getShortDescriptionRu());
                ps.setString(3, product.getShortDescriptionEn());
                ps.setString(4, product.getDescriptionRu());
                ps.setString(5, product.getDescriptionEn());
                
                
                ps.addBatch();
   
            ps.executeBatch();
      
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        sql = "insert into dir_images(id, image_1)" + " values (?,?)";
       
        try (Connection conn = DriverManager.getConnection(connectionUrl, getProperties ());

            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setLong  (1, product.getId()+10);
                ps.setBytes (2, product.getImage());
               
                ps.addBatch();
   
            ps.executeBatch();
      
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        sql = "insert into dir_price (id, price_uk)" + " values (?,?)";
       
        try (Connection conn = DriverManager.getConnection(connectionUrl, getProperties ());

            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setLong  (1, product.getPriceId());
                ps.setDouble(2, product.getPrice());

                ps.addBatch();
   
            ps.executeBatch();
      
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        sql = "insert into product_properties (id, name_id, vendor_code, category_id, descr_id, image_id, rating, vote_count, price_id, currency_id)" 
                + " values (?,?,?,?,?,?,?,?,?,?)";
       
        try (Connection conn = DriverManager.getConnection(connectionUrl, getProperties ());

            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setLong  (1, product.getId());          // id
                ps.setLong  (2, product.getId());          // name_id           
                ps.setInt   (3, product.getVendorCode());  // vendor_code
                ps.setInt   (4, product.getCategoryId());  // category_id
                ps.setLong  (5, product.getId());          // descr_id
                ps.setLong  (6, product.getId()+10);       // image_id             
                ps.setInt   (7, product.getRating());      // rating           
                ps.setInt   (8, product.getVoteCount());   // vote_count
                ps.setDouble(9, product.getPriceId());     // price_id 
                ps.setInt   (10, product.getCurrency_id());// currency_id 
                
                ps.addBatch();
   
            ps.executeBatch();
      
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        sql = "insert into health_product (id, health_id)" + " values (?,?)";
       
        try (Connection conn = DriverManager.getConnection(connectionUrl, getProperties ());

            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setLong  (1, product.getSection_id());
                ps.setLong  (2, product.getId());
                
                ps.addBatch();
   
            ps.executeBatch();
      
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    }

