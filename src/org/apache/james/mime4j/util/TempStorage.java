package org.apache.james.mime4j.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @version $Id: TempStorage.java,v 1.2 2004/10/02 12:41:11 ntherning Exp $
 */
public abstract class TempStorage {
    private static Log log = LogFactory.getLog(TempStorage.class);
    private static TempStorage inst = null;
    
    static {
        
        String clazz = System.getProperty("org.apache.james.mime4j.tempStorage");
        try {
            
            if (inst != null) {
                inst = (TempStorage) Class.forName(clazz).newInstance();
            }
            
        } catch (Throwable t) {
            log.warn("Unable to create or instantiate TempStorage class '" 
                      + clazz + "' using SimpleTempStorage instead", t);
        }

        if (inst == null) {
            inst = new SimpleTempStorage();            
        }
    }
    
    /**
     * Gets the root temporary path which should be used to 
     * create new temporary paths or files.
     * 
     * @return the root temporary path.
     */
    public abstract TempPath getRootTempPath();
    
    public static TempStorage getInstance() {
        return inst;
    }
    
    public static void setInstance(TempStorage inst) {
        if (inst == null) {
            throw new NullPointerException("inst");
        }
        TempStorage.inst = inst;
    }
}
