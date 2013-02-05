package com.smv.util.text;

import java.util.List;

public class TextUtils {
	
	/**
     * Joins a list of items into a delimiter-separated string. Each item
     * is converted to a string value with the toString() method before being
     * added to the final delimited list.
     * 
     * @param items
     * the items to include in a delimited string
     * @param delimiter
     * the delimiter character or string to insert between each item in the list
     * @return
     * a delimited string
     */
    public static String join(Object[] items, String delimiter) {
        final StringBuffer sb = new StringBuffer();        
        for (int i = 0; i < items.length; i++) {
            sb.append(items[i]);
            if (i < items.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    
    
    /**
     * Joins a list of <em>int</em>s into a delimiter-separated string.
     * 
     * @param ints
     * the ints to include in a delimited string
     * @param delimiter
     * the delimiter character or string to insert between each item in the list
     * @return
     * a delimited string
     */
    public static String join(final int[] ints, final String delimiter) {
        final StringBuffer sb = new StringBuffer();        
        for (int i = 0; i < ints.length; i++) {
            sb.append(ints[i]);
            if (i < ints.length - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
    
    
    /**
     * Joins a list of items into a delimiter-separated string. Each item
     * is converted to a string value with the toString() method before being
     * added to the final delimited list.
     * 
     * @param items
     * the items to include in a delimited string
     * @param delimiter
     * the delimiter character or string to insert between each item in the list
     * @return
     * a delimited string
     */
    public static String join(List items, String delimiter) {
        final StringBuffer sb = new StringBuffer();        
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i));
            if (i < items.size() - 1) {
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }
}
