package com.tutorialspoint.model;


import javax.servlet.http.HttpServletRequest;

import com.tutorialspoint.MYSQLDBCP;

public class User {
	protected HttpServletRequest mHttpServletRequest;
	protected String mUserId;
	protected String mUserName;
	protected String mPassword;
	protected String mLocation;
	protected String mBirthday;
	protected String mJoinedDate;
	protected String mLastOnline;
	protected String mAboutMe;
	
	public User(){
		
	}
	
	public User(HttpServletRequest request) {
		mHttpServletRequest = request;
		mUserName = request.getParameter("userName");
		mPassword = request.getParameter("password");
	}

	public boolean authenticate() {
		if(mUserName != null && mPassword != null) {
			MYSQLDBCP mysqlDBCP = new MYSQLDBCP();
			UserInfoTable userInfoTable = new UserInfoTable(mysqlDBCP.getConnection());
			boolean isUserValid = userInfoTable.authenticate(mUserName, mPassword);
			userInfoTable.close();
			if(isUserValid) {
				return true;
			}
			else {
				return false;
			}
				//return true;
				
		}
		else {
			return false;
		}
	}
	public User getUser(){
		
		return this;
	}
	/**
	 * put username into the session data to mark it as logged user
	 */
	public void setAsTrusted() {
		mHttpServletRequest.getSession().setAttribute("userName", mUserName);
	}
	
	public String getUserName() {
		return mUserName;
	}
}

