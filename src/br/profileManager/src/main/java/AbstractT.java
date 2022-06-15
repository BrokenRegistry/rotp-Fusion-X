
/*
 * Copyright 2015-2020 Ray Fowler
 * 
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *	 https://www.gnu.org/licenses/gpl-3.0.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.profileManager.src.main.java;

import java.util.ArrayList;
import java.util.List;
import static br.profileManager.src.main.java.PMconfig.listSeparator;

/**
 * @author BrokenRegistry
 * parameters are not hidden, used as record.
 * 
 * @param <T> the base Value Class
 */
public abstract class AbstractT <T> {

	private String  userView;
	private T       blankCodeView = null;
	private T       codeView;
	private List<T> codeViewList;
	private List<String> userViewList;
//	private List<AbstractT <T>> valueList;
	
	// ===== Constructors =====
	AbstractT() {
		set(blankCodeView());
	}
	/**
	 * Constructor with direct value initialization
	 * @param value the initial value
	 */
	AbstractT(T value) {
		set(value);
	}
	/**
	 * Constructor for cloning
	 * @param value The value to clone
	 */
	AbstractT(AbstractT<T> value) {
		set(value);
	}
	/**
	 * Constructor for list
	 * @param list the initial list
	 */
	AbstractT(List<T> list) {
		set(list);
	}

	// ===== Abstract Request =====
	/**
	 * convert the CodeView to UserView
	 * @return The translated CodeView
	 */
	abstract T toCodeView(String str);
	/**
	 * @return The minimum of a, b
	 */
	abstract T min(T a, T b);
	/**
	 * @return The maximum of a, b
	 */
	abstract T max(T a, T b);
	/**
	 * @return The value as min <= value <= max
	 */
	abstract T validBound(T value, T min, T max);
	/**
	 * @return The a random value between min and max
	 */
	abstract T random(T min, T max);
	/**
	 * Check for equalities
	 */
	abstract boolean equals(T a, T b);
	/**
	 * alternative to "<b>new</b>" valid in the abstract generic class
	 */
	abstract AbstractT <T> New();
	/**
	 * to get the child Class this
	 */
	abstract AbstractT <T> This();

	// ===== Getters =====
	/**
	 * @return The Blank CodeView
	 */
	protected T blankCodeView() { return blankCodeView; };
	/**
	 * @return The memorized userView
	 */
	protected String userView() { return userView; }
	/**
	 * @return The standard userView from the codeView
	 */
	protected String toUserView() { return toUserView(codeView); }
	/**
	 * @return The value in its specific class
	 */
	public T codeView() { return codeView; }
	/**
	 * @return The default blank / empty codeView
	 */
	protected AbstractT <T> nextRandom(AbstractT <T> min, AbstractT <T> max) {
		set(random(min.codeView(), max.codeView()));
		return this;
	}

	protected Boolean isBlank() {
		boolean test1 = codeView == null 
				|| codeView.toString().isBlank()
				|| equals(codeView, blankCodeView());
		boolean test2 = codeViewList == null 
				|| codeViewList.toString().isBlank();
		return test1 && test2;
	}

	// ===== Setters =====
	protected AbstractT <T> userView(String str) {
		userView = str;
		return This();
	}
	protected AbstractT <T> codeView(T val) {
		codeView = val; 
		return This();
	}
	protected AbstractT <T> blankCodeView(T val) {
		blankCodeView = val;
		return This();
	}
	protected AbstractT <T> set(T initialValue) {
		codeView(initialValue);
		userView(toUserView(codeView));
		return This();
	}

	protected AbstractT <T> set(AbstractT<T> value) {
		userView      = value.userView;
		blankCodeView = value.blankCodeView;
		codeView      = value.codeView;
		if (value.codeViewList != null) {
			codeViewList = new ArrayList<T>(value.codeViewList);
		}
		if (value.userViewList != null) {
			userViewList = new ArrayList<String>(value.userViewList);
		}
		return This();
	}

	protected AbstractT<T> setFromUserView(String view) {
		codeView(toCodeView(view));
		userView(toUserView(codeView));
		return This();
	}

	protected AbstractT <T> reset() {
		return set(New());
	}

	// ===== Other Methods =====
	@Override public String toString() {
		if (userView() == null || userView().isBlank()) {
			if (userViewList() != null) {
				return getUserViewListString();
			}
			return "";
		}
		return userView();
	}

	@Override protected AbstractT<T> clone() {
		return New().set(this);
	}

	protected AbstractT<T> New(T value) {
		return New().set(value);
	}

	protected AbstractT<T> New(List<T> value) {
		return New().set(value);
	}

