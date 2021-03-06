package homework;

import java.text.DecimalFormat;

public class JavaEnum_Planet {
/**
 * 문제)
 * 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
   (단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 
	
	예) 행성의 반지름(KM):
	수성(2439), 
	금성(6052), 
	지구(6371), 
	화성(3390), 
	목성(69911), 
	토성(58232), 
	천왕성(25362), 
	해왕성(24622)
 * @author PC-14
 *
 */

	public enum Planet {
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		int km;
		
		Planet(int r){
			this.km = r;
		}

		public int getKm() {
			return km;
		}
	};
	
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.########################");
		
		System.out.println("===== 행성의 면적 =====");
		for(Planet planet : Planet.values()) {
			double sphereS = (Math.round( (4 * 3.141592 * planet.getKm() * planet.getKm())/1000000)*1000000.0 );
			System.out.println(planet + " : " + df.format(sphereS) + "㎢");
		}			
	}
}
