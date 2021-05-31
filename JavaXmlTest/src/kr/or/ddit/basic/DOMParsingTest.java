package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParsingTest {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		new DOMParsingTest().parse();
	}
	
	public void parse() throws ParserConfigurationException, SAXException, IOException {
		// DOM Document 객체 생성하기 위한 메서드
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); //new키워드 사용X
		
		// DOM 파서로부터 입력받은 파일을 파싱하도록 요청
		DocumentBuilder builder = dbf.newDocumentBuilder();
		//빌더 왜 만들었어? document 만들어주려구, Document xmlDoc을 만들기 위해, 빌더 만들었다
		
		// XML파일 지정
		String url = new File("./src/new_book.xml").toURI().toString();
		//URI로 바꾼 후 toString(), URI가 궁금하면 아래처럼 찍어보기
		System.out.println("URI : " + new File("./src/new_book.xml").toURI());
		
		// DOM 파서로부터 입력받은 파일을 파싱하도록 요청
		// (DOM Document Object를 리턴함.)
		Document xmlDoc = builder.parse(url);
		
		// DOM Document 객체로부터 루트 엘리먼트 및 자식 객체 가져오기
		Element root = xmlDoc.getDocumentElement();
		System.out.println("루트 엘리먼트 태그명: " + root.getTagName());
		
		
		// 하위 엘리먼트 접근방법1: getElementsByTagName()메서드를 이용
		
		//돔에서는 모든 게 노드, 여러 개가 리턴돼서 List로 받을 건데
		//최상위타입(노드)으로 받으면 나중에 캐스팅해서 쓰면 된다. 가장 최상위 인터페이스
		//자바에서 Object로 받고 나중에 사용할 자료형으로 캐스팅하는 것처럼
		
		NodeList bookNodeList = root.getElementsByTagName("book");	//엘리먼트라면 다 갖고있는 메서드, 엘리먼트도 하나의 인터페이스
		Node firstBookNode = bookNodeList.item(0); // 첫 번째 항목		//노드라면 다 item() 메서드를 가지고 있다
		Element firstBookElement = (Element) firstBookNode;			//캐스팅, 같은 객체지만 타입만 다르게 바꿔놨다. 둘 다 firstBookNode
		
		// 속성 접근방법 1 : 엘리먼트 객체의 getAttribute()메서드 이용 
		//엘리먼트라면 다 가지고 있는 메서드이므로, 위에서 캐스팅한 element를 사용
		System.out.println("엘리먼트 객체의 getAttribute()메서드 이용 : " + firstBookElement.getAttribute("isbn"));
		
		// 속성 접근방법 2 : 노드 객체의 getAttributes()메서드 이용
		//				 (속성 노드 접근하기)
		//노드라면 다 가지고 있는 메서드, 위에서 형변환하지 않은 Node를 사용
		NamedNodeMap nodeMap = firstBookNode.getAttributes();
		//NodeMap = 여러 개의 데이터가 담긴다, Map이니까 유추 가능
		//1. 노드맵으로 일단 다 담고
		
		System.out.println("노드 객체의 getAttributes()메서드 이용 : " + nodeMap.getNamedItem("isbn").getNodeValue());
		//NodeMap이 제공하는 메서드 getNamedItem(), getNodeValue()
		//2. isbn이라는 이름을 가진 item들 다 찾고, 거기서 value를 찾는다
		//엘리먼트에 비해 복잡
		
		// 하위 엘리먼트 접근 방법 2 : getChildNodes() 이용
		NodeList firstBookChildNodeList = firstBookNode.getChildNodes();
		
		// 엔터키에 해당하는 부분이 읽힐 수 있으므로, getChildNodes()보다는
		// getElementsByTagName()을 이용해 접근하는 게 좋다.
		Node titleNode = firstBookChildNodeList.item(1);
		
		//first인데 왜 인덱스를 0이 아닌 1을 썼을까 => 엔터, 인덴트 같은 텍스트 노드 때문에
		
		//0번 값 확인 가능
//		System.out.println(firstBookChildNodeList.item(0));
				
		
		Element titleElement = (Element) titleNode;
		System.out.println("titleElement.getTagName() => " + titleElement.getTagName());
		System.out.println("titleElement.getTextContent() => " + titleElement.getTextContent());
		
		//전체 출력하기
		System.out.println("-----------------------------------------------------------------");
		System.out.printf("%8s %8s %15s %10s %8s\n", "ISBN", "분류", "제목", "저자", "가격");
		System.out.println("-----------------------------------------------------------------");
		for(int i=0; i<bookNodeList.getLength(); i++) {
			Node bookNode = bookNodeList.item(i);
			Element element = (Element) bookNode;
			String isbn = element.getAttribute("isbn");
			String kind = element.getAttribute("kind");
			String title = element.getElementsByTagName("title").item(0).getTextContent().trim();
			String author = element.getElementsByTagName("author").item(0).getTextContent().trim();
			String price = element.getElementsByTagName("price").item(0).getTextContent().trim();
			String str = String.format("%8s %8s %15s %10s %8s\n", isbn, kind, title, author, price);
			System.out.println(str);			
		}

		System.out.println("-----------------------------------------------------------------");
		
	}
}
