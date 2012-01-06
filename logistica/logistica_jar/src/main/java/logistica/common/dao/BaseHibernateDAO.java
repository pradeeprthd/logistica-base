package logistica.common.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import logistica.common.BaseModel;
import logistica.query.BaseQuery;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class BaseHibernateDAO<T extends BaseModel, Q extends BaseQuery>
		extends HibernateDaoSupport implements BaseModelDAO<T> {

	public abstract Class<T> getModelClass();

	public T find(Long id) throws DataAccessException {
		T object = getHibernateTemplate().get(getModelClass(), id);
		return object;
	}

	@SuppressWarnings({ "unchecked" })
	public T findFULL(Long id) throws DataAccessException {

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass())
				.add(Restrictions.eq("id", id));

		for (String listName : getListNames()) {
			criteria.setFetchMode(listName, FetchMode.JOIN);
		}

		T object = (T) getHibernateTemplate().findByCriteria(criteria).get(0);
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
	public List<T> getList(int first, int pageSize, String sortField,
			boolean sortOrder, Map<String, String> filters)
			throws DataAccessException {
		List<T> objectList = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass());

		if (sortField != null && !sortField.isEmpty()) {
			if (sortOrder) {
				criteria.addOrder(Order.asc(sortField));
			} else {
				criteria.addOrder(Order.desc(sortField));
			}
		}

		if (!filters.isEmpty()) {
			Iterator<Entry<String, String>> iterator = filters.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				criteria.add(Restrictions.ilike(entry.getKey(),
						entry.getValue(), MatchMode.START));
			}
		}

		objectList = getHibernateTemplate().findByCriteria(criteria, first,
				pageSize);

		return objectList;
	}

	public Long count(Map<String, String> filters) throws DataAccessException {
		Long count = 0l;

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass());
		criteria.setProjection(Projections.rowCount());
		if (!filters.isEmpty()) {
			Iterator<Entry<String, String>> iterator = filters.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> entry = iterator.next();
				criteria.add(Restrictions.ilike(entry.getKey(),
						entry.getValue(), MatchMode.START));
			}
		}
		count = (Long) getHibernateTemplate().findByCriteria(criteria).get(0);

		return count;
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

	public List<String> getListNames() {
		return null;
	}

	public List<T> getList(Object query) throws DataAccessException {
		// TODO implementar donde sea necesario
		return null;
	}
}