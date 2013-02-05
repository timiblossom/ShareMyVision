/**
 * 
 */
package com.smv.service.mailer.helper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.smv.common.bean.KeyValueMapDTO;
import com.smv.common.bean.KeyValueEntryDTO;

/**
 * @author TriNguyen
 *
 */
public class KeyValueMapHelper {

	public static Map convertToMap(KeyValueMapDTO keyValueMapDTO) {
		Map<String, String> mapResult = new HashMap<String, String>();
		
		if (keyValueMapDTO != null) {
			List<KeyValueEntryDTO> entries = keyValueMapDTO.getEntries();
			
			if (entries != null) {
				for (KeyValueEntryDTO entry : entries) {
					mapResult.put(entry.getKey(), entry.getValue());
				}
			}
		}
		
		return mapResult;
	}

}
