import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class clob1 {
   public static void main(String args[]) throws Exception {
      //Registering the Driver
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      
      //Getting the connection
      String mysqlUrl = "jdbc:mysql://localhost/sampledatabase";
      Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");
      System.out.println("Connection established......");
      
      
      //Inserting values
      String query = "INSERT INTO articles_data(Name, Article, Logo) VALUES (?, ?, ?)";
      PreparedStatement pstmt = con.prepareStatement(query);
      pstmt.setString(1, "JavaFX");
      FileReader fileReader = new FileReader("E:\\images\\javafx_contents.txt");
      pstmt.setClob(2, fileReader);
      InputStream inputStream = new FileInputStream("E:\\images\\javafx_logo.jpg");
      pstmt.setBlob(3, inputStream);
      pstmt.execute();
      System.out.println("Record inserted......");
      
      
      //Retrieving the results
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * from articles_data");
      while(rs.next()) {
         String name = rs.getString("Name");
         Clob clob = rs.getClob("Article");
         Blob blob = rs.getBlob("Logo");
         System.out.println("Name: "+name);
         System.out.println("Clob value: "+clob);
         System.out.println("Blob value: "+blob);
         System.out.println("");
         System.out.print("Clob data is stored at: ");
         
         
         //Storing clob to a file
         int i, j =0;
         Reader r = clob.getCharacterStream();
         String filePath = "E:\\output\\"+name+"_article_content.txt";
         FileWriter writer = new FileWriter(filePath);
         while ((i=r.read())!=-1) {
               writer.write(i);
         }
         writer.close();
         System.out.println(filePath);
         j++;
         System.out.print("Blob data is stored at: ");
         
         
         InputStream is = blob.getBinaryStream();
         byte byteArray[] = new byte[is.available()];
         is.read(byteArray);
         filePath = "E:\\output\\"+name+"_article_logo.jpg";
         FileOutputStream outPutStream = new FileOutputStream(filePath);
         outPutStream.write(byteArray);
         System.out.println(filePath);
      }
   }
}