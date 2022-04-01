package br.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;

public class CfgBlock {

	private LinkedHashMap<String, CfgLine> cfgBlock;
	
	// ------------------------------------------------------------------------
	// Constructors and Helpers
	//
	public CfgBlock() {
		cfgBlock = new LinkedHashMap<String, CfgLine>();
	}

	// ------------------------------------------------------------------------
	// Getters and Setters
	//
	/**
	 * Return list of UpperCase User Setting Keys with given filter value
	 */
	private List<String> getUserSettingKeyList (String filter) {
		List<String> list = new ArrayList<String>();
		for (Entry<String, CfgLine> entry : cfgBlock.entrySet()) {
			if (entry.getValue().value().get().equalsIgnoreCase(filter)) {
				list.add(entry.getKey());
			}
		}
		return list;
	}	
	/**
	 * Return Set of UpperCase User Setting Keys
	 */
	private LinkedHashSet<String> getUserSettingKeySet () {
		return new LinkedHashSet<String>(cfgBlock.keySet());
	}
	/**
	 * Return selected key as CfgLine 
	 */
	private CfgLine getLine(String key) {
		if (key == null) { 
			return null;
		}
		return cfgBlock.get(key.toUpperCase());
	}
	/**
	 * Return selected key as CfgLine or default if none
	 */
	private CfgLine getOrDefaultLine(String key, CfgLine defaultCfgLine) {
		if (key != null && cfgBlock.containsKey(key.toUpperCase())) {
			return cfgBlock.get(key.toUpperCase());	
		}
		return defaultCfgLine;
	}
	/**
	 * Return selected key value as CfgField 
	 */
	private CfgField getValue(String key) {
		if (key == null) { 
			return null;
		}
		return cfgBlock.get(key.toUpperCase()).value();
	}
	/**
	 * Return selected key value as CfgField or default if none
	 */
	private CfgField getOrDefaultValue(String key, CfgField defaultValue) {
		if (key != null && cfgBlock.containsKey(key.toUpperCase())) {
			return cfgBlock.get(key.toUpperCase()).value();	
		}
		return defaultValue;
	}
}
