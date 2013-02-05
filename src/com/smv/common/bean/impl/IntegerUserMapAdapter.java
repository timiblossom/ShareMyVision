
package com.smv.common.bean.impl;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.smv.common.bean.UserDTO;



public class IntegerUserMapAdapter extends XmlAdapter<IntegerUserMap, Map<Integer, UserDTO>> {
    public IntegerUserMap marshal(Map<Integer, UserDTO> v) throws Exception {
        IntegerUserMap map = new IntegerUserMap();
        for (Map.Entry<Integer, UserDTO> e : v.entrySet()) {
            IntegerUserMap.IntegerUserEntry iue = new IntegerUserMap.IntegerUserEntry();
            iue.setUser(e.getValue());
            iue.setId(e.getKey());
            map.getEntries().add(iue);
        }
        return map;
    }

    public Map<Integer, UserDTO> unmarshal(IntegerUserMap v) throws Exception {
        Map<Integer, UserDTO> map = new LinkedHashMap<Integer, UserDTO>();
        for (IntegerUserMap.IntegerUserEntry e : v.getEntries()) {
            map.put(e.getId(), e.getUser());
        }
        return map;
    }

}

