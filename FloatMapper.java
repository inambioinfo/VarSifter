import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

/**
*   Subclass of AbstractMapper to handle Floats
*   @author Jamie K. Teer
*/
public class FloatMapper implements AbstractMapper {
    private Map<Float, Integer> dataMap;
    private Map<Integer, Float> indexMap;
    private final static int dataType = VarData.FLOAT;
    private int lastIndex = 0;

    /**
    *   Constructor
    *
    */
    public FloatMapper() {
        dataMap = new HashMap<Float, Integer>(5000, 0.75f);
        indexMap = new HashMap<Integer, Float>(5000, 0.75f);
    }


    public BitSet filterWithPattern(Pattern pat) {
        BitSet bs = new BitSet(dataMap.size());
        for (Float f: dataMap.keySet()) {
            if (pat.matcher(f.toString()).find()) {
                bs.set(dataMap.get(f));
            }
        }
        return bs;
    }


    public int getDataType() {
        return dataType;
    }


    public int getIndexOf( Object obj ) {
        if (dataMap.get(obj) == null) {
            return -1;
        }
        else {
            return dataMap.get(obj).intValue();
        }
    }


    /**
    *   Add new Float and return index
    *
    *   @param obj The Float to add
    *   @return Index of newly added Float
    */
    public int addData(Object obj) {
        Float inF = (Float)obj;
        int index = getIndexOf(inF);
        if (index == -1) {
            dataMap.put(inF, Integer.valueOf(lastIndex));
            indexMap.put(Integer.valueOf(lastIndex), inF);
            lastIndex++;
            return lastIndex - 1; //remove 1 to get index;
        }
        else {
            return index;
        }
    }


    /**
    *   Convert to string and return requested String
    *
    *   @param index Index of Float to convert and return
    *   @return String form of requested Float
    */
    public String getString(int index) {
        return indexMap.get(Integer.valueOf(index)).toString();
    }


    /**
    *   Convert to float and return requested Float
    *
    *   @param index Index of Float to convert and return
    *   @return primitive form of requested Float
    */
    public float getFloat(int index) {
        return ((Float)indexMap.get(Integer.valueOf(index))).floatValue();
    }

    public int getLength() {
        return lastIndex;
    }

    /**
    *   Not currently used
    */
    public String[] getSortedEntries() {
        return null;
    }

}
