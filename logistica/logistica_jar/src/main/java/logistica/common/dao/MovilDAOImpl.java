package logistica.common.dao;

import java.util.List;

import logistica.model.Movil;
import logistica.query.MovilQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

public class MovilDAOImpl extends BaseHibernateDAO<Movil, MovilQuery> {

	@Override
	public Class<Movil> getModelClass() {
		return Movil.class;
	}

	@SuppressWarnings("unchecked")
	public List<Movil> getList(String nombreOID) {
		List<Movil> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Movil.class);
		criteria.add(Restrictions.or(
				Restrictions.ilike("patente", "%" + nombreOID + "%"),
				Restrictions.eq("Movil", nombreOID)));
		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	@SuppressWarnings("unchecked")
	public Movil get(String patente) throws DataAccessException {
		List<Movil> list = null;
		Movil movil = new Movil();
		movil.setPatente(patente);
		list = getHibernateTemplate().findByExample(movil);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Movil> getList(Object query) throws DataAccessException {
		MovilQuery movilQuery = (MovilQuery) query;
		List<Movil> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Movil.class);
		if (movilQuery.getNumeroMovil() != null) {
			criteria.add(Restrictions.or(Restrictions.eq("numeroMovil",
					movilQuery.getNumeroMovil()), Restrictions.ilike("patente",
					movilQuery.getPatente(), MatchMode.START)));
		} else {
			criteria.add(Restrictions.ilike("patente", movilQuery.getPatente(),
					MatchMode.START));
		}

		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
}
