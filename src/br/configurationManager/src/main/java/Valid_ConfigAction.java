package br.configurationManager.src.main.java;

/**
 * For the validation of the configurations Action
 */
class Valid_ConfigAction extends Valid_String {
		protected static final String LOAD_ENABLED  = "ENABLE_LOAD_LIST";
		protected static final String WRITE_ENABLED = "ENABLE_WRITE_LIST";
		protected static final String GAME_ENABLED  = "ENABLE_GAME_LIST";
		protected static final String SPECIAL_ENABLED = "SPECIAL_LIST";

		Valid_ConfigAction() {
			setValidationCriteria(new ValidationCriteria()
					.isNullAllowed(false)
					.isBlankAllowed(true)
					.isRandomAllowed(false)
					.userViewEquals(false)
					.categoryEquals(false)
					.userViewIsCaseSensitive(false)
					.codeViewIsCaseSensitive(false)
					.categoryIsCaseSensitive(false)
					.printFormat(PrintFormat.CAPITALIZE));
		
			addElement("GUI",
					"Load from file and change GUI" ,
					LOAD_ENABLED);
			addElement("GAME", 
					"Load from file and change Game at \"X LOAD\"" ,
					GAME_ENABLED + " " + LOAD_ENABLED);
			addElement("SAVE", 
					"Get from GUI and Save to file" ,
					WRITE_ENABLED);
			addElement("PULL",
					"Get from Game and Save to file" ,
					WRITE_ENABLED);
			addElement("INITIAL",
					"Get initial value of GUI and Save to file" ,
					WRITE_ENABLED);
			addElement("UPDATE",
					"If actif Field: Get from GUI and Save to file" ,
					WRITE_ENABLED);
			addElement("PICK", 
					"If actif Field: Get from Game and Save to file" ,
					WRITE_ENABLED);
			addElement("INIT",
					"If actif Field: Get initial value of GUI and Save to file" ,
					WRITE_ENABLED);
//			list.addElement("CLEAR",
//					"Remove every occurrence of this setting" ,
//					"SPECIAL_LIST");
		}
}
