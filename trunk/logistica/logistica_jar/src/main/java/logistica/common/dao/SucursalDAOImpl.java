package logistica.common.dao;

import java.util.List;

import logistica.model.Sucursal;
import logistica.query.SucursalQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

public class SucursalDAOImpl extends BaseHibernateDAO<Sucursal, SucursalQuery> {

	@Override
	public Class<Sucursal> getModelClass() {
		return Sucursal.class;
	}

	@SuppressWarnings("unchecked")
	public List<Sucursal> getList(String nombreOID) {
		List<Sucursal> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Sucursal.class);
		criteria.add(Restrictions.or(
				Restrictions.ilike("nombre", "%" + nombreOID + "%"),
				Restrictions.ilike("numeroSucursal", "%" + nombreOID + "%")));
		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	@SuppressWarnings("unchecked")
	public Sucursal get(String nombre) throws DataAccessException {
		List<Sucursal> list = null;
		Sucursal sucursal = new Sucursal();
		sucursal.setNombre(nombre);
		list = getHibernateTemplate().findByExample(sucursal);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Sucursal> getList(Object query) throws DataAccessException {
		SucursalQuery sucursalQuery = (SucursalQuery) query;
		List<Sucursal> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Sucursal.class);
		criteria.add(Restrictions.ilike("nombre", sucursalQuery.getNombre(),
				MatchMode.START));
		if (sucursalQuery.getNumeroSucursal() != null) {
			criteria.add(Restrictions.eq("numeroSucursal",
					sucursalQuery.getNumeroSucursal()));
		}

		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}
}
