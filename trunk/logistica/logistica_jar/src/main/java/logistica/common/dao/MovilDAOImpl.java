package logistica.common.dao;

import java.util.ArrayList;
import java.util.List;

import logistica.model.Movil;
import logistica.query.MovilQuery;
import logistica.type.EstadoEnum;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
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
		criteria.add(Restrictions.eq("estado", EstadoEnum.ACTIVO));
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

	@Override
	public List<String> getListNames() {
		List<String> list = new ArrayList<String>();
		list.add("patcomList");
		// list.add("movilList");

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getListQuery(Object query) throws DataAccessException {
		List<Object[]> resumenReport = new ArrayList<Object[]>();
		// List<ResumenGeneralReport> list = new
		// ArrayList<ResumenGeneralReport>();
		String queryString = "select m.asignacionMovil, count(*) from logistica.model.Movil m group by m.asignacionMovil";
		resumenReport = getHibernateTemplate().find(queryString);
		return resumenReport;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getList2Query(Object query)
			throws DataAccessException {
		List<Object[]> resumenReport = new ArrayList<Object[]>();
		// List<ResumenGeneralReport> list = new
		// ArrayList<ResumenGeneralReport>();
		String queryString = "select m.asignacionMovil , m.numeroMovil, m.patente, coalesce(c1.nombre,'') , coalesce(c1.dni,'') , coalesce(c2.nombre,'') , coalesce(c2.dni,'') , m.comunicacion "
				+ " from logistica.model.Movil m  left join m.chofer1 as c1 left join m.chofer2 as c2"
				// +
				// " group by  m.asignacionMovil, m.numeroMovil, m.patente, m.chofer1.nombre, m.chofer1.dni, m.chofer2.nombre, m.chofer2.dni, m.comunicacion  "
				+ " group by  m.asignacionMovil, m.numeroMovil, m.patente, coalesce(c1.nombre,'') , coalesce(c1.dni,'') , coalesce(c2.nombre,'') , coalesce(c2.dni,'') , m.comunicacion "
				+ " order by m.asignacionMovil";
		resumenReport = getHibernateTemplate().find(queryString);
		return resumenReport;
	}

	public Long count(Object object) throws DataAccessException {
		Long count = 0l;

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass());
		criteria.setProjection(Projections.rowCount());

		if (object instanceof Long) {
			try {
				Long numero = Long.parseLong(object.toString());
				criteria.add(Restrictions.eq("numeroMovil", numero));
				criteria.add(Restrictions.eq("estado", EstadoEnum.ACTIVO));
			} catch (NumberFormatException e) {
			}
		}

		count = (Long) getHibernateTemplate().findByCriteria(criteria).get(0);

		return count;
	}

	public Long count(Object object, Object param) throws DataAccessException {
		Long count = 0l;

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass());
		criteria.setProjection(Projections.rowCount());

		if (object instanceof Long) {
			try {
				Long numero = Long.parseLong(object.toString());
				criteria.add(Restrictions.eq("numeroMovil", numero));
				criteria.add(Restrictions.eq("estado", EstadoEnum.ACTIVO));
				criteria.add(Restrictions.not(Restrictions.eq("id",
						Long.parseLong(param.toString()))));
			} catch (NumberFormatException e) {
			}
		}

		count = (Long) getHibernateTemplate().findByCriteria(criteria).get(0);

		return count;
	}

	public Long count(String object) throws DataAccessException {
		Long count = 0l;

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass());
		criteria.setProjection(Projections.rowCount());

		criteria.add(Restrictions.eq("patente", object));
		criteria.add(Restrictions.eq("estado", EstadoEnum.ACTIVO));

		count = (Long) getHibernateTemplate().findByCriteria(criteria).get(0);

		return count;
	}

	public Long count(String object, Object param) throws DataAccessException {
		Long count = 0l;

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass());
		criteria.setProjection(Projections.rowCount());

		criteria.add(Restrictions.eq("patente", object));
		criteria.add(Restrictions.eq("estado", EstadoEnum.ACTIVO));
		criteria.add(Restrictions.not(Restrictions.eq("id",
				Long.parseLong(param.toString()))));

		count = (Long) getHibernateTemplate().findByCriteria(criteria).get(0);

		return count;
	}

	// @SuppressWarnings("serial")
	/*
	 * public Movil edit(Movil object) throws DataAccessException { if (object
	 * != null) { if (count(object.getNumeroMovil()) != 1 ||
	 * count(object.getPatente()) != 1) { throw new DataAccessException(
	 * "Ya existe un movil activo con el número " + object.getNumeroMovil() +
	 * " o Ya existe un movil con el número de patente " + object.getPatente())
	 * { }; } else if(count(object.getPatente()) != 1){ throw new
	 * DataAccessException( "Ya existe un movil con el número de patente " +
	 * object.getPatente()) { };
	 * 
	 * } else{ getHibernateTemplate().merge(object); }
	 * 
	 * } } return object; }
	 */
	@SuppressWarnings("serial")
	public Movil edit(Movil object) throws DataAccessException {
		if (object != null) {
			if (object.getEstado() == EstadoEnum.ACTIVO) {
				if (count(object.getNumeroMovil(), object.getID()) != 0
						|| count(object.getPatente(), object.getID()) != 0) {
					throw new DataAccessException(
							"Ya existe un movil activo con el número "
									+ object.getNumeroMovil()
									+ " o Ya existe un movil con el número de patente "
									+ object.getPatente()) {
					};
				}
			}
			getHibernateTemplate().merge(object);
		}
		return object;
	}

}
