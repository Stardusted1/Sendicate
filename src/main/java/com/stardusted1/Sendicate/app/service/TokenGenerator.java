package com.stardusted1.Sendicate.app.service;

import java.security.SecureRandom;

public class TokenGenerator {
	protected static SecureRandom random = new SecureRandom();

	public static synchronized String generateToken(String type) {
		long longToken = Math.abs(random.nextLong());
		return type + "_" + Long.toString(longToken, 18);
	}
}
