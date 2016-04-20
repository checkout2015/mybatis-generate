package cn.xiaoniu.generate.utils;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class  JavaTypeResolverUtils{


	public static boolean forceBigDecimals;

    private static Map<Integer, JdbcTypeInformation> typeMap;
    
    static{
        typeMap = new HashMap<Integer, JdbcTypeInformation>();

        typeMap.put(Types.ARRAY, new JdbcTypeInformation("ARRAY", 
                Object.class.getName()));
        typeMap.put(Types.BIGINT, new JdbcTypeInformation("BIGINT", 
                Long.class.getName()));
        typeMap.put(Types.BINARY, new JdbcTypeInformation("BINARY", 
                "byte[]")); 
        typeMap.put(Types.BIT, new JdbcTypeInformation("BIT", 
                Boolean.class.getName()));
        typeMap.put(Types.BLOB, new JdbcTypeInformation("BLOB", 
                "byte[]")); 
        typeMap.put(Types.BOOLEAN, new JdbcTypeInformation("BOOLEAN", 
                Boolean.class.getName()));
        typeMap.put(Types.CHAR, new JdbcTypeInformation("CHAR", 
                String.class.getName()));
        typeMap.put(Types.CLOB, new JdbcTypeInformation("CLOB", 
                String.class.getName()));
        typeMap.put(Types.DATALINK, new JdbcTypeInformation("DATALINK", 
                Object.class.getName()));
        typeMap.put(Types.DATE, new JdbcTypeInformation("DATE", 
                Date.class.getName()));
        typeMap.put(Types.DISTINCT, new JdbcTypeInformation("DISTINCT", 
                Object.class.getName()));
        typeMap.put(Types.DOUBLE, new JdbcTypeInformation("DOUBLE", 
                Double.class.getName()));
        typeMap.put(Types.FLOAT, new JdbcTypeInformation("FLOAT", 
                Double.class.getName()));
        typeMap.put(Types.INTEGER, new JdbcTypeInformation("INTEGER", 
                Integer.class.getName()));
        typeMap.put(Types.JAVA_OBJECT, new JdbcTypeInformation("JAVA_OBJECT", 
                Object.class.getName()));
        typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("LONGNVARCHAR", 
                String.class.getName()));
        typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation(
                "LONGVARBINARY", 
                "byte[]")); 
        typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("LONGVARCHAR", 
                String.class.getName()));
        typeMap.put(Types.NCHAR, new JdbcTypeInformation("NCHAR", 
                String.class.getName()));
        typeMap.put(Types.NCLOB, new JdbcTypeInformation("NCLOB", 
                String.class.getName()));
        typeMap.put(Types.NVARCHAR, new JdbcTypeInformation("NVARCHAR", 
                String.class.getName()));
        typeMap.put(Types.NULL, new JdbcTypeInformation("NULL", 
                Object.class.getName()));
        typeMap.put(Types.OTHER, new JdbcTypeInformation("OTHER", 
                Object.class.getName()));
        typeMap.put(Types.REAL, new JdbcTypeInformation("REAL", 
                Float.class.getName()));
        typeMap.put(Types.REF, new JdbcTypeInformation("REF", 
                Object.class.getName()));
        typeMap.put(Types.SMALLINT, new JdbcTypeInformation("SMALLINT", 
                Short.class.getName()));
        typeMap.put(Types.STRUCT, new JdbcTypeInformation("STRUCT", 
                Object.class.getName()));
        typeMap.put(Types.TIME, new JdbcTypeInformation("TIME", 
                Date.class.getName()));
        typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("TIMESTAMP", 
                Date.class.getName()));
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", 
                Byte.class.getName()));
        typeMap.put(Types.VARBINARY, new JdbcTypeInformation("VARBINARY", 
                "byte[]")); 
        typeMap.put(Types.VARCHAR, new JdbcTypeInformation("VARCHAR", 
                String.class.getName()));
        
    }

    public static JdbcTypeInformation calculateJavaType(int jdbcType,String jdbcTypeName,int scale,int length) {
        JdbcTypeInformation jdbcTypeInformation = typeMap.get(jdbcType);
        if (jdbcTypeInformation == null||jdbcType==Types.OTHER) {
            switch (jdbcType) {
            case Types.DECIMAL:
            	jdbcTypeInformation = new JdbcTypeInformation("DECIMAL", calculateJdbcTypeName(scale,length));
            	break;
            case Types.NUMERIC:
            	jdbcTypeInformation = new JdbcTypeInformation("NUMERIC", calculateJdbcTypeName(scale,length));
                break;
            default:
            	String classTypeName = calculateJdbcTypeName(jdbcTypeName);
            	if(classTypeName!=null){
            		jdbcTypeInformation = new JdbcTypeInformation("OTHER",classTypeName);
            	}
                break;
            }
        }
        return jdbcTypeInformation;
    }
    
    private static String calculateJdbcTypeName(String jdbcTypeName) {
    	jdbcTypeName = jdbcTypeName.toLowerCase();
		String dataType = null;
		if (jdbcTypeName.contains("char") || jdbcTypeName.contains("text"))
			dataType = String.class.getName();
		else if (jdbcTypeName.contains("float")||jdbcTypeName.contains("double"))
			dataType = BigDecimal.class.getName();
		else if (jdbcTypeName.contains("date")||jdbcTypeName.contains("time"))
			dataType = Date.class.getName();
		else if (jdbcTypeName.contains("day")||jdbcTypeName.contains("year"))
			dataType = Integer.class.getName();
		else if (jdbcTypeName.contains("clob"))
			dataType = Clob.class.getName();
		return dataType;
	}
    
    
    private static String calculateJdbcTypeName(int scale,int length){
    	String javaFullName = null;
    	if (scale > 0|| length > 18|| forceBigDecimals) {
        	javaFullName = BigDecimal.class.getName();
        } else if (length > 9) {
        	javaFullName = Long.class.getName();
        } else if (length > 4) {
        	javaFullName = Integer.class.getName();
        } else {
        	javaFullName = Short.class.getName();
        }
    	return javaFullName;
    }

  
    public static class JdbcTypeInformation {
        private String jdbcTypeName;
        private String javaShortName;
        private String javaFullName;
        private String javaPackage;
        
        public JdbcTypeInformation(String jdbcTypeName,String javaFullName){
        	this.jdbcTypeName =jdbcTypeName;
        	this.javaFullName =javaFullName;
        	 int index = javaFullName.lastIndexOf('.');
             if (index != -1) {
            	 javaShortName = javaFullName.substring(index + 1);
            	 javaPackage = javaFullName.substring(0,index);
             }
             if(javaFullName.endsWith("]")){
            	 javaShortName = javaFullName;
            	 javaPackage = null;
            	 this.javaFullName = null;
             }
        }
        
		public String getJavaPackage() {
			return javaPackage;
		}
		public void setJavaPackage(String javaPackage) {
			this.javaPackage = javaPackage;
		}
		public String getJdbcTypeName() {
			return jdbcTypeName;
		}
		public void setJdbcTypeName(String jdbcTypeName) {
			this.jdbcTypeName = jdbcTypeName;
		}
		public String getJavaShortName() {
			return javaShortName;
		}
		public void setJavaShortName(String javaShortName) {
			this.javaShortName = javaShortName;
		}
		public String getJavaFullName() {
			return javaFullName;
		}
		public void setJavaFullName(String javaFullName) {
			this.javaFullName = javaFullName;
		}
    }
        
    }