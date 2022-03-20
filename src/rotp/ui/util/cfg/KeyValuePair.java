package rotp.ui.util.cfg;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class KeyValuePair {
    // ------------------------------------------------------------------------
	// Constant Properties
    //
    static final String BASE_KEY_FORMAT     = "%-20s";
	static final String KEY_VALUE_SEPARATOR = ":";
	static final String VALUE_SPACER        = " ";
	static final String RANDOM_ID           = "RANDOM";
	static final String KEY_VALUE_SEPARATOR_KEY_SPACER = KEY_VALUE_SEPARATOR + VALUE_SPACER;
	static final String KEY_FORMAT = BASE_KEY_FORMAT + KEY_VALUE_SEPARATOR_KEY_SPACER;
    static final String LABEL_OF_SECTION_KEY = "¦ SETTING";
    static final List<String> ENABLE_LOAD    = List.of("LOAD", "BOTH");
	static final List<String> ENABLE_WRITE   = List.of("SAVE", "BOTH");
    static final List<String> YES_LIST       = List.of("YES", "TRUE");
	static final List<String> NO_LIST        = List.of("NO", "FALSE");

    // ------------------------------------------------------------------------
	// Variables Properties
    //
    private String  key   = "";
    String  value = "";
    // ------------------------------------------------------------------------
    // Constructors
    //
    KeyValuePair() {}
    KeyValuePair(String key, String value) {
        if (key == null) {
            key = "Error! key is null";
        }
        if (value == null) {
            value = "";
        }
        setKey(key);
        setValue(value);
    }
    KeyValuePair(String line) {
        if (line == null || line.isBlank()) {return;}
        List<String> list = Arrays.asList(line.split(KEY_VALUE_SEPARATOR));
        setKey(list.get(0).trim());
        if (list.size() > 1) {
            // in the case the value contains KEY_VALUE_SEPARATOR
            setValue(String.join(KEY_VALUE_SEPARATOR, list.subList(1, list.size())).trim());
        }
    }
    // ------------------------------------------------------------------------
    // Getters and Setters
    //
    private void setKey(String key) {
        this.key = key;
    }
    void setValue(String val) {
        value = val;
    }
    Integer getValue(Integer onWrong) {
        return getInteger(value, onWrong);
    }
    boolean getValue(boolean onWrong) {
        return toBoolean(value, onWrong);
    }
    String  getValue() {
        return value;
    }
    String  getKey() {
        return key;
    }
    // ------------------------------------------------------------------------
    // Other Methods
    //
    int[] getRandomParameters(int min, int max) {
        int userMin = min;
        int userMax = max;
        if (isRandom()) {
            String randomParameters = value.toUpperCase().replace(RANDOM_ID, "").trim();
            if (!randomParameters.isBlank()) {
                List<String> list = Arrays.asList(randomParameters.split(" "));
                userMin = getInteger(list.get(0), min);
                if (list.size() > 1) userMax = getInteger(list.get(1), max);
            }
        }
        int result[] = {userMin, userMax};
        return result;
    }
    boolean isValid(Integer min, Integer max) {
        Integer val = getInteger(value, min - 1);
        return (val >= min && val <= max);
    }
    boolean isValid(List<String> list) {
        return list.contains(value.toUpperCase());
    }
    boolean isValid(Set<String> set) {
        return set.contains(value.toUpperCase());
    }
    boolean hasKey() {
        return !key.isBlank();
    }
    boolean isBlank() {
        return (value == null || value.isBlank());
    }
    boolean isSectionKey() {
        return key.equalsIgnoreCase(LABEL_OF_SECTION_KEY);
    }
    boolean isRandom() {
        return isRandomValue(value);
    }
    boolean isWritable() {
        return ENABLE_WRITE.contains(value.toUpperCase());
    }
    boolean isReadable() {
        return ENABLE_LOAD.contains(value.toUpperCase());
    }
    public  String  toString() {
        return String.format(KEY_FORMAT, key) + value;
    }
    // --------------------------------------------------------------------
    // shared static methods
    //
    static boolean toBoolean(String s, boolean onWrong) {
        if (s != null) {
            String S = s.toUpperCase();
            if ( YES_LIST.contains(S) ) return true;
            if ( NO_LIST.contains(S) )  return false;
        }
        return onWrong;
    }
    static Integer getInteger(String str, Integer onWrong){
        if (isNumeric(str)) return Integer.valueOf(str);
        return onWrong;
    }
    // --------------------------------------------------------------------
    // private static methods
    //
    private static boolean isNumeric(String str){
        return str != null && str.matches("[0-9.]+");
    }
    private static boolean isRandomValue(String value) {
        return value.toUpperCase().contains(RANDOM_ID);
    }
}
