package logistica.common.dao;

import java.util.List;

import logistica.common.BaseModel;
import logistica.query.BaseQuery;

import org.springframework.dao.DataAccessException;

public interface BaseModelDAO<T extends BaseModel, Q extends BaseQuery> {

	T find(Long id) throws DataAccessException;

	List<T> getList() throws DataAccessException;

	T save(T object) throws DataAccessException;

	void saveList(List<T> objectList) throws DataAccessException;

	T edit(T object) throws DataAccessException;

	void delete(T object) throws DataAccessException;

	List<T> getList(String query) throws DataAccessException;

	T get(String query) throws DataAccessException;

	public List<T> getList(Q query) throws DataAccessException;

	Long count() throws DataAccessException;

	List<T> getList(int first, int pageSize) throws DataAccessException;
}
