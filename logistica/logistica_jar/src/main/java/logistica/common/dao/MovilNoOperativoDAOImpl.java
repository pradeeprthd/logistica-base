package logistica.common.dao;

import java.util.List;

import logistica.model.MovilNoOperativo;
import logistica.query.MovilNoOperativoQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

public class MovilNoOperativoDAOImpl extends
		BaseHibernateDAO<MovilNoOperativo, MovilNoOperativoQuery> {

	@Override
	public Class<MovilNoOperativo> getModelClass() {
		return MovilNoOperativo.class;
	}

	@SuppressWarnings("unchecked")
	public List<MovilNoOperativo> getList(Object query)
			throws DataAccessException {
		MovilNoOperativoQuery movilNoOperativoQuery = (MovilNoOperativoQuery) query;

		List<MovilNoOperativo> list = null;

		DetachedCriteria criteria = DetachedCriteria
				.forClass(MovilNoOperativo.class);
		criteria.add(Restrictions.or(
				Restrictions.or(
						Restrictions.eq("fechaDesde",
								movilNoOperativoQuery.getFecha()),
						Restrictions.eq("fechaHasta",
								movilNoOperativoQuery.getFecha())),
				Restrictions.and(
						Restrictions.lt("fechaDesde",
								movilNoOperativoQuery.getFecha()),
						Restrictions.gt("fechaHasta",
								movilNoOperativoQuery.getFecha()))));

		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
}
