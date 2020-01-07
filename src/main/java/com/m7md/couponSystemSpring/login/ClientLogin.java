/**
 * 
 */
package com.m7md.couponSystemSpring.login;

import com.couponSystem.classes.ClientType;

/**
 * @author scary
 *
 */
public interface ClientLogin {
	public ClientLogin login(String name, String password, ClientType type);

}
