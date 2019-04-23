package com.noteshare.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DbUtil{

	private static Log log = LogFactory.getLog(DbConnection.class);
	/**
	 * 获取connection连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection conn = DbConnection.getConn();
		return conn;
	}
	
	public static void closeConnection(){
		DbConnection.closeConnection();
	}

	// 获取statement连接
	public static Statement getStatement(){
		Connection conn = getConnection();
		Statement stat = null;
		try{
			stat = conn.createStatement();
		}
		catch (SQLException e){
			log.info(e.getMessage());
		    throw new RuntimeException(e.getMessage(), e);
		}
		return stat;
	}

	// 获取preparedStatment连接
	public static PreparedStatement getPreparedStatement(String sql,Object[] parameters){
		Connection conn = getConnection();
		PreparedStatement pstat = null;
		try{
			pstat = conn.prepareStatement(sql);
			if (parameters != null && parameters.length > 0){
				pstat = conn.prepareStatement(sql);
				for (int i = 0; i < parameters.length; i++){
					pstat.setObject(i + 1, parameters[i]);
				}
			}
		}
		catch (SQLException e){
			log.info(e.getMessage());
		    throw new RuntimeException(e.getMessage(), e);
		}
		return pstat;
	}

	/**
	 * 调用存储过程
	 * @param sql{call.queryempinfo(?,?,?,?)}
	 * @param inparamerters
	 * @param outparamerters
	 * @return
	 */
	public static CallableStatement gerCall(String sql,Object[] inparamerters,Object[] outparamerters){
		Connection conn = getConnection();
		CallableStatement call = null;
		try{
			call = conn.prepareCall(sql);
			if(inparamerters!=null){
				for (int i = 0; i < inparamerters.length; i++){
					call.setObject(i + 1, inparamerters[i]);
				}
			}
			if(outparamerters!=null){
				for (int i = 0; i < outparamerters.length; i++){
					if(outparamerters[i] instanceof String){
						call.registerOutParameter(inparamerters.length+1+i, Types.VARCHAR);
					} else if(outparamerters[i] instanceof Integer){
						call.registerOutParameter(inparamerters.length+1+i, Types.INTEGER);
					}  else if(outparamerters[i] instanceof Double){
						call.registerOutParameter(inparamerters.length+1+i, Types.NUMERIC);
					} else if(outparamerters[i] instanceof Date){
						call.registerOutParameter(inparamerters.length+1+i, Types.DATE);
					}
				}
			}
		}
		catch (SQLException e){
			log.info(e.getMessage());
		    throw new RuntimeException(e.getMessage(), e);
		}
		return call;
	}
	
	public static void crudCall(String sql,Object[] paramerters){
		Connection conn = getConnection();
		try{
			CallableStatement call = conn.prepareCall(sql);
			if(paramerters!=null){
				for (int i = 0; i < paramerters.length; i++){
					call.setObject(i + 1, paramerters[i]);
				}
			}
			call.execute();
		}
		catch (SQLException e){
			log.info(e.getMessage());
		    throw new RuntimeException(e.getMessage(), e);
		}
	}
	/**
	 * 关闭相关对象
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void close(Statement st,ResultSet rs) {
		try{
			if(rs!=null){
				rs.close();
			}
		}catch(Exception e){
			log.info(e.getMessage());
		    throw new RuntimeException(e.getMessage(), e);
		}
		try{
			if(st!=null)
				st.close();
		}catch(Exception e){
			log.info(e.getMessage());
		    throw new RuntimeException(e.getMessage(), e);
		}
	}
}
