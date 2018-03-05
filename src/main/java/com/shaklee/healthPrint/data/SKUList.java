package com.shaklee.healthPrint.data;

import java.util.Collection;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * A list of SKUs that creates a bundle. This used to be a simple generic type,
 * however issues with Jackson schema generator for documentation require
 * creating a strongly typed actual subclass. This is fixed in jackson 2.* where
 * the package name changed from codehouse to fasterxml.
 * 
 * @author Elli Albek
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public abstract class SKUList<BundleType, ItemsType> {

	//TODO: Keep bundle here
	// public BundleType bundle;
	public Collection<ItemsType> skus;

	protected abstract Collection<ItemsType> getSkus();

	protected abstract void setSkus(Collection<ItemsType> skus);

	public abstract BundleType getBundle();

	public abstract void setBundle(BundleType bundle);
}