package com.shaklee.common.util.collections;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/**
 * Wrapper for a collection that needs lightweight transformation without
 * creating a new collection.
 * 
 * @author Elli Albek
 */
public abstract class AbstractCollectionTransformDecorator<F, T> extends AbstractCollection<T> {

	private final Collection<F> origin;

	protected AbstractCollectionTransformDecorator(Collection<F> origin) {
		this.origin = origin;
	}

	protected abstract T convert(F object);

	@Override
	public int size() {
		return origin.size();
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private final Iterator<F> it = origin.iterator();

			@Override
			public boolean hasNext() {
				return it.hasNext();
			}

			@Override
			public T next() {
				return convert(it.next());
			}

			@Override
			public void remove() {
				it.remove();
			}
		};
	}
}