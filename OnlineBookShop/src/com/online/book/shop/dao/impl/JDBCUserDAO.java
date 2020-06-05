package com.online.book.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.online.book.shop.dao.UserDAO;
import com.online.book.shop.to.UserTO;
import com.online.book.shop.util.JDBCUtil;

public class JDBCUserDAO implements UserDAO{
	
	Logger log = Logger.getLogger(this.getClass());
	public UserTO verifyUser(String username, String password) {
		
		UserTO uto = null;
		Connection con = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps= con.prepareStatement("select * from user_table inner join login_table using (userId) where login_table.username = ? and login_table.password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()){
				if(password.equals(rs.getString("password")) && (username.equals(rs.getString("username")))){
					String uid = rs.getString("userId");
					String fName = rs.getString("firstName");
					String mName = rs.getString("middleName");
					String lName = rs.getString("lastName");
					String email = rs.getString("email");
					long phone = rs.getLong("phone");
					String logId = rs.getString("loginId");
					String unm = rs.getString("username");
					String pwd = rs.getString("password");
					String role = rs.getString("role");
					//System.out.println("===========Role : "+role);
					uto = new UserTO(fName, mName, lName, email, phone, unm, pwd, role);
					uto.setUserId(uid);
					uto.setLoginId(logId);
				}
			}
		}catch(Exception e){
			uto = null;
			log.error("Exception in verifyUser()\t "+e);
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		return uto;
	}

	public UserTO changePassword(UserTO uto, String password) {
		
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps = con .prepareStatement("update login_table set password = ? where loginId=?");
			ps.setString(1, password);
			ps.setString(2, uto.getLoginId());
			int x = ps.executeUpdate();
			if(x>0){
				uto.setPassword(password);
			}
		}catch(Exception e){
			log.error("Exception in changePassword()\t : "+e);
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		
		return uto;
	}

	public String searchPassword(String username, String email) {
		
		String password = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps =null;
		try{
			con = JDBCUtil.getConnection();
			ps =con.prepareStatement("select * from user_table inner join login_table using (userId) where login_table.username=? and user_table.email=?");
			ps.setString(1, username);
			ps.setString(2, email);
			rs=ps.executeQuery();
			if(rs.next()){
				password = rs.getString("password");
			}
		}catch(Exception e ){
			log.error("Error in searchPassword()\t : "+e);
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		return password;
	}

	public boolean alreadyExist(String username) {
		
		boolean exist =false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement("select * from login_table where login_table.username=?");
			ps.setString(1, username);
			rs=ps.executeQuery();
			if(rs.next()){
				exist = true;
			}
		}catch(Exception e){
			exist =false;
			log.error("Exception in alreadyExist()\t : "+e);
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		return exist;
	}


	public boolean registerUser(UserTO uto) {
		
		boolean registered = false;
		Connection con = null;
		PreparedStatement ps =null;
		PreparedStatement ps2 =null;
		ResultSet rs = null;
		try{
			con=JDBCUtil.getConnection();
			con.setAutoCommit(false);
			ps = con.prepareStatement("insert into user_table values(?,?,?,?,?,?)");
			String userId = getUserId();
			ps.setString(1, userId);
			ps.setString(2, uto.getFirstName());
			ps.setString(3, uto.getMiddleName());
			ps.setString(5, uto.getEmail());
			ps.setString(4, uto.getLastName());
			ps.setLong(6, uto.getPhone());
			ps.executeUpdate();
			ps2= con.prepareStatement("insert into login_table values(?,?,?,?,?)");
			String loginId = getLoginId();
			ps2.setString(1, loginId);
			ps2.setString(2, userId);
			ps2.setString(3, uto.getUsername());
			ps2.setString(4, uto.getPassword());
			ps2.setString(5, uto.getRole());
			ps2.executeUpdate();
			con.commit();
			registered=true;
		}catch(Exception e){		
			registered = false;
			try{
				con.rollback();
			}catch(Exception e1){
				e1.printStackTrace();
				log.error("Error while rollbacking in registerUser : \t  "+e1);
			}
			log.error("Error in registerUser\t"+e);
			e.printStackTrace();
		}finally{
			JDBCUtil.close(rs, ps2, null);
			JDBCUtil.close(rs, ps, con);
		}
		
		return registered;
	}
	
	private String getUserId(){
		
		String userId = "";
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement("select max(userId) from user_table");
			rs=ps.executeQuery();
			if(rs.next()){
				String st = rs.getString(1);
				if(st != null){
					int id = Integer.parseInt(st.substring(6));
					id++;
					if(id<=9){
						userId = "BLR-U-000"+id;
					}else if(id<=99){
						userId = "BLR-U-00"+id;
					} else if(id<=999){
						userId = "BLR-U-0"+id;
					}else{
						userId = "BLR-U-"+id;
					}
				}else{
					userId = "BLR-U-0001";
				}
			}else{
				userId = "BLR-U-0001";
			}
		}catch(Exception e){
			log.error("Exception in getUserId()\t : "+e);
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		return userId;
	}
	
	private String getLoginId(){
		String loginId = "";
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement("select max(loginId) from login_table");
			rs= ps.executeQuery();
			if(rs.next()){
				String st = rs.getString(1);
				if(st != null){
					int id = Integer.parseInt(st.substring(6));
					id++;
					if(id<=9){
						loginId = "BLR-L-000"+id;
					}else if(id<=99){
						loginId = "BLR-L-00"+id;
					} else if(id<=999){
						loginId = "BLR-L-0"+id;
					}else{
						loginId = "BLR-L-"+id;
					}
				}else{
					loginId = "BLR-L-0001";
				}
			}else{
				loginId = "BLR-L-0001";
			}
		}catch(Exception e){
			log.error("Exception in getLoginId : \t"+e);
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		return loginId;
	}
	
	
	public boolean updateUserInfo(String userId, String email, long phone) {
		
		boolean updated = false;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps =con.prepareStatement("update user_table set email = ?, phone =? where userId=?");
			ps.setString(1, email);
			ps.setString(3, userId);
			ps.setLong(2, phone);
			ps.executeUpdate();
			updated =true;
		}catch(Exception e){
			log.error("Error in updateUserInfo()\t :"+e);
			updated=false;
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		return updated;
	}

	public UserTO getUserInfoById(String userId) {
		
		UserTO uto = null;
		Connection con = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		try{
			con = JDBCUtil.getConnection();
			ps = con.prepareStatement("select * from user_table where userId=?");
			ps.setString(1, userId);
			rs=ps.executeQuery();
			if(rs.next()){
				String uid = rs.getString("userId");
				String fName = rs.getString("firstName");
				String mName = rs.getString("middleName");
				String lName = rs.getString("lastName");
				String email = rs.getString("email");
				long phone = rs.getLong("phone");
				uto = new UserTO(fName, mName, lName, email, phone, null,null,null);
				uto.setUserId(uid);
			}
		}catch(Exception e){
			uto=null;
			log.error("Error in getUserInfobyId()\t : "+e);
		}finally{
			JDBCUtil.close(rs, ps, con);
		}
		return uto;
	}

}
