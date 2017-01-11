package com.lieve.online.base.common;

import java.util.Hashtable;

public class ConfigFile {

    private Hashtable properties;

    private long lModifiedTime;

    public ConfigFile() {
        properties = new Hashtable();
    }

    public synchronized void addProperty(String name, String value) {
        properties.put(name, value);
    }

    public synchronized String getProperty(String name) {
        String strTemp = null;
        if (properties.get(name) != null) {
            strTemp = (String) properties.get(name);
        }
        return strTemp;
    }

    public synchronized long getModifiedTime() {
        return lModifiedTime;
    }

    public synchronized void setModifiedTime(long time) {
        lModifiedTime = time;
    }

    public synchronized void clearContent() {
        properties.clear();
    }

    public synchronized Hashtable getProperties() {
        return properties;
    }
}
