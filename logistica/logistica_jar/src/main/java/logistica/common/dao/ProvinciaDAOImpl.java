package logistica.common.dao;

import java.util.List;

import logistica.model.Provincia;
import logistica.query.BaseQuery;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;

public class ProvinciaDAOImpl extends BaseHibernateDAO<Provincia, BaseQuery> {

	@Override
	public Class<Provincia> getModelClass() {
		return Provincia.class;
	}

	@SuppressWarnings("unchecked")
	public List<Provincia> getList() throws DataAccessException {
		Query query = getSession().createQuery("from Provincia");
		query.setCacheable(true);
		List<Provincia> objectList = query.list();

		// DetachedCriteria criteria =
		// DetachedCriteria.forClass(Provincia.class);
		// // criteria.getExecutableCriteria(getSession()).setCacheable(true);
		// HibernateTemplate ht = getHibernateTemplate();
		// ht.setCacheQueries(true);
		// List<Provincia> objectList = ht.findByCriteria(
		// criteria);
		return objectList;
	}
}
