package fr.la.juserright.dao;

import java.sql.SQLException;
import java.util.List;

public abstract interface ModelDAO<T> {
	
	void create(T _object) throws SQLException;
	
	T read(T _object) throws SQLException;
	
	List<T> readAll() throws SQLException;
	
	void update(T _object) throws SQLException;
	
	void delete(T _object) throws SQLException;
	
}