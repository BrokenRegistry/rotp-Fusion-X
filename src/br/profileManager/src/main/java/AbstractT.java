
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
	private List<AbstractT <T>> valueList;
	
	// ===== Constructors =====
	AbstractT() {
	//	blankCodeView(New().blankCodeView);
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
	protected List<AbstractT <T>> valueList() { return valueList; }
	protected List<T> codeViewList() { return codeViewList; }
	protected List<String> userViewList() { return userViewList; }

	protected AbstractT <T> nextRandom(AbstractT <T> min, AbstractT <T> max) {
		set(random(min.codeView(), max.codeView()));
		return this;
	}

	protected Boolean isBlank() {
		return codeView == null 
				|| codeView.toString().isBlank()
				|| equals(codeView, blankCodeView());
	}

	protected String getUserViewList() {
		if (valueList() == null) {
			return "";
		}
		List<String> userViewList = new ArrayList<String>();
		for (AbstractT<T> element : valueList()) {
			userViewList.add(element.userView());
		}
		return String.join(listSeparator(), userViewList);
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
	protected AbstractT <T> valueList(List<AbstractT <T>> list) {
		valueList = list; 
		return This();
	}
	protected AbstractT <T> codeViewList(List<T> list) { 
		codeViewList = list; 
		return This();
	}
	protected AbstractT <T> userViewList(List<String> list) { 
		userViewList = list; 
		return This();
	}
	
	protected AbstractT <T> set(List<T> initialValue) {
		codeViewList(initialValue);
//		userView(toUserView(codeView)); TODO List Management
		return This();
	}

	protected AbstractT <T> set(T initialValue) {
		codeView(initialValue);
		userView(toUserView(codeView));
		return This();
	}

	protected AbstractT <T> set(AbstractT<T> value) {
		userView      = value.userView;
//		blankCodeView = value.blankCodeView;
		codeView      = value.codeView;
		if (codeViewList != null) {
			codeViewList = new ArrayList<T>(value.codeViewList);
		}
		if (userViewList != null) {
			userViewList = new ArrayList<String>(value.userViewList);
		}
		if (valueList != null) {
			valueList = new ArrayList<AbstractT <T>>(value.valueList);
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
		if (userView() == null) {
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

	protected boolean equals(AbstractT<T> value) {
		return equals(codeView(), value.codeView());
	}
}
