package com.shaklee.promo.util;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E> {

	int last = 0;
	private final E[] array;
	
	public ArrayIterator(E[] array) {
		this.array = array;
	}

	@Override
	public boolean hasNext() {
		return last < array.length;
	}

	@Override
	public E next() {
		return array[last++];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("The array is read only");
	}

}
