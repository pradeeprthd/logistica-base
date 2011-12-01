package logistica.common.dao;

import java.util.List;

import logistica.common.BaseModel;
import logistica.query.BaseQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseHibernateDAO<T extends BaseModel, Q extends BaseQuery>
		extends HibernateDaoSupport implements BaseModelDAO<T, Q> {

	public abstract Class<T> getModelClass();

	public T find(Long id) throws DataAccessException {
		T object = getHibernateTemplate().get(getModelClass(), id);
		return object;
	}

	@SuppressWarnings("unchecked")
	public List<T> getList() throws DataAccessException {
		List<T> objectList = getHibernateTemplate().find(
				"FROM " + getModelClass().getSimpleName());
		return objectList;
	}

	public T save(T object) throws DataAccessException {
		if (object != null) {
			getHibernateTemplate().save(object);
		}
		return object;
	}

	public T edit(T object) throws DataAccessException {
		if (object != null) {
			getHibernateTemplate().merge(object);
		}
		return object;
	}

	public void delete(T object) throws DataAccessException {
		getHibernateTemplate().delete(object);
	}

	public void saveList(List<T> objectList) throws DataAccessException {

		for (T object : objectList) {
			save(object);
		}
	}

	public List<T> getList(String nombreOID) {
		// TODO implementar dende sea necesario
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<T> getList(int first, int pageSize) throws DataAccessException {
		List<T> objectList = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass());

		objectList = getHibernateTemplate().findByCriteria(criteria, first,
				pageSize);

		return objectList;
	}

	public Long count() throws DataAccessException {
		Long count = 0l;

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass());
		criteria.setProjection(Projections.rowCount());
		count = (Long) getHibernateTemplate().findByCriteria(criteria).get(0);

		return count;
	}

	public T get(String query) throws DataAccessException {
		// TODO implementar donde sea necesario
		return null;
	}

	public List<T> getList(Q query) throws DataAccessException {
		// TODO implementar donde sea necesario
		return null;
	}
}