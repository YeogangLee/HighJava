package kr.or.ddit.basic;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DOMParsingExam_myContent {

	public void parse(){
		try
        {
             //DOM Document 객체 생성하기 위한 메서드
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

             //DOM 파서로부터 입력받은 파일을 파싱하도록 요청
             DocumentBuilder builder = dbf.newDocumentBuilder();
            
            URL url = new URL("http://175.125.91.94/openapi/service/rest/meta/koccaMeta");
     		
     		System.out.println("url: " + url);
     		
             //DOM 파서로부터 입력받은 파일을 파싱하도록 요청 (DOM Document object를 리턴함.)
             Document xmlDoc = builder.parse(url.toString());

             // DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
             Element root = xmlDoc.getDocumentElement();
             System.out.println("루트 엘리먼트 태그명 : " + root.getTagName());
            
             NodeList rowNodeList = root.getElementsByTagName("item");
             NodeList liNodeList = root.getElementsByTagName("li");
             NodeList ulNodeList = root.getElementsByTagName("ul");
             NodeList dbNodeList = root.getElementsByTagName("collectionDb");
             NodeList pNodeList = root.getElementsByTagName("p");
             
             System.out.println("item : " + rowNodeList.getLength());
             System.out.println("ul : " + ulNodeList.getLength());
             System.out.println("li : " + liNodeList.getLength());
             System.out.println("db : " + dbNodeList.getLength());
             System.out.println("p : " + pNodeList.getLength());
             
            String creator = root.getElementsByTagName("creator").item(0).getTextContent();

            if(creator.equals("한국콘텐츠진흥원")) {

            	for(int i = 0; i < rowNodeList.getLength(); i++) {
            		
            		Element element = (Element) rowNodeList.item(i);
            		String collectionDb2 = element.getElementsByTagName("collectionDb").item(0).getTextContent().trim();
            		String creator2 = element.getElementsByTagName("creator").item(0).getTextContent().trim();
//            		String description2 = element.getElementsByTagName("description").item(0).getTextContent().trim();
            		String regDate2 = element.getElementsByTagName("regDate").item(0).getTextContent().trim();
            		String subjectCategory2 = element.getElementsByTagName("subjectCategory").item(0).getTextContent().trim();
            		String title2 = element.getElementsByTagName("title").item(0).getTextContent().trim();
            		String str = String.format("%s \t %s \t %s \n\n %s \t %s \n", 
            									collectionDb2, creator2, regDate2, subjectCategory2, title2).trim();
            		
            		System.out.println("==================================================================");
            		System.out.println(str);            		
            		System.out.println("------------------------------------------------------------------");
            		
            	}
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		DOMParsingExam_myContent parser = new DOMParsingExam_myContent();
		parser.parse();
	}
}
