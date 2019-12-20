package com.zhiguan.carownerhomecrm.common;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;

/**
 * 初始化表结构，装入范例数据。
 * 
 * @author limin.shanlm
 */
public class DataLoader   {
    private static Logger logger;

    static {
        logger = LogManager.getLogger("DataLoader");
    }

    private static final String SQLSTR ="?";
    private DataSource dataSource;
    private Resource sqlSchemaDrop;
    private String sqlFileEncoding;
    private Resource testProperty;
    
    public void init(){
    	  Properties prop = new Properties();   
    	  String dbUser = null;
    	  InputStream in =  null;
    	  try {  
    		    in = testProperty.getInputStream();

              prop.load(in);   
              dbUser = prop.getProperty("database.userName").trim();   
              
          } catch (IOException e) {   
              e.printStackTrace();   
          }  finally{
        	  if(in !=null){
        		  try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	  }
          }
          if(!StringUtils.equals(dbUser, "hrsys_utest")){
        	  //throw new Exception("不是单元测试库，不能启动");
          }
    }
    

    public void setTestProperty(Resource testProperty) {
		this.testProperty = testProperty;
	}


	public void truncationData(){
    	 Connection conn = null;
         try {
             conn = dataSource.getConnection();
              
             batchExecute(conn, sqlSchemaDrop);
         } catch (SQLException e) {
         	logger.error(e);
 		} finally {
 	        try {
 	            conn.close();
 	        } catch (Exception e) {
 	        	logger.error(e);
 	        }
         }
    }
    public void loadData(List<Resource> dataResourceList) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            // 初始化数据
            for(Resource res : dataResourceList){
            batchExecute(conn, res);
            }
        } catch (SQLException e) {
        	logger.error(e);
		} finally {
	        try {
	            conn.close();
	        } catch (Exception e) {
	        	logger.error(e);
	        }
        }
    }

    private void batchExecute(Connection conn, Resource scriptFile) throws SQLException {
        List<String> sqlScripts = getBatchSqlScript(scriptFile);
        PreparedStatement stmt = null;
        for (String sql : sqlScripts) {
            try {
            	final String sqlStr = trimSql(sql);            	
                if(!StringUtils.isEmpty(sqlStr)){
                	stmt = conn.prepareStatement(SQLSTR);
                	stmt.setString(1, sqlStr);
                	stmt.execute(sqlStr);              	
                }
            } catch (SQLException e) {
            	logger.error("failed to execute sql " + sql, e);
            }
            finally{
            	if(stmt!=null){
            		stmt.close();
            	}         	
            }
        }        
    }
    
    private List<String> getBatchSqlScript(Resource scriptFile) {
        List<String> sqlScripts = new ArrayList<String>();
        String txt = null;
        /*try {
            //txt= StreamUtil.readText(scriptFile.getInputStream(), getSqlFileEncoding());
        } catch (IOException e) {
        	logger.error("failed to readText from file " + scriptFile, e);
        }*/
        if(txt!=null)
        {      	 
            if(StringUtils.contains(txt, ';'))
            {
               sqlScripts = parseSql(txt);
            }
            else
            {
                String[] ss = StringUtils.split(txt, '\n');
                sqlScripts = Arrays.asList(ss);
            }
        }        
        return sqlScripts;
    }
    
    
    
    /**
     * 清除头尾的特殊字符
     * @param sql
     * @return
     */
    private String trimSql(String sql){
    	//return StringUtils.trim(sql,"\r\n");
    	return null;
    }
    /**
     * 从Sql文件中解析SQL
     * @param txt
     * @return
     */
    private List<String> parseSql(String txt){
    	List<String> sqlScripts = new ArrayList<String>();
    	boolean inString = false;
        int nextBegin = 0;
        for(int i=0;i<txt.length();i++){
     	   char c = txt.charAt(i);
     	   if(c=='\''){
     		   inString = !inString;
     	   }
     	   if(c==';' && !inString){
     		   sqlScripts.add(txt.substring(nextBegin, i));
     		   nextBegin = i+1;
     	   }
        }
        return sqlScripts;
    }

	public void setSqlFileEncoding(String sqlFileEncoding) {
		this.sqlFileEncoding = sqlFileEncoding;
	}

	public String getSqlFileEncoding() {
		return sqlFileEncoding;
	}
	
   
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    

    public void setSqlSchemaDrop(Resource sqlSchemaDrop) {
        this.sqlSchemaDrop = sqlSchemaDrop;
    }
}

