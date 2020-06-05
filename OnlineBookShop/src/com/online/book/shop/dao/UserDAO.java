package com.online.book.shop.dao;

import com.online.book.shop.to.UserTO;

public interface UserDAO {

	public UserTO verifyUser(String username, String password);
	public UserTO changePassword(UserTO uto, String password);
	public String searchPassword(String username, String email);
	public boolean registerUser(UserTO uto);
	public boolean alreadyExist(String username);
	public boolean updateUserInfo(String userId, String email, long phone);
	public UserTO getUserInfoById(String userId);
	
}
