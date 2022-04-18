
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

import java.util.concurrent.ThreadLocalRandom;

/**
 * Numeric Conversion without the need to throws and catch errors
 * not the type then null.
 */	
public class Cfg_Value extends Cfg_Entry{
	public static enum DataType {STRING, BOOLEAN, WHOLE, FLOATING, UNKNOWN};
	private Long    whole;
	private Double  floating;
	private Boolean bool;
	private DataType dataType = DataType.UNKNOWN;

	// ==================================================
    // Constructors
    //
	public Cfg_Value() {}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType}
	 * and set a new {@code String} entry
	 * @param  entry  original {@code String} value
	 * @param  datatype specified {@code DataType}
	 */
	public Cfg_Value(String entry, DataType dataType) {
		init(entry);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType}
	 * and set a new {@code double} value
	 * @param  value  original {@code double} value
	 * @param  datatype specified {@code DataType}
	 */
	public Cfg_Value(double value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType} 
	 * and set a new {@code float} value
	 * @param  value  original {@code float} value
	 * @param  datatype specified {@code DataType}
	 */
	public Cfg_Value(float value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue}  with specified {@code DataType} 
	 * and set a new {@code long} value
	 * @param  value  original {@code long} value
	 * @param  datatype specified {@code DataType}
	 */
	public Cfg_Value(long value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType} 
	 * and set a new {@code int} value
	 * @param  value  original {@code int} value
	 * @param  datatype specified {@code DataType}
	 */
	public Cfg_Value(int value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType} 
	 * and set a new {@code short} value
	 * @param  value  original {@code short} value
	 * @param  datatype specified {@code DataType}
	 */
	public Cfg_Value(short value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType}
	 * and set a new {@code byte} value
	 * @param  value  original {@code byte} value
	 * @param  datatype specified {@code DataType}
	 */
	public Cfg_Value(byte value, DataType dataType) {
		init(value);
		setDataType(dataType);
	}
	/**
	 * Create new {@code CfgValue} with specified {@code DataType} 
	 * and set a new {@code boolean} value
	 * @param  value  original {@code boolean} value
	 * @param  datatype specified {@code DataType}
	 */
	public Cfg_Value(boolean value, DataType dataType) {
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
	public Cfg_Value(double value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue}  
	 * and set a new {@code float} value
	 * @param  value  original {@code float} value
	 */
	public Cfg_Value(float value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue} 
	 * and set a new {@code long} value
	 * @param  value  original {@code long} value
	 */
	public Cfg_Value(long value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue} 
	 * and set a new {@code int} value
	 * @param  value  original {@code int} value
	 */
	public Cfg_Value(int value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue} 
	 * and set a new {@code short} value
	 * @param  value  original {@code short} value
	 */
	public Cfg_Value(short value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue} 
	 * and set a new {@code byte} value
	 * @param  value  original {@code byte} value
	 */
	public Cfg_Value(byte value) {
		init(value);
	}
	/**
	 * Create new {@code CfgValue}
	 * and set a new {@code boolean} value
	 * @param  value  original {@code boolean} value
	 */
	public Cfg_Value(boolean value) {
		init(value);
	}
    // ==================================================
    // Testers
    //
	/**
	 * Check if the value returned as numeric 
	 * {@code Whole number} or {@code Floating Point} 
	 * @return  boolean
	 */
	public boolean isNumeric() { 
		return isDouble(); 
	}
	/**
	 * Check if the value may be returned as {@code Whole Number}
	 * @return  boolean
	 */
	public boolean isWhole() { 
		return isLong(); 
	}
	/**
	 * Check if the value may be returned as {@code double}
	 * @return  boolean
	 */
	public boolean isDouble() {
		return toDouble() != null;
	}
	/**
	 * Check if the value may be returned as {@code float}
	 * @return  boolean
	 */
	public boolean isFloat() {
		return toFloat() != null;
	}
	/**
	 * Check if the value may be returned as {@code long}
	 * @return  boolean
	 */
	public boolean isLong() { 
		return toLong() != null; 
	}
	/**
	 * Check if the value may be returned as {@code int}
	 * @return  boolean
	 */
	public boolean isInteger() { 
		return toInteger() != null; 
	}
	/**
	 * Check if the value may be returned as {@code short}
	 * @return  boolean
	 */
	public boolean isShort() {
		return toShort() != null; 
	}
	/**
	 * Check if the value may be returned as {@code byte}
	 * @return  boolean
	 */
	public boolean isByte() { 
		return toByte() != null;
	}
	/**
	 * Check if the value may be returned as {@code boolean}
	 * @return  boolean
	 */
	public boolean isBoolean() { 
		return toBoolean() != null;
	}
	/**
	 * Check if any value may be returned
	 * @return  boolean
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
	 * return value as Cfg_Value
	 */
	protected Cfg_Value getCfg_Value() { 
		return this;
	}
	/**
	 * Convert the value to {@code double}
	 * @return  value or null if none or not compatible
	 */
	public Double toDouble()  { 
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
		// if (bool != null) {
		// 	return bool ? 1.0 : 0.0; 
		// }
		floating = toUsableDouble(super.get());
		return floating == null ? onEmpty : floating;
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
		return (dbl != null 
				&& dbl <= Float.MAX_VALUE 
				&& dbl >= -Float.MAX_VALUE)
				? dbl.floatValue() : onEmpty;
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
		// if (bool != null) {
		// 	return bool ? 1L : 0; 
		// }
		whole = toLong(super.get());
		return whole == null ? onEmpty : whole;
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
	 * @return  value or null if none or not compatible
	 */
	public Boolean toBoolean(Boolean onEmpty) {
		if (isEmpty()) {
			return onEmpty;
		}
		if (bool != null) {
			return bool; 
		}
		bool = Cfg_Util.toBoolean(super.get());
		return bool == null ? onEmpty : bool;
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
		return null;
	}
	/**
	 * Compare the two numerical value and return the smallest one
	 * Favor the {@code DataType} if one
	 * @param  min the value to compare
	 * @return the smallest value or this if equal
	 */
	public Cfg_Value validateMin(Cfg_Value min) {
		return min(this, min);
	}
	/**
	 * Compare the two numerical value and return the biggest one
	 * Favor the {@code DataType} if one
	 * @param  min the value to compare
	 * @return the biggest value or this if equal
	 */
	public Cfg_Value validateMax(Cfg_Value max) {
		return max(this, max);
	}
	/**
	 * Compare this value with the two other and keep it inside the range
	 * Favor the {@code DataType} if one
	 * @param  min the value to compare
	 * @return the biggest value or this if equal
	 */
	public  Cfg_Value validateMinMax(Cfg_Value min, Cfg_Value max) {
		return validateMin(min).validateMax(max);
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
			return value2;
		}
		if (value2 == null) {
			return value1;
		}
		// favor WHOLE dataType if selected so
		if ((value1.getDataType() == DataType.WHOLE
				|| value1.getDataType() == DataType.WHOLE
				)
				&& value1.isWhole()
				&& value2.isWhole()) {
			return value1.toLong() >= value2.toLong()
					? value1 : value2.setDataType(value1.getDataType());
		}
		// favor FLOATING otherwise
		if (value1.isNumeric() && value2.isNumeric()) {
			return value1.toDouble() >= value2.toDouble()
					? value1 : value2.setDataType(value1.getDataType());
		}
		// Not Numeric... Return the first one!
		return value1;
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
			return value2;
		}
		if (value2 == null) {
			return value1;
		}
		// favor WHOLE dataType if selected so
		if ((value1.getDataType() == DataType.WHOLE
				|| value1.getDataType() == DataType.WHOLE
				)
				&& value1.isWhole()
				&& value2.isWhole()) {
			return value1.toLong() <= value2.toLong()
					? value1 : value2.setDataType(value1.getDataType());
		}
		// favor FLOATING otherwise
		if (value1.isNumeric() && value2.isNumeric()) {
			return value1.toDouble() <= value2.toDouble()
					? value1 : value2.setDataType(value1.getDataType());
		}
		// Not Numeric... Return the first one!
		return value1;
	}
	// ==================================================
    // Setters
    //
	/**
	 * Set new {@code DataType} value
	 * @param  dataType  the new {@code DataType} value
	 * @return this for chaining purpose
	 */
	public Cfg_Value setDataType(DataType dataType) {
		this.dataType = dataType;
		return this;
	}
	/**
	 * Set new {@code String} value for {@code CfgValue}
	 * @param  entry  the new {@code String} value
	 * @param  caseSensitive  the new case sensitivity value value
	 */
	@Override
	public void set(String entry, boolean caseSensitive) {
		reset();
		init(entry, caseSensitive);
	}
	/**
	 * Set new {@code String} value for {@code CfgValue}
	 * @param  entry  the new {@code String} value
	 */
	@Override
	public void set(String entry) {
		reset();
		init(entry);
	}
	/**
	 * Set new {@code Cfg_Entry} value for {@code CfgValue}
	 * @param  entry  the new {@code Cfg_Entry} value
	 * @param  caseSensitive  the new case sensitivity value value
	 */
	@Override
	public void set(Cfg_Entry entry, boolean caseSensitive) {
		reset();
		init(entry, caseSensitive);
	}
	/**
	 * Set new {@code Cfg_Entry} value for {@code CfgValue}
	 * @param  entry  the new {@code Cfg_Entry} value
	 */
	@Override
	public void set(Cfg_Entry entry) {
		reset();
		init(entry);
	}
	/**
	 * Set new {@code Double} value for {@code CfgValue}
	 * @param  value  the new {@code Double} value
	 */
	public void set(Double value) {
		reset();
		init(value);
	}
	/**
	 * Set new {@code Float} value for {@code CfgValue}
	 * @param  value  the new {@code Float} value
	 */
	public void set(Float value) {
		reset();
		init(value);
	}
	/**
	 * Set new {@code Long} value for {@code CfgValue}
	 * @param  value  the new {@code Long} value
	 */
	public void set(Long value) {
		reset();
		init(value);
	}
	/**
	 * Set new {@code Integer} value for {@code CfgValue}
	 * @param  value  the new {@code Integer} value
	 */
	public void set(Integer value) {
		reset();
		init(value);
	}
	/**
	 * Set new {@code Short} value for {@code CfgValue}
	 * @param  value  the new {@code Short} value
	 */
	public void set(Short value) {
		reset();
		init(value);
	}
	/**
	 * Set new {@code Byte} value for {@code CfgValue}
	 * @param  value  the new {@code Byte} value
	 */
	public void set(Byte value) {
		reset();
		init(value);
	}
	/**
	 * Set new {@code Boolean} value for {@code CfgValue}
	 * @param  value  the new {@code Boolean} value
	 */
	public void set(Boolean value) {
		reset();
		init(value);
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
	}
	private void init(String source, boolean caseSensitive) {
		super.set(source, caseSensitive);
	}
	private void init(Cfg_Entry source) {
		super.set(source);
	}
	private void init(Cfg_Entry source, boolean caseSensitive) {
		super.set(source, caseSensitive);
	}
	private void init(Double value) {
		floating = toUsableDouble(value);
	}
	private void init(Float value) {
		init(Double.valueOf(value));
	}
	private void init(Long value) {
		whole = value;
	}
	private void init(Integer value) {
		init(Long.valueOf(value));
	}
	private void init(Short value) {
		init(Long.valueOf(value));
	}
	private void init(Byte value) {
		init(Long.valueOf(value));
	}
	private void init(boolean value) {
		bool = value;
	}
}