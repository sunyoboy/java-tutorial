package com.lieve.online.base.common;

import org.apache.log4j.Logger;

public class ErrorPool {

    private static Logger logger = Logger.getLogger(ErrorPool.class);

    // 从配置文件中读取
    private static final String fileName = "error.ini";

    /**
     * @return String the errMsg
     */
    public static String getErrMsg(String sqlError) {

        String key = getKey(sqlError);

        logger.debug("Key=" + key);

        String result = ConfigFileReader.getPropertyFromBuffer(fileName, key);

        logger.debug("result=" + result);

        if (result != null)

            result = result.trim();

        if (result == null || result.equals("")) {
            // 没有取到相应的配置信息
            logger.debug(" 在" + fileName + "文件中没有找到对应的配置信息,key=" + key);

            result = null;

        }

        return result;// null;//sqlErrorPool.get(getKey(sqlError));

    }

    private static String getKey(String sqlError) {
        //
        // java.sql.BatchUpdateException: ORA-00001: 违反唯一约束条件
        // (DDPMS.UK_DDPMS_PRODUCT_CODE)
        // java.sql.SQLException: ORA-02292: 违反完整约束条件
        // (DDPMS.FK_DDPMS_PRODUCTCONFIG_PN) - 已找到子记录
        String key = "";

        if (sqlError != null) {

            // hibernate ora
            /*
             * key = sqlError.substring(sqlError.lastIndexOf(".") + 1);
             * if(key.indexOf(")")>0) { key = key.substring(0,
             * key.indexOf(")"));
             * 
             * if (sqlError.indexOf("已找到子记录") > 0) { key = key + "_delete"; }
             * 
             * key=key.toLowerCase(); }
             */
            sqlError = sqlError.toLowerCase();

            // 违反唯一约束
            if (Util.isNull(key)) {
                // Duplicate entry 'zhenjw' for key 'uq_base_ui_ln'
                String judgeKey = "for key";
                int index = sqlError.indexOf(judgeKey);
                if (index > 0) {
                    String temp = sqlError.substring(index + judgeKey.length());
                    key = temp.trim().replaceAll("'", "");
                }

            }

            // 违反外键约束
            if (Util.isNull(key)) {
                int index = sqlError.lastIndexOf("constraint");
                if (index > 0) {
                    String temp = sqlError.substring(index
                            + "constraint".length());
                    index = temp.indexOf("foreign key");
                    if (index > 0) {
                        temp = temp.substring(0, index);
                        key = temp.trim().replaceAll("`", "");

                        if (sqlError.indexOf("cannot delete or update") >= 0) {
                            key = key + "_delete";
                        }
                    }
                }
            }

        }
        if (Util.isNull(key)) {
            key = "";
        }
        return key;
    }

    public static void main(String[] args) {

        // Cannot delete or update a parent row: a foreign key constraint fails
        // (`qsoccer`.`base_userinfo`, CONSTRAINT `fk_base_ui_rn` FOREIGN KEY
        // (`RoleNo`) REFERENCES `base_sysrole` (`RoleNo`))
        // Cannot add or update a child row: a foreign key constraint fails
        // (`qsoccer`.`base_userinfo`, CONSTRAINT `fk_base_ui_rn` FOREIGN KEY
        // (`RoleNo`) REFERENCES `base_sysrole` (`RoleNo`))
        // "Duplicate entry 'zhenjw' for key 'uq_base_ui_ln'";

        String sqlError = "Cannot delete or update a parent row: a foreign key constraint fails (`qsoccer`.`base_userinfo`, CONSTRAINT `fk_base_ui_rn` FOREIGN KEY (`RoleNo`) REFERENCES `base_sysrole` (`RoleNo`))";

        System.out.println(getKey(sqlError));

        sqlError = "Cannot add or update a child row: a foreign key constraint fails (`qsoccer`.`base_userinfo`, CONSTRAINT `fk_base_ui_rn` FOREIGN KEY (`RoleNo`) REFERENCES `base_sysrole` (`RoleNo`))";

        System.out.println(getKey(sqlError));

        sqlError = "Duplicate entry 'zhenjw' for key 'uq_base_ui_ln'";

        System.out.println(getKey(sqlError));

        sqlError = "Duplicate entry '102' for key 'uq_qs_school_id'";

        System.out.println(getKey(sqlError));

    }

}
