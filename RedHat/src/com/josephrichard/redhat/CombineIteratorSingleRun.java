package com.josephrichard.redhat;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Joseph Richard
 */
public class CombineIteratorSingleRun {
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
		public Iterator<String> combine(Iterator<String> itr1, Iterator<String> itr2) {
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
        List sortedMap = new LinkedList(itersList.entrySet());
        Collections.sort(sortedMap, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
	}); 
        return sortedMap.iterator();
    }
   
}
