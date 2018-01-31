package com.shaklee.common.util;

import java.security.SecureRandom;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads a secure random lazily. On some boxes a secure random can take up to a
 * minute, and block server startup and unit tests.
 * 
 * This implementation provides a temporary random and loads the secure random
 * on a background thread. It is also loading lazily, to ensure optimal
 * performance.
 * 
 * This class is thread safe.
 * 
 * CREATE ONE INSTANCE OF THIS CLASS AND REUSE IT.
 * 
 * @author Elli Albek
 */
public class LazyLoadingSecureRandom {

	private static final Logger logger = LoggerFactory.getLogger(LazyLoadingSecureRandom.class);

	private static volatile int threadCounter = 0;

	private Random random;

	public synchronized Random getRandom() {
		if (random == null) {
			// no random, load a temporary one immediately and the final one in
			// the background.
			random = new Random();
			startSecureRandomLoaderThread();
		}
		return random;
	}

	private synchronized void setRandom(Random r) {
		random = r;
	}

	private void startSecureRandomLoaderThread() {
		Runnable r = new Runnable() {

			@Override
			public void run() {
				try {
					long time = System.currentTimeMillis();
					Random r = SecureRandom.getInstance("SHA1PRNG", "SUN");
					time = System.currentTimeMillis() - time;
					logger.info("Created a new secure random " + time + " ms");
					setRandom(r);
				} catch (Exception e) {
					logger.error("Cannot load secure random", e);
				}
			}
		};

		new Thread(r, "SecureRandomLoader_" + (threadCounter++)).start();
	}
}
