package br.config.old;

import java.util.List;

public class Cfg_Block_UserActions {
// public class Cfg_Block_UserActions extends Cfg_BlockValidation{
//	protected static final String LOAD_ENABLED  = "ENABLE_LOAD_LIST";
//	protected static final String WRITE_ENABLED = "ENABLE_WRITE_LIST";
//	protected static final String GAME_ENABLED  = "ENABLE_GAME_LIST";
//	protected static final String SPECIAL_ENABLED = "SPECIAL_LIST";
//	private static final Cfg_Entry LOADABLE = new Cfg_Entry(LOAD_ENABLED,  false);
//	private static final Cfg_Entry WRITABLE = new Cfg_Entry(WRITE_ENABLED, false);
//	private static final Cfg_Entry GAMEABLE = new Cfg_Entry(GAME_ENABLED,  false);
//
//	private static final
//	Cfg_ValidationData LOCAL_LIST = initLocalList();
//
//	protected static Cfg_ValidationData initLocalList() {
//		Cfg_ValidationData list = new Cfg_ValidationData();	
//		list.setCategoryContains(true);
//		list.setOptionContains(true);
//		list.setIsLabelCaseSensitive(false, false);
//		list.setIsOptionCaseSensitive(false, false);
//		list.setIsCategoryCaseSensitive(false, false);
//		
//		list.addElement("GUI",
//				"Load from file and change GUI" ,
//				LOAD_ENABLED);
//		list.addElement("GAME", 
//				"Load from file and change Game at \"X LOAD\"" ,
//				GAME_ENABLED + " " + LOAD_ENABLED);
//		list.addElement("SAVE", 
//				"Get from GUI and Save to file" ,
//				WRITE_ENABLED);
//		list.addElement("PULL",
//				"Get from Game and Save to file" ,
//				WRITE_ENABLED);
//		list.addElement("INITIAL",
//				"Get initial value of GUI and Save to file" ,
//				WRITE_ENABLED);
//		list.addElement("UPDATE",
//				"If actif Field: Get from GUI and Save to file" ,
//				WRITE_ENABLED);
//		list.addElement("PICK", 
//				"If actif Field: Get from Game and Save to file" ,
//				WRITE_ENABLED);
//		list.addElement("INIT",
//				"If actif Field: Get initial value of GUI and Save to file" ,
//				WRITE_ENABLED);
////		list.addElement("CLEAR",
////				"Remove every occurrence of this setting" ,
////				"SPECIAL_LIST");
//		return list;
//	}
//	protected Cfg_Block_UserActions() {
//		super(LOCAL_LIST);
//	}
//
//	public List<String> getUserLabelList() {
//		return getLabelList();
//	}
//	public List<String> getReadableUserLabelList() {
//		return this.getLabelListIfCategoryContainsFilter(LOADABLE);
//	}
//	public List<String> getWritableUserLabelList() {
//		return this.getLabelListIfCategoryContainsFilter(WRITABLE);
//	}
}
