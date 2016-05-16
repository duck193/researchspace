package com.josephrichard.redhat;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Joseph Richard
 */
public class StringNames {
    
    public String addStringItems(String[] items, boolean forceUpperCase) {
        StringBuffer returnValue = new StringBuffer();
        for(int i = 0; i < items.length; i++) {
            returnValue.append(items[i]);
        }
        return forceUpperCase?returnValue.toString().toUpperCase():returnValue.toString();
    }
    
    public String addStringItemsCollectionsFramework(String[] items, boolean forceUpperCase) {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(items));
        StringBuffer returnValue = new StringBuffer();
        for(String value : strings) {
            returnValue.append(value);
        }
        return forceUpperCase?returnValue.toString().toUpperCase():returnValue.toString();
    }
}
