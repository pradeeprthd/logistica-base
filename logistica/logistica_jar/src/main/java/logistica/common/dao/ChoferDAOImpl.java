package logistica.common.dao;

import java.util.List;

import logistica.model.Chofer;
import logistica.query.ChoferQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

public class ChoferDAOImpl extends BaseHibernateDAO<Chofer, ChoferQuery> {

	@Override
	public Class<Chofer> getModelClass() {
		return Chofer.class;
	}

	@SuppressWarnings("unchecked")
	public List<Chofer> getList(String nombreOID) {
		List<Chofer> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Chofer.class);
		criteria.add(Restrictions.ilike("nombre", nombreOID, MatchMode.START));
		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	@SuppressWarnings("unchecked")
	public Chofer get(String nombre) throws DataAccessException {
		List<Chofer> list = null;
		Chofer chofer = new Chofer();
		chofer.setNombre(nombre);
		list = getHibernateTemplate().findByExample(chofer);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Chofer> getList(Object query) throws DataAccessException {
		ChoferQuery choferQuery = (ChoferQuery) query;
		List<Chofer> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Chofer.class);
		criteria.add(Restrictions.ilike("nombre", choferQuery.getNombre(),
				MatchMode.START));

		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
}
