package br.profileManager.src.main.java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class basicTest {

	@Test void ValidationElement_String() {
		
		LocalDate xxx = LocalDate.parse( "2013-09-18" );
		
		System.out.println(xxx.toString());
		Long ms = xxx.toEpochDay();
		System.out.println(ms.toString());
		

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date convertedCurrentDate = sdf.parse("2013-09-18");
//		String date=sdf.format(convertedCurrentDate );
//		System.out.println(date);
		
		 	
		
		assertEquals("XXX", new Options<String>("XXX")
				.getCodeView()
				, "should have been «XXX»");
	}
}
