package rotp.ui.util.cfg;

import java.util.ArrayList;
import java.util.List;

public class Comments {
    static final String COMMENT_KEY        = "#";
	static final String COMMENT_SPACER     = " ";
	static final String COMMENT_KEY_SPACER = COMMENT_KEY + COMMENT_SPACER;
    private List<String> comments;

    // ------------------------------------------------------------------------
    // Constructors
    //
    public Comments(String comment) {
        comments = new ArrayList<String>();
        addLine(comment);
    }
    public Comments(List<String> comments) {
        this.comments = comments;
    }
    public Comments() {
    }
    // ------------------------------------------------------------------------
    // Getters and Setters
    //
    public void set(String comment) {
        comments = new ArrayList<String>();
        addLine(comment);
    }
    public void set(List<String> comments) {
        this.comments = comments;
    }
    // ------------------------------------------------------------------------
    // Other Public Methods
    //
    public boolean isEmpty() {
        return comments.isEmpty();
    }
    public String toString() {
        if (comments == null || comments.isEmpty()) { return COMMENT_KEY_SPACER; }
        List<String> list = new ArrayList<String>();
        for (String comment : comments) {
            list.add(COMMENT_KEY_SPACER + comment);
        }
        return String.join(System.lineSeparator(), list);
    }
    public static boolean isComment(String s) {
        if (s == null || s.isEmpty()) { return false; }
        boolean result = s.trim().startsWith(COMMENT_KEY);
        return result;
    }
    // ------------------------------------------------------------------------
    // Other protected Methods
    //
    private void addLine(String comments) {
        this.comments.add(comments);
    }
}
