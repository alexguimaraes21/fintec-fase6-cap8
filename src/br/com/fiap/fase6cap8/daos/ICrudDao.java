package br.com.fiap.fase6cap8.daos;

import java.util.List;

public interface ICrudDao<T> {
	
	List<T> getAll();
	T getById(Long id);
	void save(T t);
	void delete(Long id);
}
