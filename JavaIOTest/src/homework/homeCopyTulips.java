package homework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 파일 복사 예제
 * @author PC-14
 *
 */
public class homeCopyTulips {
	public static void main(String[] args) {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			
			File file = new File("d:/D_Other/Tulips.jpg"); //읽기 위해 파일 객체 생성
			fis = new FileInputStream(file);			   //read하면 1byte씩 읽는다
			fos = new FileOutputStream("d:/D_Other/복사본_Tulips.jpg");
			
			int c;
			
			while((c=fis.read()) != -1) {
				fos.write(c);
			}
			System.out.println("파일 복사 완료...");
			fis.close();
			fos.close();
			
		}catch(IOException ex) {
			
		}
	}
}
