package logistica.common.dao;

import java.util.List;

import logistica.model.HistorialMovilChofer;
import logistica.model.Movil;
import logistica.query.BaseQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

public class HistorialMovilChoferDAOImpl extends
		BaseHibernateDAO<HistorialMovilChofer, BaseQuery> {

	@Override
	public Class<HistorialMovilChofer> getModelClass() {
		return HistorialMovilChofer.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HistorialMovilChofer> getList(Object query)
			throws DataAccessException {
		Movil movil = (Movil) query;
		List<HistorialMovilChofer> list = null;

		DetachedCriteria criteria = DetachedCriteria
				.forClass(HistorialMovilChofer.class);

		if (movil != null && movil.getID() != null) {
			criteria.add(Restrictions.eq("movil.id", movil.getID()));
			list = getHibernateTemplate().findByCriteria(criteria);
		}

		return list;
	}
}
