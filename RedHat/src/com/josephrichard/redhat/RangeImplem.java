package com.josephrichard.redhat;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Joseph Richard
 */
public class RangeImplem implements Range {

    private Map<String, Integer> values;
    
    public RangeImplem() {
        this.values = new HashMap<>();    
    }
    
    /**
     * Return a new Range object
     * @param from start of range
     * @param to end of range
     * @return new Range object containing requested range
     */
    @Override
    public Range newRange(int from, int to) {
        RangeImplem r = new RangeImplem();
        for(int counter=from; counter<=to; counter++) {
            r.values.put(String.valueOf(counter), counter);
        }
        r.values = r.valueComparator();
        return r;
    }

    /**
     * return true if value is in the defined range of this object
     * @param value to search for
     * @return true if is in range/ false if not in range
     */
    @Override
    public boolean isIn(int value) {
        return values.containsKey(String.valueOf(value));
    }

    /**
     * Return the minimum value in a range
     * @return minimum integer value
     */
    @Override
    public int min() {
        return Collections.min(values.values());
    }

    /**
     * Return the maximum value in a range
     * @return maximum integer value
     */
    @Override
    public int max() {
        return Collections.max(values.values());
    }

    /**
     * Add range 'r' to this range, and return 'this'.
     * @param r range to be added
     * @return this Range object
     */
    @Override
    public Range add(Range r) {
        for (int counter=r.min(); counter <= r.max(); counter++) {
            values.put(String.valueOf(counter), counter);
        }
        values = valueComparator();
        return this;
    }
    
    /**
     * Subtract range 'r' from this range and return 'this'.
     * @param r range to be subtracted
     * @return this Range object
     */
    public Range subtract(Range r) {
        for (int counter=r.max(); counter >= r.min(); counter--) {
            values.remove(String.valueOf(counter));
        }
        values = valueComparator();
        return this;        
    }
    
    /**
     * Sort a Map
     * @param unsortMap
     * @return sorted Map
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
		public Map<String, Integer> valueComparator() {
        List list = new LinkedList(values.entrySet());
 
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                if ((o1.getValue()) < (o2.getValue()))
                    return -1;
                else if (o1.getValue()==o2.getValue())
                    return 0;
                else {
                    return 1;
                }
            }
	}); 
        // Read thru list and add to sorted map
	Map sortedMap = new LinkedHashMap();
	for (Iterator it = list.iterator(); it.hasNext();) {
		Map.Entry entry = (Map.Entry) it.next();
		sortedMap.put(entry.getKey(), entry.getValue());
	}
	return sortedMap;
    }  
}
