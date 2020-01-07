/**
 * 
 */
package com.m7md.couponSystemSpring.login;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author scary
 *
 */
@Component
public @Data class Session {

	public ClientLogin getLogin() {
		return login;
	}
	public void setLogin(ClientLogin login) {
		this.login = login;
	}
	public long getLastAccesed() {
		return lastAccesed;
	}
	public void setLastAccesed(long lastAccesed) {
		this.lastAccesed = lastAccesed;
	}
	private ClientLogin login;
	private long lastAccesed;

	
}
