/**
 * 
 */
package com.m7md.couponSystemSpring.login;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couponSystem.classes.ClientType;
import com.couponSystem.classes.CouponSystemException;

/**
 * @author scary
 *
 */
@RestController
@RequestMapping("loginService")
public class LoginService {

	public static Map<String, Session> tokens = new HashMap<String, Session>();

	@Autowired
	private SystemService systemService;

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestParam String user, @RequestParam String password,
			@RequestParam String type) throws CouponSystemException {
		if (!type.equals("ADMINISTRATOR") && !type.equals("company") && !type.equals("CUSTOMER")) {
			return new ResponseEntity<String>("something went wrong with client type", HttpStatus.UNAUTHORIZED);
		}
		Session session = new Session();
		ClientLogin login = null;
		String token = UUID.randomUUID().toString();
		long lastAccessed = System.currentTimeMillis();
		try {
			login = systemService.login(user, password, ClientType.valueOf(type));
			session.setLogin(login);
			session.setLastAccesed(lastAccessed);
			tokens.put(token, session);
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping(path = "logout")
	public boolean logout(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession(false);
			session.invalidate();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
