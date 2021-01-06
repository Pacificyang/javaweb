package cn.pacificy.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import cn.pacificy.utils.JDBCUtils;
import cn.pacificy.utils.JDBCUtils2;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.pacificy.domain.User;

import javax.sql.DataSource;

public class UserDao {


	public User findUserByUsername1(String username) {
		User user = null;
		System.out.println("qr start");
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		if(qr == null){
			System.out.println("qr is null!");
		}
		try {
			user = qr.query("select * from user where name = ?", new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public User findUserByUsername(String username){
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			// 使用连接池：
			// 从属性文件中获取：
//			Properties properties = new Properties();
//			properties.load(new FileInputStream("/Users/Pacificy/IdeaProjects/javaweb/src/druid.properties"));
//			DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
//			// 获得连接：
////
//			conn = dataSource.getConnection();
			conn = JDBCUtils2.getConnection();
			// 编写SQL:
			String sql = "select * from user";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			// 执行SQL:
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name")+" "+rs.getString("password"));
				if(rs.getString("name").equals(username)){
					user = new User();
					user.setUsername(rs.getString("name"));
					user.setPassword(rs.getString("password"));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils2.release(rs, pstmt, conn);
		}
		return user;
	}

	public boolean addUser(String username,String password){
		//User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = JDBCUtils2.getConnection();
			// 编写SQL:
			String sql = "insert into user values(null,?,?)";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			pstmt.setString(1,username);
			pstmt.setString(2,password);
			// 执行SQL:
			pstmt.executeUpdate();

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			JDBCUtils2.release(rs, pstmt, conn);
		}
		return true;
	}

	public boolean login(String username, String password) {
		User user = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{

			conn = JDBCUtils2.getConnection();
			// 编写SQL:
			String sql = "select * from user";
			// 预编译SQL:
			pstmt = conn.prepareStatement(sql);
			// 设置参数:
			// 执行SQL:
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("name")+" "+rs.getString("password"));
				if(rs.getString("name").equals(username) && rs.getString("password").equals(password)){
					user = new User();
					user.setUsername(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					return true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtils2.release(rs, pstmt, conn);
		}
		return false;
	}
}
