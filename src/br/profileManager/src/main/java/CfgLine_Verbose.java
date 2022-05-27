package br.profileManager.src.main.java;
//package br.config;
//
//public class CfgLine_Verbose extends Cfg_LineValidation{
//
//	private static final 
//	Cfg_ValidationData LOCAL_LIST = initLocalList();
//	private static final String OPTIONS_ENABLED  = "OPTIONS";
//	private static final String SETTINGS_ENABLED = "SETTINGS";
//	private static final String COMMENTS_ENABLED = "COMMENTS";
//	
//
//	private static Cfg_ValidationData initLocalList() {
//		Cfg_ValidationData list = new Cfg_ValidationData();
//		list.setCategoryContains(true);
//		list.setOptionContains(false);
//		list.setIsLabelCaseSensitive(false, false);
//		list.setIsOptionCaseSensitive(false, false);
//		list.setIsCategoryCaseSensitive(false, false);
//
//		list.addElement("NO",
//				"No option's comments, No setting's comments, no options" ,
//				"");
//		list.addElement("OPTIONS",
//				"No option's comments, No setting's comments, options" ,
//				OPTIONS_ENABLED);
//		list.addElement("SETTINGS", 
//				"No option's comments, setting's comments, options" ,
//				OPTIONS_ENABLED + " " + SETTINGS_ENABLED);
//		list.addElement("FULL", 
//				"Option's comments, Setting's Comments, options",
//				OPTIONS_ENABLED + " " + SETTINGS_ENABLED + " " + COMMENTS_ENABLED);
//		return list;
//	}
//	// ------------------------------------------------------------------------
//	// Constructors
//	//
//	protected CfgLine_Verbose() {
//		super(LOCAL_LIST);
//	}
//	// --------------------------------------------------------------
//	// Protected Methods
//	//
//	protected boolean isOptionEnabled() {
//		String category = getCategory();
//		if (category != null) {
//			return category.toUpperCase().contains(OPTIONS_ENABLED);
//		}
//		return false;
//	}
//	protected boolean isSettingEnabled() {
//		String category = getCategory();
//		if (category != null) {
//			return category.toUpperCase().contains(SETTINGS_ENABLED);
//		}
//		return false;
//	}
//	protected boolean isCommentEnabled() {
//		String category = getCategory();
//		if (category != null) {
//			return category.toUpperCase().contains(COMMENTS_ENABLED);
//		}
//		return false;
//	}
//}
