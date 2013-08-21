package com.ilovegolf.util;

import java.util.HashMap;

public class StringHashMap extends HashMap<String, String>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getString(Object key){
		return (String)this.get(key);
	}

	public String toString(){
		StringBuffer sb = new StringBuffer("============StringHashMap============");
		sb.append("\n");
		Object[] objs = this.keySet().toArray();
		String str=null;
		for(int i=0; i<objs.length; i++){
			str=this.get(objs[i]);
			if(str.length()<2000){
				sb.append(objs[i]+"="+str+"  ");
			}else{
				sb.append(objs[i]+"="+str.substring(0, 10)+"  ");
			}
			
		}  
		sb.append("\n=====================================");
		return sb.toString();
	}

}
