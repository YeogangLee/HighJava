package kr.or.ddit.basic;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathParsingTest {
/*
 *	[ XPath ]에 대하여...
 *	
 *	자바에서 내장 패키지(javax.xml.xpath)로 제공하는 라이브러리로 XML형식의 웹문서, 파일, 문자열을 파싱하는 데 사용
 *
 *	사용 예)
 *	item	: <item>요소를 모두 선택함.
 *	/item	: "/" 루트 노드의 자식 노드 중에서 <item>엘리먼트를 선택함.
 *			  (앞에 "/"가 들어가면 절대 경로이다.)
 *	item/java : <item>엘리먼트의 자식 노드 중 <java>엘리먼트를 선택함.
 *  //		: 현재 노드의 위치와 상관없이 지정된 노드부터 탐색
 *  item/@id : 모든 <item>엘리먼트의 id속성 노드를 모두 선택함.
 *  item[k]	: <item>엘리먼트 중에서 k번째 <item>엘리먼트
 *  item[@attr=val]	: attr이라는 속성이 val값을 가지는 모든 <item>엘리먼트   
 */
	
	public static void main(String[] args) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		new XPathParsingTest().parse();
	}
	
	public void parse() throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		File file = new File(getClass().getResource("./book.xml").getPath());
		FileReader fr = new FileReader(file);
		
		// XML Document 객체 생성
		InputSource is = new InputSource(fr);
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		
		//Xpath 객체 생성하기
		XPath xPath = XPathFactory.newInstance().newXPath();
		
		//NodeList 가져오기 : booklist 아래에 있는 모든 book노드 선택하기
		NodeList bookList = (NodeList) xPath.evaluate("//booklist/book", document, XPathConstants.NODESET);
		
		System.out.println("//booklist/book 검색 결과...");
		System.out.println("-----------------------------------------");
		
		for(int i=0; i<bookList.getLength(); i++) {
			System.out.println(bookList.item(i).getTextContent());
		}
		System.out.println("-----------------------------------------");
		
		//kind 속성이 JAVA인 모든 Node의 isbn attribute값 가져오기
		Node node = (Node) xPath.evaluate("//*[@kind='JAVA']", document, XPathConstants.NODE);
		
		System.out.println(node.getAttributes().getNamedItem("isbn").getTextContent());
		
		//isbn이 B001인 Node의 textContent값 가져오기
		System.out.println(xPath.evaluate("//*[@isbn='B001']", document, XPathConstants.STRING));
				
	}
}
