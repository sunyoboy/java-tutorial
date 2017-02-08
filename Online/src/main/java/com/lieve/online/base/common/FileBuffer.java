package com.lieve.online.base.common;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

/**
 *
 * 配置文件的缓存<br>
 * 〈功能详细描述〉
 *
 * @author sunlj
 */
public class FileBuffer implements Runnable {

    private static Logger logger = Logger.getLogger(FileBuffer.class);

    private static final String CONFIG_FILE_PATH = "configs.ini";

    private static final String BUFFER_FILE_PATH = "filebuffer.ini";

    private static FileBuffer instance;

    private Properties prop;

    private Hashtable htConfigFile;

    private boolean stopFlag;

    private boolean succeedFlag;

    private PrintWriter logFile;

    private long lInterval;

    private static String webRoot = Constant.getWebRootPath() + "config/";

    public static FileBuffer getInstance() {
        return getInstance(CONFIG_FILE_PATH, BUFFER_FILE_PATH);
    }

    public static FileBuffer getInstance(String configFilePath,
                                         String bufferFilePath) {
        if (instance == null) {

            logger.debug("配置文件的路径  :" + webRoot);
            if (webRoot == null) {
                logger.debug("读取配置文件失败，没有找到相应的文件的路径." + webRoot);
                return null;
            }
            instance = new FileBuffer(webRoot + configFilePath, webRoot
                    + bufferFilePath);
            if (!instance.succeedFlag) {
                return instance;
            }
            Thread thread = new Thread(instance);
            thread.start();
        }
        return instance;
    }

    private FileBuffer(String configPath, String fileBufferPath) {
        prop = null;
        htConfigFile = null;
        prop = new Properties();
        htConfigFile = new Hashtable();
        succeedFlag = true;
        stopFlag = false;
        init(configPath, fileBufferPath);
    }

    public String getProperty(String fileName, String property) {
        String value = null;
        ConfigFile configFile = (ConfigFile) htConfigFile.get(fileName);
        if (configFile == null) {
            log("getProperty(String fileName, String property)  没有读到配置文件:"
                    + fileName);
            return null;
        } else {
            value = configFile.getProperty(property);
            return value;
        }
    }

    public String getProperty(String fileName, String property,
                              String defaultValue) {
        String value = null;
        ConfigFile configFile = (ConfigFile) htConfigFile.get(fileName);
        if (configFile == null) {
            log(" getProperty(String fileName, String property, String defaultValue)  没有读到配置文件:"
                    + fileName);
            return defaultValue;
        }
        value = configFile.getProperty(property);
        if (value == null) {
            value = defaultValue;
        }
        return value;
    }

    public Hashtable getPropeties(String fileName) {
        ConfigFile configFile = (ConfigFile) htConfigFile.get(fileName);
        return configFile.getProperties();
    }

    public synchronized void run() {
        long curTime = 0L;
        long endTime = 0L;
        while (!stopFlag) {
            curTime = System.currentTimeMillis();
            logger.debug("     curTime      " + curTime);
            try {
                wait(lInterval);
            } catch (InterruptedException e) {
                endTime = System.currentTimeMillis();
                logger.debug("     endTime      " + endTime);

                if (endTime - curTime - lInterval < 0L) {
                    continue;
                }
            }
            update();
        }
    }

    protected void finalize() throws Throwable {
        stopThread();
        super.finalize();
    }

    public void stopThread() {
        logFile.close();
        stopFlag = true;
        instance = null;
    }

    public boolean getSucceedflag() {
        return succeedFlag;
    }

    private void readBufferFile(String configFilePath, String bufferFilePath) {
        InputStream is = null;
        String strInterval = null;
        try {
            is = new FileInputStream(bufferFilePath);
            if (is == null) {
                logger.debug("读取配置文件时出错：" + bufferFilePath);
                succeedFlag = false;
                return;
            }
            prop.load(is);
            is.close();
        } catch (IOException e) {
            logger.debug("从内存中读取文件时出错");
            succeedFlag = false;
            return;
        }
        // String sLogFile = prop.getProperty("logfile", "bufferLogfile.log");
        // logFile = Util.getLogFile(sLogFile);
        // if(logFile == null)
        // {
        // succeedFlag = false;
        // return;
        // }
        strInterval = prop.getProperty("interval", "120");
        try {
            lInterval = Util.StringToLong(strInterval.trim(), 120L) * 1000L;
            logger.debug("  lInterval======" + lInterval);

        } catch (NumberFormatException ex) {
            log("读取配置文件的刷新周期错误！！", ex);
            return;
        }
        prop.clear();
        try {
            is = new FileInputStream(configFilePath);
            prop.load(is);
            is.close();
        } catch (IOException e) {
            log("加栽配置文件时出现错误", e);
            succeedFlag = false;
            return;
        }
    }

