package kr.or.ddit.basic;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * XML DOM을 이용한 문서 생성 예제
 * @author PC-14
 *
 */
public class DOMCreationTest {
/*
 * W3C(World Wide Web Consortium) DOM(Document Object Model)에 대하여...
 * 
 * DOM은 문서에 접근하는 표준 방법으로서, 
 * 이를 이용하면 플랫폼 및 개발 언어에 독립적으로
 * 문서의 내용,구조 및 스타일 정보를 동적으로 핸들링(접근, 수정, 삭제) 할 수 있다.
 * 
 * W3C DOM 표준은 다음과 같이 크게 3가지로 나누어 볼 수 있다.
 * 
 *	Core DOM - 모든 문서타입을 위한 핵심 표준 모델(API)
 * 	XML DOM - XML문서를 위한 표준 모델
 * 	HTML DOM - HTML문서를 위한 표준 모델
 * 
 * 자바는 항상 뭐로 만들어요? 객체로 만든다
 * 문서를 객체화하기 위한 인터페이스가 필요
 * 그 부분은 W3C라고 하는 단체에서 DOM이라는 표준으로, 인터페이스를 만들어놓은 것
 * 표준에 맞춰 코딩하면, 문서를 핸들링할 수 있다
 * 
 * DOM을 지원하는 표준이 3가지가 있다 ... * 
 */
	
	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		
		// XML 문서를 생성하기 위한 DocumentBuilder 객체 생성하기
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		//돔 표준(DOM api)을 이용하면, 문서 핸들링이 가능해진다.
		
		// Document 객체 생성
		//반드시 org.w3c.dom 으로 Document 인터페이스 설정
		Document document = documentBuilder.newDocument();
		
		//root 엘리먼트 생성
		//반드시 org.w3c.dom 으로 Element 인터페이스 설정
		Element root = document.createElement("data");
		
		//boolist 엘리먼트 생성
		Element booklist = document.createElement("booklist");
		
		// book 엘리먼트 생성 및 속성값 설정하기
		Element book = document.createElement("book");
		book.setAttribute("isbn", "B001");
		book.setAttribute("kind", "JAVA");
		
		//자식 엘리먼트 생성 및 설정
		Element title = document.createElement("title");
		title.setTextContent("자바초급");
		Element author = document.createElement("author");
		author.setTextContent("이순신");
		Element price = document.createElement("price");
		price.setTextContent("25000");
		
		// book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		booklist.appendChild(book);
		
		//---------------------------------------------------------
		//book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B002");
		book.setAttribute("kind", "JAVA");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("자바고급");
		author = document.createElement("author");
		author.setTextContent("홍길동");
		price = document.createElement("price");
		price.setTextContent("30000");
		
		// book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		//booklist에 book추가하기
		booklist.appendChild(book);
		
		//---------------------------------------------------------
		//book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B003");
		book.setAttribute("kind", "DB");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("DB설계");
		author = document.createElement("author");
		author.setTextContent("이몽룡");
		price = document.createElement("price");
		price.setTextContent("30000");
		
		// book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		//booklist에 book추가하기
		booklist.appendChild(book);
		
		//---------------------------------------------------------
		//book 엘리먼트 생성 및 속성값 설정하기
		book = document.createElement("book");
		book.setAttribute("isbn", "B004");
		book.setAttribute("kind", "DB");
		
		//자식 엘리먼트 생성 및 설정
		title = document.createElement("title");
		title.setTextContent("DB구현");
		author = document.createElement("author");
		author.setTextContent("강감찬");
		price = document.createElement("price");
		price.setTextContent("40000");
		
		// book 엘리먼트에 자식 엘리먼트 추가하기
		book.appendChild(title);
		book.appendChild(author);
		book.appendChild(price);
		
		//booklist에 book추가하기
		booklist.appendChild(book);
		
		//---------------------------------------------------------
		
		//루트 엘리먼트에 booklist 추가하기
		root.appendChild(booklist);
		
		//도큐먼트에 root 엘리먼트 추가하기
		document.appendChild(root);
		
		//XML 문자열로 변환하기
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		DOMSource source = new DOMSource(document);
		StreamResult result = new StreamResult(baos);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		
		Transformer transformer = transformerFactory.newTransformer();
		
		//출력 인코딩 설정하기
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		//들여쓰기 설정(공백: 2)
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		transformer.transform(source, result);
		
		try(FileOutputStream fos = new FileOutputStream("./src/new_book.xml")) {
			fos.write(baos.toByteArray());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	
}
































