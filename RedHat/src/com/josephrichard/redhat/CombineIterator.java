package com.josephrichard.redhat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Joseph Richard
 */
public class CombineIterator {

    @SuppressWarnings({ "unchecked", "rawtypes" })
		public List<String> combine(Iterator<String> itr1, Iterator<String> itr2) {
        Map<String, String> itersList = new HashMap<>();
        String element;
        while(itr1.hasNext()) {  // Add iterator 1 
            element = (String)itr1.next();
            itersList.put(element, element);
        }
        while(itr2.hasNext()) {  // Add iterator 2
            element = (String)itr2.next();
            itersList.put(element, element);            
        }
        return (List)itersList.values();
    }
        
}
