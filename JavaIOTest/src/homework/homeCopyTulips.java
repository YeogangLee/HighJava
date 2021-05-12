package homework;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			
			File file = new File("d:/D_Other/Tulips.jpg"); //읽기 위해 파일 객체 생성
			fis = new FileInputStream(file);			   //read하면 1byte씩 읽는다
			fos = new FileOutputStream("d:/D_Other/복사본_Tulips2.jpg");
			
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			
			int c;
			
//			while((c=fis.read()) != -1) {
//				fos.write(c);
//			}
			
//			fis.close();
//			fos.close();
			
			
			//Buffer 스트림 사용, 결과는 동일
			while((c=bis.read()) != -1) {
				bos.write(c);
			}
			
			bis.close();
			bos.close();
			
			System.out.println("파일 복사 완료...");
			
		}catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
