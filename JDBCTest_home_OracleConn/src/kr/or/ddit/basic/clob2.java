package kr.or.ddit.basic;

import java.io.CharArrayReader;
import java.io.Reader;
import java.io.Writer;
import java.sql.ResultSet;

import oracle.jdbc.internal.OracleResultSet;
import oracle.sql.CLOB;

public class clob2 {

	public static void main(String[] args) {

//	2) CLOB

//	① DB에 CLOB 데이터형 쓰기

     // UPDATE 또는 INSERT 명령으로 DB 에 공간 확보
     String query = "UPDATE TABLE SET CLOB_DATA = EMPTY_CLOB() " ;
     stmt.executeUpdate(query);

     // 그런 다음 다시 요놈을 다시 SELECT
     query = "SELECT CLOB_DATA  FROM TABLE WHERE ~ " ;   

     stmt = dbConn.createStatement();
     ResultSet rs = stmt.executeQuery(query);

     if(rs.next()) {
//          CLOB clob = null;
          Writer writer = null;
          Reader src = null;
          char[] buffer = null;
          int read = 0;  

//          clob = ((OracleResultSet)rs).getCLOB(1);        
//          writer = clob.getCharacterOutputStream();

          // str -> DB에 넣을 내용
          src = new CharArrayReader(str.toCharArray());
          buffer = new char[1024];
          read = 0;
          while ( (read = src.read(buffer,0,1024)) != -1) {
               writer.write(buffer, 0, read); // write clob.
          }
          src.close();        
          writer.close();
     }

     dbConn.commit();
     dbConn.setAutoCommit(true);


// ② DB에서 CLOB 데이터형 읽기

      // SELECT
     String query = "SELECT CLOB_DATA  FROM TABLE WHERE ~ " ;   

     stmt = dbConn.createStatement();
     rs = stmt.executeQuery(query);

     if(rs.next()) {

          StringBuffer output = new StringBuffer();
          Reader input = rs.getCharacterStream("CLOB_DATA");
          char[] buffer = new char[1024];
          int byteRead = 0;
          while((byteRead=input.read(buffer,0,1024))!=-1){
               output.append(buffer,0,byteRead);
          }
         

          // contents -> CLOB 데이터가 저장될 String
          String contents = output.toString();


     }

     dbConn.commit();
     dbConn.setAutoCommit(true);

	}
}
