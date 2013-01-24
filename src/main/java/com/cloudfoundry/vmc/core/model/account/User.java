package com.cloudfoundry.vmc.core.model.account;

public class User {
    private String target;
	private String email;
	private String passwd;

	public User() {
	}

	public User(String target, String email, String passwd) {
	    this.target = target;
		this.email = email;
		this.passwd = passwd;
	}

	public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
