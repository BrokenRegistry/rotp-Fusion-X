
/*
 * Licensed under the GNU General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/gpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.config;

import static br.config.Cfg_Util.*;

/**
 * String, Boolean and Numeric management and conversion.
 * No exceptions are thrown, if a problem happen: null will be returned.
 * not the type then null.
 */	
public class Cfg_Value extends Cfg_Entry{
	/**
	 * The most probable type of this value 
	 * Could be used by toString and to Print methods
	 * Could be useful to optimize random generation 
	 */
	public static enum DataType {/**
	 * Probably only a simple string
	 */
	STRING, /**
	 * Probably a binary type YES/NO true/false Pass/Fail
	 */
	BOOLEAN, /**
	 * Probably a whole number (managed as Long)
	 */
	WHOLE, /**
	 * Probably a Floating point number (managed as Double)
	 */
	FLOATING, /**
	 * ... Do your best!
	 */
	UNKNOWN};
	
	// from Cfg_Entry: 	
	// private String entry = "";
	// private Boolean caseSensitive = null;
	// extended with:
	private Long    whole = null;
	private Double  floating = null;
	private Boolean bool = null;
	private DataType dataType = DataType.UNKNOWN;
	private DataType origin = DataType.UNKNOWN;

	// ==================================================
    // Constructors
    //
	/**
	 * Create new empty {@code CfgValue}
	 */
	public Cfg_Value() {}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType}
	 * and set a new {@code String} entry
	 * @param  entry  original {@code String} value
	 * @param  dataType specified {@code DataType}
	 */
	public Cfg_Value(String entry, DataType dataType) {
		init(entry);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType}
	 * and set a new {@code double} value
	 * @param  value  original {@code double} value
	 * @param  dataType specified {@code DataType}
	 */
	public Cfg_Value(Double value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType} 
	 * and set a new {@code float} value
	 * @param  value  original {@code float} value
	 * @param  dataType specified {@code DataType}
	 */
	public Cfg_Value(Float value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue}  with specified {@code DataType} 
	 * and set a new {@code long} value
	 * @param  value  original {@code long} value
	 * @param  dataType specified {@code DataType}
	 */
	public Cfg_Value(Long value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType} 
	 * and set a new {@code int} value
	 * @param  value  original {@code int} value
	 * @param  dataType specified {@code DataType}
	 */
	public Cfg_Value(Integer value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType} 
	 * and set a new {@code short} value
	 * @param  value  original {@code short} value
	 * @param  dataType specified {@code DataType}
	 */
	public Cfg_Value(Short value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType}
	 * and set a new {@code byte} value
	 * @param  value  original {@code byte} value
	 * @param  dataType specified {@code DataType}
	 */
	public Cfg_Value(Byte value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType} 
	 * and set a new {@code boolean} value
	 * @param  value  original {@code boolean} value
	 * @param  dataType specified {@code DataType}
	 */
	public Cfg_Value(Boolean value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType}
	 * and set a new {@code String} entry
	 * @param  entry  original {@code String} value
	 */
	public Cfg_Value(String entry) {
		init(entry);
	}
	/**
	 * Create new {@code CfgValue} 
	 * and set a new {@code double} value
	 * @param  value  original {@code double} value
	 */
	public Cfg_Value(Double value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue}  
	 * and set a new {@code float} value
	 * @param  value  original {@code float} value
	 */
	public Cfg_Value(Float value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue} 
	 * and set a new {@code long} value
	 * @param  value  original {@code long} value
	 */
	public Cfg_Value(Long value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue} 
	 * and set a new {@code int} value
	 * @param  value  original {@code int} value
	 */
	public Cfg_Value(Integer value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue} 
	 * and set a new {@code short} value
	 * @param  value  original {@code short} value
	 */
	public Cfg_Value(Short value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue} 
	 * and set a new {@code byte} value
	 * @param  value  original {@code byte} value
	 */
	public Cfg_Value(Byte value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue}
	 * and set a new {@code boolean} value
	 * @param  value  original {@code boolean} value
	 */
	public Cfg_Value(Boolean value) {
		init(value);
	}
    // ==================================================
    // Testers
    //
	/**
	 * Check if the value may be returned as numeric 
	 * {@code Whole number} or {@code Floating Point} 
	 * @return  true if {@code double} compatible
	 */
	@Override
	public Boolean testForNumeric() { 
		return isDouble(); 
	}
	/**
	 * Check if the value may be returned as {@code Whole Number}
	 * @return  true if {@code long} compatible
	 */
	public boolean isWhole() { 
		return isLong(); 
	}
	/**
	 * Check if the value may be returned as {@code double}
	 * @return  true if {@code double} compatible
	 */
	public boolean isDouble() {
		return toDouble() != null;
	}
	/**
	 * Check if the value may be returned as {@code float}
	 * @return  true if {@code float} compatible
	 */
	public boolean isFloat() {
		return toFloat() != null;
	}
	/**
	 * Check if the value may be returned as {@code long}
	 * @return  true if {@code long} compatible
	 */
	public boolean isLong() { 
		return toLong() != null; 
	}
	/**
	 * Check if the value may be returned as {@code int}
	 * @return  true if {@code int} compatible
	 */
	public boolean isInteger() { 
		return toInteger() != null; 
	}
	/**
	 * Check if the value may be returned as {@code short}
	 * @return  true if {@code short} compatible
	 */
	public boolean isShort() {
		return toShort() != null; 
	}
	/**
	 * Check if the value may be returned as {@code byte}
	 * @return  true if {@code byte} compatible
	 */
	public boolean isByte() { 
		return toByte() != null;
	}
	/**
	 * Check if the value may be returned as true if {@code long} compatible
	 * @return  true if {@code boolean} compatible
	 */
	public boolean isBoolean() { 
		return toBoolean() != null;
	}
	/**
	 * Check if any value may be returned
	 * @return  true if no values have been set,
	 *  only empty {@code String} may be returned
	 */	
	@Override
	public boolean isEmpty() {
		return(super.isEmpty() 
				&& whole    == null 
				&& floating == null 
				&& bool     == null);
	}
    // ==================================================
    // Getters
    //
	/**
	 * Constructor for cloning purpose
	 * @param e this.Cfg_Entry
	 * @param w this.whole
	 * @param f this.floating
	 * @param b this.bool
	 * @param t this.dataType
	 * @param o this.origin
	 */
	private Cfg_Value(
			Cfg_Entry e, Long w, Double f, Boolean b
			, DataType t, DataType o) {
		set(e);
		whole = w;
		floating = f;
		bool = b;
		dataType = t;
		origin = o;
	}
	/**
	 * Ask for a cloned {@code Cfg_Value}
	 * @return the clone {@code Cfg_Value}
	 */
	@Override
	public Cfg_Value clone() { 
		return new Cfg_Value(
				getCfg_Value(), whole, floating, bool, dataType, origin);
	}
	/**
	 * Ask for a copy of superclass
	 * @return value as  {@code Cfg_Value} (used by subclasses)
	 */
	public Cfg_Value getCfg_Value() { 
		return this;
	}
	/**
	 * get the entry as String
	 * @return  {@code String}
	 */
	@Override
	public String get() { 
		return toString(""); // never null
	}
	/**
	 * Convert the value to {@code String} ready to be printed
	 * @return  {@code String}
	 */
	@Override
	public String toPrint() { 
		return toString(""); // never null
	}
	/**
	 * Convert the value to {@code String}
	 * @return  {@code String}
	 */
	@Override
	public String toString() { 
		return toString(""); // never null
	}
	/**
	 * Convert the value to {@code String}
	 * @param onEmpty value to return if empty
	 * @return  {@code String} or onEmpty if none
	 */
	public String toString(String onEmpty) { 
		if (isEmpty()) {
			return onEmpty;
		}
		// Go for User preference if possible
		switch(dataType) {
		case STRING:
			if (!super.isEmpty()) {
				return super.get();
			}
			break;
		case BOOLEAN:
			if (isBoolean()) {
				return toYesNoString(bool);
			}
			break;
		case FLOATING:
				if (isDouble()) {
					return floating.toString();
				}
				break;
		case WHOLE:
			if (isLong()) {
				return whole.toString();
			}
			break;
		default:
			break;
		}
		// No valid DataType... Go for Origin
		switch(origin) {
		case STRING:
			if (!super.isEmpty()) {
				return super.get();
			}
			break;
		case BOOLEAN:
			if (isBoolean()) {
				return toYesNoString(bool);
			}
			break;
		case FLOATING:
				if (isDouble()) {
					return floating.toString();
				}
				break;
		case WHOLE:
			if (isLong()) {
				return whole.toString();
			}
			break;
		default:
			break;
		}
		// This should not happen !!!
		// But let the game continue...
		return onEmpty; 
	}
	/**
	 * Convert the value to {@code double}
	 * @return  value or null if none or not compatible
	 */
	public Double toDouble() { 
		return toDouble((Double)null); 
	}
	/**
	 * Convert the value to {@code double}
	 * @param   onEmpty  value to return if empty
	 * @return  value or onEmpty if none or not compatible
	 */
	public Double toDouble(Double onEmpty) {
		if (isEmpty()) {
			return onEmpty;
		}
		if (floating != null) {
			return floating; 
		}
		if (whole != null) {
			floating = Double.valueOf(whole);
			return floating; 
		}
		floating = toFiniteDouble(super.get());
		if (floating == null) {
			return onEmpty;
		}
		return  floating;
	}
	/**
	 * Convert the value to {@code float}
	 * @return  value or null if none or not compatible
	 */
	public Float toFloat() {
		return toFloat((Float)null); 
	}
	/**
	 * Convert the value to {@code float}
	 * @param   onEmpty  value to return if empty
	 * @return  value or onEmpty if none or not compatible
	 */
	public Float toFloat(Float onEmpty) {
		Double dbl = toDouble((Double)null);
		if(dbl != null 
				&& dbl <= Float.MAX_VALUE 
				&& dbl >= -Float.MAX_VALUE ) {
			return dbl.floatValue();
		}
		return onEmpty;
	}
	/**
	 * Convert the value to {@code Long}
	 * @return  value or null if none or not compatible
	 */
	public Long toLong() { 
		return toLong((Long)null); 
		}
	/**
	 * Convert the value to {@code Long}
	 * @param   onEmpty  value to return if empty
	 * @return  value or onEmpty if none or not compatible
	 */
	public Long toLong(Long onEmpty) { 
		if (isEmpty()) {
			return onEmpty;
		}
		if (whole != null) {
			return whole; 
		}
		if (floating != null
				&& floating <= Long.MAX_VALUE 
				&& floating >= Long.MIN_VALUE) {
			whole = Math.round(floating);
			return whole; 
		}
		whole = Cfg_Util.toLong(super.get());
		if (whole == null) {
			return onEmpty;
		}
		return  whole;
	}
	/**
	 * Convert the value to {@code int}
	 * @return  value or null if none or not compatible
	 */
	public Integer toInteger() { 
		return toInteger((Integer)null);
	}
	/**
	 * Convert the value to {@code int}
	 * @param   onEmpty  value to return if empty
	 * @return  value or onEmpty if none or not compatible
	 */
	public Integer toInteger(Integer onEmpty) { 
		if (isLong() 
				&& whole <= Integer.MAX_VALUE
				&& whole >= Integer.MIN_VALUE) {
			return whole.intValue();
		}
		return onEmpty;
	}
	/**
	 * Convert the value to {@code short}
	 * @return  value or null if none or not compatible
	 */
	public Short toShort() {
		return toShort((Short)null);
	}
	/**
	 * Convert the value to {@code short}
	 * @param   onEmpty  value to return if empty
	 * @return  value or onEmpty if none or not compatible
	 */
	public Short toShort(Short onEmpty) {
		if (isLong() 
				&& whole <= Short.MAX_VALUE
				&& whole >= Short.MIN_VALUE) {
			return whole.shortValue();
		}
		return onEmpty;
	}
	/**
	 * Convert the value to {@code byte}
	 * @return  value or null if none or not compatible
	 */
	public Byte toByte() { 
		return toByte((Byte)null);
	}
	/**
	 * Convert the value to {@code byte}
	 * @param   onEmpty  value to return if empty
	 * @return  value or onEmpty if none or not compatible
	 */
	public Byte toByte(Byte onEmpty) { 
		if (isLong() 
				&& whole <= Byte.MAX_VALUE
				&& whole >= Byte.MIN_VALUE) {
			return whole.byteValue();
		}
		return onEmpty;
	}
	/**
	 * Convert the value to {@code boolean}
	 * @return  value or null if none or not compatible
	 */
	public Boolean toBoolean() {
		return toBoolean((Boolean)null);
	}
	/**
	 * Convert the value to {@code boolean}
	 * @param onEmpty default value to return
	 * @return  value or onEmpty if none or not compatible
	 */
	public Boolean toBoolean(Boolean onEmpty) {
		if (isEmpty()) {
			return onEmpty;
		}
		if (bool != null) {
			return bool; 
		}
		bool = Cfg_Util.toBoolean(super.get());
		if (bool == null) {
			return onEmpty;
		}
		return bool;
	}
	/**
	 * get the {@code DataType} value
	 * @return dataType
	 */
	public DataType getDataType() {
		return dataType;
	}
	// ==================================================
    // Tools
    //
	/**
	 * Check boundaries to avoid error throwing 
	 * and generate random number {@code DataType} if one
	 * @param min inclusive minimum bound
	 * @param max exclusive maximum bound for FLOATING
	 * <br> but inclusive maximum bound for Whole
	 * @return random {@code CfgValue} value in the specified range
	 * <br> if min = max : return min
	 */
	public static Cfg_Value nextRandom(Cfg_Value min, Cfg_Value max) {
		if (min != null && max != null) {
			if (min.getDataType() == DataType.WHOLE
					|| max.getDataType() == DataType.WHOLE) {
				return new Cfg_Value(nextRandomLong( min.toLong()
													, max.toLong()+1L))
									.setDataType(DataType.WHOLE);
			}
			return new Cfg_Value(nextRandomDouble( min.toDouble()
												, max.toDouble()))
								.setDataType(DataType.FLOATING);
		}
		return new Cfg_Value();
	}
	/**
	 * Compare the two numerical value and keep the smallest one
	 * Favor the {@code DataType} if one
	 * @param  min the value to compare
	 * @return the smallest value or this if equal
	 */
	public Cfg_Value validateMin(Cfg_Value min) {
		return set(min(this, min));
	}
	/**
	 * Compare the two numerical value and keep the biggest one
	 * Favor the {@code DataType} if one
	 * @param  max the value to compare
	 * @return the biggest value or this if equal
	 */
	public Cfg_Value validateMax(Cfg_Value max) {
		return set(max(this, max));
	}
	/**
	 * Compare this value with the two other and keep it inside the range
	 * Favor the {@code DataType} if one
	 * @param  min the value to compare
	 * @param  max the value to compare
	 * @return the biggest value or this if equal
	 */
	public  Cfg_Value validateMinMax(Cfg_Value min, Cfg_Value max) {
		return validateMin(max).validateMax(min);
	}
	/**
	 * Compare the two numerical value and return the biggest one
	 * Favor the {@code DataType} if one
	 * Keep Value1 DataType
	 * @param  value1 the first value to compare, the favorite in equality
	 * @param  value2 the second value to compare
	 * @return the biggest value or value 1 if equal
	 */	
	public static Cfg_Value max(Cfg_Value value1, Cfg_Value value2) {
		// If one is null, return the other, return null if both are null
		if (value1 == null) {
			return value2.clone();
		}
		if (value2 == null) {
			return value1.clone();
		}
		// favor WHOLE dataType if selected so
		if (value1.getDataType() == DataType.WHOLE
				&& value1.isWhole()
				&& value2.isWhole()) {
			if (value1.toLong() >= value2.toLong()) {
				return value1.clone();
			}
			return value2.clone().setDataType(value1.getDataType());
		}
		// favor FLOATING otherwise
		if (value1.testForNumeric() && value2.testForNumeric()) {
			if (value1.toDouble() >= value2.toDouble()) {
				return value1.clone();
			}
			return value2.clone().setDataType(value1.getDataType());
		}
		if (value2.testForNumeric()) {
			return value2.clone().setDataType(value1.getDataType());
		}
		// Not Numeric... Return the first one!
		return value1.clone();
	}
	/**
	 * Compare the two numerical value and return the smallest one
	 * Favor the {@code DataType} if one
	 * Keep Value1 DataType
	 * @param  value1 the first value to compare, the favorite in equality
	 * @param  value2 the second value to compare
	 * @return the smallest value or value 1 if equal
	 */
	public static Cfg_Value min(Cfg_Value value1, Cfg_Value value2) {
		// If one is null, return the other, return null if both are null
		if (value1 == null) {
			return value2.clone();
		}
		if (value2 == null) {
			return value1.clone();
		}
		// favor WHOLE dataType if selected so
		if (value1.getDataType() == DataType.WHOLE
				&& value1.isWhole()
				&& value2.isWhole()) {
			if (value1.toLong() <= value2.toLong()) {
				return value1.clone();
			}
			return value2.clone().setDataType(value1.getDataType());
		}
		// favor FLOATING otherwise
		if (value1.testForNumeric() && value2.testForNumeric()) {
			if(value1.toDouble() <= value2.toDouble()) {
				return value1.clone();
			}
			return value2.clone().setDataType(value1.getDataType());
		}
		if (value2.testForNumeric()) {
			return value2.clone().setDataType(value1.getDataType());
		}
		// Not Numeric... Return the first one!
		return value1.clone();
	}
	// ==================================================
    // Setters
    //
	private void setOrigin(DataType origin){
		this.origin = origin;
	}
	/**
	 * Set new {@code DataType} value
	 * @param  dataType  the new {@code DataType} value
	 * @return this for chaining purpose
	 */
 	public Cfg_Value setDataType(DataType dataType) {
 		if (dataType == null) {
 			dataType = DataType.UNKNOWN;
 		}
		this.dataType = dataType;
		return this;
	}
	/**
	 * Set new {@code String} value for {@code CfgValue}
	 * @param  entry  the new {@code String} value
	 * @param  caseSensitive  the new case sensitivity value value
	 * @return this for chaining purpose
	 */
	@Override
	public Cfg_Value set(String entry, Boolean caseSensitive) {
		reset();
		init(entry, caseSensitive);
		return this;
	}
	/**
	 * Set new {@code String} value for {@code CfgValue}
	 * @param  entry  the new {@code String} value
	 * @return this for chaining purpose	 */
	@Override
	public Cfg_Value set(String entry) {
		reset();
		init(entry);
		return this;
	}
	/**
	 * Set new {@code Cfg_Entry} value for {@code CfgValue}
	 * @param  entry  the new {@code Cfg_Entry} value
	 * @param  caseSensitive  the new case sensitivity value value
	 * @return this for chaining purpose
	 */
	@Override
	public Cfg_Value set(Cfg_Entry entry, Boolean caseSensitive) {
		reset();
		init(entry, caseSensitive);
		return this;
	}
	/**
	 * Set new {@code Cfg_Entry} value for {@code CfgValue}
	 * @param  entry  the new {@code Cfg_Entry} value
	 * @return this for chaining purpose
	 */
	@Override
	public Cfg_Value set(Cfg_Entry entry) {
		reset();
		init(entry);
		return this;
	}
	/**
	 * Set new {@code Double} value for {@code CfgValue}
	 * @param  value  the new {@code Double} value
	 * @return this for chaining purpose
	 */
	public Cfg_Value set(Double value) {
		reset();
		init(value);
		return this;
	}
	/**
	 * Set new {@code Float} value for {@code CfgValue}
	 * @param  value  the new {@code Float} value
	 * @return this for chaining purpose
	 */
	public Cfg_Value set(Float value) {
		reset();
		init(value);
		return this;
	}
	/**
	 * Set new {@code Long} value for {@code CfgValue}
	 * @param  value  the new {@code Long} value
	 * @return this for chaining purpose
	 */
	public Cfg_Value set(Long value) {
		reset();
		init(value);
		return this;
	}
	/**
	 * Set new {@code Integer} value for {@code CfgValue}
	 * @param  value  the new {@code Integer} value
	 * @return this for chaining purpose
	 */
	public Cfg_Value set(Integer value) {
		reset();
		init(value);
		return this;
	}
	/**
	 * Set new {@code Short} value for {@code CfgValue}
	 * @param  value  the new {@code Short} value
	 * @return this for chaining purpose
	 */
	public Cfg_Value set(Short value) {
		reset();
		init(value);
		return this;
	}
	/**
	 * Set new {@code Byte} value for {@code CfgValue}
	 * @param  value  the new {@code Byte} value
	 * @return this for chaining purpose
	 */
	public Cfg_Value set(Byte value) {
		reset();
		init(value);
		return this;
	}
	/**
	 * Set new {@code Boolean} value for {@code CfgValue}
	 * @param  value  the new {@code Boolean} value
	 * @return this for chaining purpose
	 */
	public Cfg_Value set(Boolean value) {
		reset();
		init(value);
		return this;
	}
	// ==================================================
    // Initialization Methods
    //
	private void reset() {
		floating = null;
		whole    = null;
		bool     = null;
		super.set("");
	}
	private void init(String source) {
		super.set(source);
		setOrigin(DataType.STRING);
	}
	private void init(String source, Boolean caseSensitive) {
		super.set(source, caseSensitive);
		setOrigin(DataType.STRING);
	}
	private void init(Cfg_Entry source) {
		super.set(source);
		setOrigin(DataType.STRING);
	}
	private void init(Cfg_Entry source, Boolean caseSensitive) {
		super.set(source, caseSensitive);
		setOrigin(DataType.STRING);
	}
	private void init(Double value) {
		floating = toFiniteDouble(value);
		setOrigin(DataType.FLOATING);
	}
	private void init(Float value) {
		init(Double.valueOf(value));
		setOrigin(DataType.FLOATING);
	}
	private void init(Long value) {
		whole = value;
		setOrigin(DataType.WHOLE);
	}
	private void init(Integer value) {
		init(Long.valueOf(value));
		setOrigin(DataType.WHOLE);
	}
	private void init(Short value) {
		init(Long.valueOf(value));
		setOrigin(DataType.WHOLE);
	}
	private void init(Byte value) {
		init(Long.valueOf(value));
		setOrigin(DataType.WHOLE);
	}
	private void init(Boolean value) {
		bool = value;
		setOrigin(DataType.BOOLEAN);
	}
}