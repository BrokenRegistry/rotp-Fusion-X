package mod.br.profileManager;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.KeyEvent;

import org.junit.jupiter.api.Test;

class UserProfilesTest {

	private ClientClasses clientObject = new ClientClassesTest();
	private UserProfiles userProfiles;
//	private AbstractParameter<?, ?, ClientClasses> param;
	
	private void init() {
		userProfiles = new UserProfiles();
		userProfiles.initAndLoadProfiles(clientObject);		
	}
	
	@Test void initAndLoadProfiles_ClientClasses() {
		userProfiles = new UserProfiles();
		assertEquals(false, userProfiles.isInitialized(), "Not yet initialized");
		userProfiles.initAndLoadProfiles(clientObject);
		assertEquals(true, userProfiles.isInitialized(), "Should be initialized");
		assertEquals(true
				, userProfiles.isParameterEnabled("MAXIMIZE EMPIRES SPACING")
				, "Should be initialized");
		assertEquals(true
				, userProfiles.isParameterEnabled("NO PLANET MULTIPLIER")
				, "Should be initialized");
		assertEquals(true
				, userProfiles.isParameterEnabled("GUI OPPONENTS RACE LIST")
				, "Should be initialized");
		assertEquals(true
				, userProfiles.isParameterEnabled("OPPONENTS RACE LIST")
				, "Should be initialized");
	}

//	@Test
//	void getAllProfiles() {
//		init();
//		System.out.println("getAllProfiles()");
//		System.out.println(userProfiles.getAllProfiles().toString());
//		System.out.println();
//	}

	@Test
	void processKey_U() {
		init();
		assertEquals(false
				, userProfiles.processKey(KeyEvent.VK_U, false, "", clientObject)
				, "This KeyEvent sould return false");
	}

//	@Test void GuiOpponentRaceList() {
//		init();
//		userProfiles.processKey(KeyEvent.VK_U, false, "", clientObject);
//		param = userProfiles.getParameter("GUI OPPONENTS RACE FILTER");
//		String result = param.toString(userProfiles.getAllProfiles());
//		System.out.println(result);
//		Lines<?, ?> line = param.getProfileLine("User");
//		AbstractT<?> value =line.getValue();
//		System.out.println("List");
//		System.out.println(value.codeViewList().toString());
//		System.out.println(value.toString());
//		System.out.println(value.toString());
//		System.out.println(line.toString());
//	}

	@Test
	void isParameterEnabled_String() {
		// TODO
	}

}
