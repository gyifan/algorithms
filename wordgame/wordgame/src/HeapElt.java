/**
 * A generic class for heap elements that include handles
 */

public class HeapElt {

    protected String record;

    protected int handle = 0;

    public void setRecord(String inRec){
	record = inRec;
    }

    public String getRecord() {
	return record;
    }

    public void setHandle(int inHandle){
	handle = inHandle;
    }

    public int getHandle() {
	return handle;
    }

}

