package com.hdsx.taxi.woxing.xmpp.util;

import java.util.*;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
/**
 *  配置文件类 <p>
 *
 *  在使用之前应该先设置configFileName <p>
 *
 *  modify by wjz 0312 ,为了同时支持Properties类型和XML类型的配置</p> 对于XML类型的配置，格式如下<p>
 *
 *  &lt;CONFIG&gt;<br>
 *  &lt;DESCRIPTION&gt;关于电子申报的配置文件&lt;/DESCRIPTION&gt;<br>
 *  &lt;ITEM KEY=dbaDBType&gt; <br>
 *  &lt;VALUE&gt;oracle.jdbc.driver.OracleDriver&lt;/VALUE&gt;<br>
 *  &lt;REMARK&gt;oracle驱动程序&lt;/REMARK&gt;<br>
 *  &lt;/ITEM&gt;<br>
 *  &lt;/CONFIG&gt;
 *
 *@author     william
 *@created    2003年4月22日
 */
public class Config {
    //add by wjz 0225
    /**
     *  Description of the Field
     */
    public static int CONFIG_PROPFILE = 1;
    /**
     *  Description of the Field
     */
    public static int CONFIG_XMLFILE = 2;

    private static Config cfg = null;
    private static String configFileName = null;
    //默认的配置文件类型为CONFIG_PROPFILE
    private int config_file_type = 1;
    private Document doc = null;
    //end of add by wjz

    private Properties props;
    private Node rootElement = null;
    private static String workPath = "";
    private Vector tempProps=new Vector();

    private final static Logger logger = LoggerFactory.getLogger(Config.class);
    
    /**
     *  Constructor for the Config object
     */
    public Config() {
        props = new java.util.Properties();
    }


    /**
     *  Sets the ConfigFileName attribute of the Config object
     *
     *@param  cfg  The new ConfigFileName value
     */
    public static void setConfigFileName(String cfg) {
        configFileName = cfg;
        //LogUtil.setConfigFile(cfg);
    }

 public void setProperty(String keyName, String keyValue,boolean isTemp) {
   if(isTemp){
     tempProps.remove(keyName);
     tempProps.add(keyName);
   }
   setProperty(keyName,keyValue);

 }
    /**
     *  Sets the Property attribute of the Config object
     *
     *@param  keyName   The new Property value
     *@param  keyValue  The new Property value
     */
    public void setProperty(String keyName, String keyValue) {
        if (keyName == null || keyValue == null || keyName.length() == 0) {
            return;
        }
            props.setProperty(keyName, keyValue);
    }


    /**
     *  Gets the ConfigFileName attribute of the Config object
     *
     *@return    The ConfigFileName value
     */
    public static String getConfigFileName() {
        return configFileName;
    }


    /**
     *@return     SticConfig
     *@since      2001-12-25 if(cfg==null){ cfg=new SticConfig();
     *      cfg.loadConfig(); }else{ return cfg; }
     *@roseuid    3C2D9408001F
     */
    public static Config getInstance() {
        if (cfg == null) {
            cfg = new Config();
            cfg.loadConfig();
            return cfg;
        } else {
            return cfg;
        }

    }


    /**
     *  Gets the instance attribute of the Config class
     *
     *@param  configFileName  Description of the Parameter
     *@return                 The instance value
     */
    public static Config getInstance(String configFileName) {
        if (cfg == null) {
            cfg.setConfigFileName(configFileName);
            cfg = new Config();
            cfg.loadConfig();
            return cfg;
        } else {
            return cfg;
        }

    }


    /**
     *  Gets the Property attribute of the Config object
     *
     *@param  keyName  Description of Parameter
     *@return          The Property value
     */
    public String getProperty(String keyName) {
    	String s = props.getProperty(keyName);
        return toCHNString(s);
    }


    /**
     *  取得系统的工作目录
     *
     *@return
     */
    public final static String getWorkPath() {
        if (workPath == null || workPath.length() <= 0) {
            workPath = getInstance().getProperty("workDir");
        }
        if (workPath == null) {
            workPath = getInstance().getProperty("workdir");
        }
        return workPath;
    }


    /**
     *  从文件名为stol.properties读取配置
     *
     *@return     int
     *@roseuid    3C22E0060356
     */
    private int loadConfig() {
        InputStream fis = null;
        try {
            try {
                if (configFileName == null || configFileName.length() <= 0) {
                	logger.error("配置文件为空！");
                    return -1;
                }
                fis = Config.class.getResourceAsStream(configFileName);
                props.load(fis);
            } catch (Exception ex1) {
                /*
                 *  如果失败,直接从文件中取
                 */
                fis = new FileInputStream(configFileName);
                props.load(fis);
            }
            logger.debug("load config success:" + configFileName);
            return 0;
        } catch (Exception ex) {
            logger.debug("file may not exist");
            logger.debug(this.getClass().getName() + "::" + ex.getMessage());
            logger.debug(ex.getMessage(),ex);

            return 100;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception ex2) {
            }
        }
    }
    /**
	 * 将字符串转换为中文
	 * 
	 * @param src
	 *            Description of Parameter
	 * @return Description of the Returned Value
	 */
	public static String toCHNString(String src) {
		if (src == null || src.length() <= 0) {
			return src;
		}
		try {
			return new String(src.getBytes("ISO-8859-1"), "utf-8");
		} catch (java.io.UnsupportedEncodingException ex) {
			logger.error(ex.getMessage(), ex);
			return null;
		}

	}
}
