package com.sparta.domain.repository;

public interface DataRepository<T> {

    public void save(String key, T value);

    public long countByProvider(String provider);
}