    private void createClassInstance() {
        if (!succeedFlag) {
            return;
        }
        int i = 0;
        int size = prop.size();
        String fileName = null;
        ConfigFile configFileArray[] = new ConfigFile[size];
        for (int j = 0; j < size; j++) {
            configFileArray[j] = new ConfigFile();
        }

        for (Enumeration fileNames = prop.keys(); fileNames.hasMoreElements();) {
            fileName = (String) fileNames.nextElement();
            htConfigFile.put(fileName, configFileArray[i]);
            i++;
        }

    }

    private void readConfigFile(String fileName) {

        logger.debug("readConfigFile(String fileName) 加加载文件的配置项 fileName="
                + fileName);
        ConfigFile configFile = (ConfigFile) htConfigFile.get(fileName);
        InputStream is = null;
        String propName = null;
        String propValue = null;
        Properties lProp = new Properties();
        String filePath = webRoot + prop.getProperty(fileName);
        try {
            is = new FileInputStream(filePath);
            lProp.load(is);
            is.close();
        } catch (Exception e) {
            log("读取配置文件时出错:" + fileName, e);
            succeedFlag = false;
            return;
        }
        configFile.clearContent();
        long oldTime = (new File(filePath)).lastModified();
        configFile.setModifiedTime(oldTime);
        Enumeration properties = lProp.keys();
        byte bytes[] = (byte[]) null;
        for (; properties.hasMoreElements(); configFile.addProperty(propName,
                propValue)) {
            propName = (String) properties.nextElement();
            if (propName != null) {
                propName = propName.trim();
            }
            propValue = lProp.getProperty(propName);
            if (propValue != null) {
                propValue = propValue.trim();
                try {
                    bytes = propValue.getBytes("ISO-8859-1");
                    propValue = new String(bytes, "GBK");
                } catch (UnsupportedEncodingException ex) {
                    log("转换代码时失败", ex);
                }
            }
        }

    }

    private void init(String configFilePath, String bufferFilePath) {
        String name = null;
        readBufferFile(configFilePath, bufferFilePath);
        createClassInstance();
        if (!succeedFlag) {
            return;
        }
        for (Enumeration fileNames = htConfigFile.keys(); fileNames
                .hasMoreElements(); readConfigFile(name)) {
            name = (String) fileNames.nextElement();
        }

    }

    private boolean checkFile(String fileName) {
        String filePath = null;
        try {
            filePath = webRoot + (String) prop.get(fileName);
            long newTime = (new File(filePath)).lastModified();
            ConfigFile configFile = (ConfigFile) htConfigFile.get(fileName);
            return configFile.getModifiedTime() != newTime;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.debug("读取配置文件: " + fileName + " 文件的路径: " + filePath);
        return false;
    }

    private void update() {
        ConfigFile config = null;
        String fileName = null;
        for (Enumeration fileNames = htConfigFile.keys(); fileNames
                .hasMoreElements();) {
            if (stopFlag) {
                return;
            }
            fileName = (String) fileNames.nextElement();
            if (checkFile(fileName)) {
                readConfigFile(fileName);
            }
        }

    }

    private void log(String msg) {
        logger.debug("FileBuffer.Loge()" + msg);
    }

    private void log(String msg, Throwable e) {
        log(msg);
        e.printStackTrace(logFile);
    }

    private static String getconfigfilepath() {

        return null;// Constant.getWebRootPath(); // (new InitPropLoader()).getHome();

    }

}

/**
 * A very small class to load the jive_init.properties file. The class is needed
 * since loading files from the classpath in a static context often fails.
 */
/*
 * class InitPropLoader { private Logger logger =
 * Logger.getLogger(FileBuffer.class);
 * public String getHome() { String configfilepath = ""; try {
 * InputStream in = getClass().getResourceAsStream("/config.properties");
 * Properties prop = new Properties(); prop.load(in);
 * configfilepath = prop.getProperty("configfilepath");
 * if (!(configfilepath.endsWith("" + File.separatorChar))) { configfilepath =
 * configfilepath + File.separatorChar; } } catch (Exception e) {
 * logger.debug("系统配置错误", e); throw new RuntimeException("系统配置错误"); } return
 * configfilepath;
 * } }
 */
