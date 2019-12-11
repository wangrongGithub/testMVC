package wangrong.wr1.domain;

import javax.validation.constraints.NotNull;

import wangrong.wr1.validator.IsMobile;



public class LoginVal {
	
@NotNull
@IsMobile
String mobile;
String password;
public String getMobile() {
	return mobile;
}
public void setMobile(String mobile) {
	this.mobile = mobile;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	
	this.password = password;
}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return mobile;
}

}
