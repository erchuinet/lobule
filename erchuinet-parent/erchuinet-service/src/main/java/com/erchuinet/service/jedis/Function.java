package com.erchuinet.service.jedis;

public interface Function<T, E> {
	public T callback(E e);
}
