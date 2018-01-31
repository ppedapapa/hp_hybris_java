package com.shaklee.promo;

/**
 * Basic interface for components that compose a promo, such as conditions,
 * actions, messages, etc. Used mostly for initialization.
 * 
 * @author Elli Albek
 */
public interface PromoComponent<T> {

	/**
	 * Cloning is required since those objects come from the IOC container, and
	 * specifying scope(private) will most likely cause bugs in the future.
	 * Limitation of spring over guice.
	 */
	PromoComponent<T> clone(RuleSet<T> promo);
}
