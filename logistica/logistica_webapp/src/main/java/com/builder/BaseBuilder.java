package com.builder;

import java.io.Serializable;

public interface BaseBuilder<V extends Serializable, M extends Serializable> {
	M toDomain(V view);

	V toView(M model);
}
