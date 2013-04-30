package fr.la.juserright.dao;

import java.util.List;

public abstract interface ModelDAO<T> {
	
	void create(T _object);
	
	T read(T _object);
	
	List<T> readAll();
	
	void update(T _object);
	
	void delete(T _object);
	
}