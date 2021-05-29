package kr.or.ddit.basic;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * XML DOM 을 이용한 XML 문서 파싱 예제(레시피 정보 조회 API)
 * @author macween
 *
 */
public class DOMParsingExam {

	public void parse(){
		try
        {
             //DOM Document 객체 생성하기 위한 메서드
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

             //DOM 파서로부터 입력받은 파일을 파싱하도록 요청
             DocumentBuilder builder = dbf.newDocumentBuilder();

            String svcKey = "Grid_20150827000000000227_1";  // 레시피 재료 정보 조회 서비스
     		String apiKey = "1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c"; // 개인별 발급.
     		String startIdx = "1";  	// 레시피 재료 시작 순번
     		String endIdx = "5";		// 레시피 재료 종료 순번
     		String recipeId = "195428";	// 레시피가 궁금한 음식 ID
     		recipeId = "109"; 

     		URL url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
     				+ "/xml/"+ svcKey + "/"+startIdx +"/" + endIdx
     				+"?RECIPE_ID=" +  recipeId);
     		
     		/*
     		 * 스타트 idx, 엔드 idx 정확한 값을 모른다, 그래서 코드를 작성할 때 임의로 기본값으로 1,5를 지정해서 url로 보낸다
     		 * 루트에 totalCnt라는 태그로 던져줘요
     		 * 얘네는 기본적으로 다 안쏴줘요
     		 * 누가 장난치든 아니든 다 날라갈테니, 범위를 지정해서 요청해야 결과를 받을 수 있도록 api를 설계한 것 
     		 * => 페이징 개념
     		 */
     		
     		System.out.println("url: " + url);
     		/*
     		 * http://211.237.50.150:7080/openapi/
     		 * 1df7e8571e8df3f8cbc9b87691ca7d3e4d04f03c593d477e52bf67b03f0b6e1c
     		 * /xml/
     		 * Grid_20150827000000000227_1
     		 * /
     		 * 1
     		 * /
     		 * 5
     		 * ?RECIPE_ID=
     		 * 195428 
     		 */
     		//이거 웹브라우저 주소창에 치면 xml파일을 웹브라우저에서 확인 가능    		

             //DOM 파서로부터 입력받은 파일을 파싱하도록 요청 (DOM Document object를 리턴함.)
             Document xmlDoc = builder.parse(url.toString());

             // DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
             Element root = xmlDoc.getDocumentElement();
             System.out.println("루트 엘리먼트 태그명 : " + root.getTagName());    //Grid_20150827000000000227_1

             // 하위 엘리먼트 접근방법1 : getElementsByTagName() 메서드를 이용
             NodeList rowNodeList = root.getElementsByTagName("row");

            String code = root.getElementsByTagName("code").item(0).getTextContent();
            String totalCnt = root.getElementsByTagName("totalCnt").item(0).getTextContent();
            endIdx = totalCnt;
            
            url = new URL("http://211.237.50.150:7080/openapi/"+ apiKey
     				+ "/xml/"+ svcKey + "/"+startIdx +"/" + endIdx
     				+"?RECIPE_ID=" +  recipeId);
            
            System.out.println("url2: " + url);
            /*
             * 지금은 totalCnt를 이용해서 시작 인덱스와 끝 인덱스를 알 수 있다
             * url로 접속하면 이전의 url과는 다른 결과의 xml 파일을 받게 된다
             * 모든 행을 읽어온다, 아까는 자바 코드에서 지정했던 1행부터 5행까지였는데.
             */            

             xmlDoc = builder.parse(url.toString());

             root = xmlDoc.getDocumentElement();

             rowNodeList = root.getElementsByTagName("row");

            if(code.equals("INFO-000")) {

            	for(int i = 0; i < rowNodeList.getLength(); i++) {
            		Element element = (Element) rowNodeList.item(i);
            		String rowNum = element.getElementsByTagName("ROW_NUM").item(0).getTextContent();
            		String irdntNm = element.getElementsByTagName("IRDNT_NM").item(0).getTextContent();
            		String irdntCpcty = element.getElementsByTagName("IRDNT_CPCTY").item(0).getTextContent();
            		String irdntTypNm = element.getElementsByTagName("IRDNT_TY_NM").item(0).getTextContent();
            		String str = String.format("%3s %8s %10s %10s %8s", rowNum, recipeId, irdntTypNm, irdntNm, irdntCpcty);
            		System.out.println(str);
            		System.out.println("------------------------------------------------------------------");
            	}
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		DOMParsingExam parser = new DOMParsingExam();
		parser.parse();
	}
}
