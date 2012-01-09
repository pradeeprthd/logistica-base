package com.builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public abstract class BaseBuilder<V extends Serializable, M extends Serializable> implements
Serializable {
	public abstract M toDomain(V view);

	public abstract V toView(M model);

	public List<M> toDomain(List<V> listView) {

		List<M> list = new ArrayList<M>();

		for (V v : listView) {
			list.add(toDomain(v));
		}

		return list;
	}

	public List<V> toView(List<M> listModel) {

		List<V> list = new ArrayList<V>();

		for (M m : listModel) {
			list.add(toView(m));
		}

		return list;
	}
}
