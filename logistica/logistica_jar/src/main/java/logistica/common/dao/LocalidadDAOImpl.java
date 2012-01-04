package logistica.common.dao;

import java.util.List;

import logistica.model.Localidad;
import logistica.query.LocalidadQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

public class LocalidadDAOImpl extends
		BaseHibernateDAO<Localidad, LocalidadQuery> {

	@Override
	public Class<Localidad> getModelClass() {
		return Localidad.class;
	}

	@SuppressWarnings("unchecked")
	public List<Localidad> getList(String nombreOID) throws DataAccessException {
		List<Localidad> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Localidad.class);
		criteria.add(Restrictions.ilike("descripcion", nombreOID,
				MatchMode.START));
		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	@SuppressWarnings("unchecked")
	public Localidad get(String nombre) throws DataAccessException {
		List<Localidad> list = null;
		Localidad localidad = new Localidad();
		localidad.setDescripcion(nombre);
		list = getHibernateTemplate().findByExample(localidad);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Localidad> getList(Object query) throws DataAccessException {
		LocalidadQuery localidadQuery = (LocalidadQuery) query;
		List<Localidad> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Localidad.class);
		criteria.add(Restrictions.ilike("descripcion",
				localidadQuery.getDescripcion(), MatchMode.START));
		criteria.add(Restrictions.eq("provincia.id",
				localidadQuery.getProvinciaID()));

		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
}
