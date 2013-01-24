package com.cloudfoundry.vmc.common;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.cloudfoundry.client.lib.CloudCredentials;
import org.cloudfoundry.client.lib.CloudFoundryClient;

import com.cloudfoundry.vmc.CloudConstants;
import com.cloudfoundry.vmc.core.model.account.User;

public final class Session {

    /** 20 min */
    private static final long EXPIRY = 15 * 60 * 1000 * 1000L;
	private static Session session;
	private static CloudFoundryClient client;
	private Map<String, Object> map;

	private Session() {
		map = new HashMap<String, Object>();
	}

	public static Session getInstance() {
		if (session == null) {
			session = new Session();
		}
		return session;
	}
	
	//-------------------------------------------------
	public void put(String s, Object o) {
		map.put(s, o);
	}
	
	public Object get(String s) {
		return map.get(s);
	}
	
	public void clear() {
		map.clear();
	}
	public boolean isEmpty() {
		return map.isEmpty();
	}
	//-------------------------------------------------
	public boolean isExpiry() {
	    return System.currentTimeMillis() - Long.valueOf(map.get(CloudConstants.session.EXPIRY).toString()) >= EXPIRY;
	}
	
	/**
	 * get cf client
	 * @param flag
	 *     if expiry<br>
	 *         <code>true</code> : need relogin<br>
	 *         <code>false</code> : do nothing
	 * @return auth client
	 */
	public CloudFoundryClient getClient() {
	    if (isExpiry()) {
    	    User user = (User)map.get(CloudConstants.session.USER);
    	    try {
                client = new CloudFoundryClient(
                        new CloudCredentials(user.getEmail(), user.getPasswd()), new URL(user.getTarget()));
            } catch (MalformedURLException e) {
                //quiet
            }
	    }
        return client;
	}
	
	//-------------------------------------------------
	public boolean getBoolean(String s) {
	    return Boolean.valueOf(String.valueOf(get(s)));
	}

    public static void setClient(CloudFoundryClient client) {
        Session.client = client;
    }
}
