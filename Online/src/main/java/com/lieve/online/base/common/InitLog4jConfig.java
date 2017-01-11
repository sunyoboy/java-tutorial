package com.lieve.online.base.common;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class InitLog4jConfig extends HttpServlet {

    protected Logger logger = Logger.getLogger(this.getClass());

    
    /**
     * Constructor of the object.
     */
    public InitLog4jConfig() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * Initialization of the servlet. <br>
     * 
     * @throws ServletException
     *             if an error occurs
     */
    public void init() throws ServletException {
        // Put your code here
        String configFilePath = "";

        try {

            String rootPath = this.getServletContext().getRealPath("/");

            Constant.setRootPath(rootPath);

            logger.info("rootPath " + rootPath);

            String webRoot = rootPath.substring(0, rootPath.length() - 5);

            Constant.setWebRootPath(webRoot);

            logger.info("webRoot " + webRoot);

            String htmlPath = rootPath + "log/";
            logger.info("htmlPath " + htmlPath);

            String logPath = webRoot + "log/";
            logger.info("logPath " + logPath);
            Properties props = new Properties();

            configFilePath = rootPath + "WEB-INF/classes/log4j.properties";

            FileInputStream istream = new FileInputStream(configFilePath);

            props.load(istream);

            istream.close();

            Set set = props.keySet();

            Iterator it = set.iterator();

            int index = 0;

            while (it.hasNext()) {
                String key = (String) it.next();

                if (key.endsWith(".File")) {

                    String logFile = props.getProperty(key);

                    index = logFile.lastIndexOf("/");
                    if (index < 0) {
                        index = logFile.lastIndexOf("\\");
                    }
                    if (index >= 0) {
                        logFile = logFile.substring(index + 1);
                    }

                    if (logFile.endsWith(".html")) {
                        logFile = htmlPath + logFile;
                    } else {
                        logFile = logPath + logFile;
                    }

                    props.setProperty(key, logFile);

                }

            }

            PropertyConfigurator.configure(props);// 装入log4j配置信息

        } catch (IOException e) {
            // this.log(" fil")
            this.log(" 文件不存在 ：" + configFilePath);

            return;
        }

    }

}
