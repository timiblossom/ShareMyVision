package com.smv.common.bean;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class KeyValueMapAdapter extends XmlAdapter<KeyValueMapDTO, Map<String, String>> {
    public KeyValueMapDTO marshal(Map<String, String> v) throws Exception {
    	KeyValueMapDTO map = new KeyValueMapDTO();
        for (Map.Entry<String, String> e : v.entrySet()) { 
        	KeyValueEntryDTO kvp = new KeyValueEntryDTO();
        	kvp.setValue(e.getValue());
            kvp.setKey(e.getKey());
            map.getEntries().add(kvp);
        }
        return map;
    }

    public Map<String, String> unmarshal(KeyValueMapDTO v) throws Exception {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (KeyValueEntryDTO e : v.getEntries()) {
            map.put(e.getKey(), e.getValue());
        }
        return map;
    }

}