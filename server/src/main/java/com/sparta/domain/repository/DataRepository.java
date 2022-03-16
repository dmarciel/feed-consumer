package com.sparta.domain.repository;

public interface DataRepository<T> {

    public void save(String key, T value);

    public int countByProvider(String provider);

	public void deleteAll();
}
