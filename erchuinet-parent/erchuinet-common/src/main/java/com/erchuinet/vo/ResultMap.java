package com.erchuinet.vo;

import java.util.HashMap;

public class ResultMap extends HashMap{

	@Override
	public Object put(Object key, Object value) {
		return super.put(key, value==null?"":String.valueOf(value));
	}  
}
