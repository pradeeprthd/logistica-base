package logistica.common.dao;

import java.util.List;
import java.util.Map;

import logistica.common.BaseModel;

import org.springframework.dao.DataAccessException;

public interface BaseModelDAO<T extends BaseModel> {

	T find(Long id) throws DataAccessException;

	T findFULL(Long id) throws DataAccessException;

	List<T> getList() throws DataAccessException;

	T save(T object) throws DataAccessException;

	void saveList(List<T> objectList) throws DataAccessException;

	T edit(T object) throws DataAccessException;

	void delete(T object) throws DataAccessException;

	List<T> getList(String query) throws DataAccessException;

	T get(String query) throws DataAccessException;

	List<T> getList(Object query) throws DataAccessException;

	Long count() throws DataAccessException;

	public Long count(Map<String, String> filters) throws DataAccessException;

	List<T> getList(int first, int pageSize, String sortField,
			boolean sortOrder, Map<String, String> filters)
			throws DataAccessException;
}
