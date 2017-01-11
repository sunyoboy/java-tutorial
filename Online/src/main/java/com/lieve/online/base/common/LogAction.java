package com.lieve.online.base.common;

import java.util.Properties;

 
public abstract class LogAction extends BaseErrorLog implements ILogAction {
    
    private Properties funcIdAttributes;

    /* (non-Javadoc)
     * @see com.lieve.online.base.ILogAction#getFuncIdAttributes()
     */
    public Properties getFuncIdAttributes() {
        return funcIdAttributes;
    }

    /* (non-Javadoc)
     * @see com.lieve.online.base.ILogAction#setFuncIdAttributes(java.util.Properties)
     */
    public void setFuncIdAttributes(Properties funcIdAttributes) {
        this.funcIdAttributes = funcIdAttributes;
    }  
    
    

}
