package com.shaklee.promo.basic;

import org.springframework.stereotype.Component;

/**
 * Message object is an action. This implementation can be loaded from DB.
 * 
 * @author Elli Albek
 */
@Component
public class Message<T> extends MessageBase<T> {
	private static final long serialVersionUID = 123L;
}