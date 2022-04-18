package br.config;

public class CfgLine_LocalEnable extends Cfg_LineValidation{

	private static final 
	Cfg_ValidationData LOCAL_LIST = initLocalList();
	private static final String LOAD_ENABLED  = Abstract_CfgFile.LOAD_ENABLED;
	private static final String WRITE_ENABLED = Abstract_CfgFile.WRITE_ENABLED;
	private static final String LOCAL_ENABLED = "LOCAL_LIST";
	private static final Cfg_Entry LOADABLE = new Cfg_Entry(LOAD_ENABLED,  false);
	private static final Cfg_Entry WRITABLE = new Cfg_Entry(WRITE_ENABLED, false);
	private static final Cfg_Entry LOCAL    = new Cfg_Entry(LOCAL_ENABLED, false);

	private static Cfg_ValidationData initLocalList() {
		// this list include userActionsList list
		Cfg_ValidationData list = Abstract_CfgFile.initLocalList();
		list.setCategoryContains(true);
		list.setOptionContains(false);
		list.setIsLabelCaseSensitive(false, false);
		list.setIsOptionCaseSensitive(false, false);
		list.setIsCategoryCaseSensitive(false, false);

		list.addElement("NONE",
				"No actions are allowed in this Setting" ,
				LOCAL_ENABLED);
		list.addElement("ALL",
				"All actions are allowed in this Setting" ,
				LOCAL_ENABLED + " " + LOAD_ENABLED + " " + WRITE_ENABLED);
		list.addElement("TO FILE", 
				"Allows actions that change the file" ,
				LOCAL_ENABLED + " " + WRITE_ENABLED);
		list.addElement("FROM FILE", 
				"Allows actions Get from GUI and Save to file" ,
				LOCAL_ENABLED + " " + LOAD_ENABLED);
		return list;
	}
	// ------------------------------------------------------------------------
	// Constructors
	//
	protected CfgLine_LocalEnable() {
		super(LOCAL_LIST);
	}
	// --------------------------------------------------------------
	// Protected Methods
	//
	protected boolean isLoadEnabled() {
		String category = getCategory();
		if (category != null) {
			return LOADABLE.isContained(category);
		}
		return false;
	}
	protected boolean isWriteEnabled() {
		String category = getCategory();
		if (category != null) {
			return WRITABLE.isContained(category);
		}
		return false;
	}
	protected boolean isLocal() {
		String category = getCategory();
		if (category != null) {
			return LOCAL.isContained(category);
		}
		return false;
	}

}