	protected AbstractT<T> validBound(T min, T max) {
		set(validBound(codeView(), min, max));
		return This();
	}

	protected AbstractT<T> validBound(AbstractT<T> min, AbstractT<T> max) {
		set(validBound(codeView(), min.codeView(), max.codeView()));
		return This();
	}

	protected String toUserView(T value) {
		if (value == null) {
		return "";
		}
	return value.toString();
	}
	protected String toUserViewNeverNull(T value) {
		String userView = toUserView(value);
		if (userView == null) {
		return "";
		}
	return userView;
	}

	protected boolean equals(AbstractT<T> value) {
		return equals(codeView(), value.codeView());
	}
	// ===== List Management  Methods =====
	/**
	 * @return The current codeView List
	 */
	public List<T> codeViewList() { return codeViewList; }
	/**
	 * @return The current userView List
	 */
	protected List<String> userViewList() { return userViewList; }
	/**
	 * set the codeView List only (no change to userView List)
	 * @parameter list The new codeView List
	 */
	protected AbstractT <T> codeViewList(List<T> list) { 
		codeViewList = list; 
		return This();
	}
	/**
	 * set the userView List only (no change to codeView List)
	 * update userView to image the List
	 * @parameter list The new userView List
	 */
	protected AbstractT <T> userViewList(List<String> list) { 
		userViewList = list;
		return This();
	}

	/**
	 * @return The value List from the userView List and codeView List
	 */
	protected List<AbstractT <T>> getValueList() {
		if (userViewList == null || codeViewList == null) {
			return null;
		}
		ArrayList<AbstractT<T>> valueList = new ArrayList<AbstractT <T>>();
		int length = Math.min(userViewList.size(), codeViewList.size());
		for(int i = 0; i < length; i++) {
			valueList.add(New()
					.codeView(codeViewList.get(i))
					.userView(userViewList.get(i)));
		}
		return valueList; 
	}

	/**
	 * @return The codeView List from the userView List
	 */
	protected List<T> getCodeViewList(List<String> userViewList) {
		if (userViewList == null) {
			return null;
		}
		List<T> codeViewList = new ArrayList<T>();
		for (String element : userViewList) {
			codeViewList.add(toCodeView(element));
		}
		return codeViewList;
	}
	/**
	 * @return The codeView List from the userView List
	 */
	protected List<T> getCodeViewList() {
		if (userViewList() == null) {
			return null;
		}
		return getCodeViewList(userViewList());
	}

	/**
	 * @return The userView List from the codeView List
	 */
	protected List<String> getUserViewList(List<T> codeViewList) {
		if (codeViewList == null) {
			return null;
		}
		List<String> userViewList = new ArrayList<String>();
		for (T element : codeViewList) {
			userViewList.add(toUserView(element));
		}
		return userViewList;
	}
	/**
	 * @return The userView List from the codeView List
	 */
	protected List<String> getUserViewList() {
		if (codeViewList() == null) {
			return null;
		}
		return getUserViewList(codeViewList());
	}

	/**
	 * @return a String from the userView List
	 */
	protected String getUserViewListString() {
		if (userViewList() == null) {
			return "";
		}
		List<String> noNull = new ArrayList<String>();
		for (String element : userViewList()) {
			if (element != null) {
				noNull.add(element);
			}
		}
		return String.join(listSeparator(), noNull);
	}
	/**
	 * Set userView List and codeView List from value List
	 * @return This for chaining purpose
	 */	
	protected AbstractT <T> setValue(List<AbstractT <T>> list) {
		reset();
		List<String> userList = new ArrayList<String>();
		List<T> codeList = new ArrayList<T>();
		if (list != null) {
			for (AbstractT<T> element : list) {
				if (element == null) {
					userList.add(null);
					codeList.add(null);
				}
				else {
					userList.add(element.userView);
					codeList.add(element.codeView);
				}
			}
			codeViewList(codeList);
			userViewList(userList);
		}
		return This();
	}
	/**
	 * Set userView List and codeView List from codeView List
	 * @param codeViewList the new codeView List
	 * @return This for chaining purpose
	 */	
	public AbstractT <T> set(List<T> codeViewList) {
		reset();
		codeViewList(codeViewList);
		userViewList(getUserViewList());
		return This();
	}
	/**
	 * Set userView List and codeView List from userView List
	 * @return This for chaining purpose
	 */	
	protected AbstractT <T> setFromUserView(List<String> userViewList) {
		reset();
		userViewList(userViewList);
		codeViewList(getCodeViewList());
		return This();
	}
}
