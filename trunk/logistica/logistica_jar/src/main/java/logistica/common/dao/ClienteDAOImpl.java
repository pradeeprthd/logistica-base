package logistica.common.dao;

import java.util.List;

import logistica.model.Cliente;
import logistica.query.ClienteQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

public class ClienteDAOImpl extends BaseHibernateDAO<Cliente, ClienteQuery> {
	public Class<Cliente> getModelClass() {

		return Cliente.class;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getList(String nombreOID) throws DataAccessException {
		List<Cliente> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Cliente.class);
		criteria.add(Restrictions.or(
				Restrictions.ilike("nombre", "%" + nombreOID + "%"),
				Restrictions.ilike("numeroTarjeta", "%" + nombreOID + "%")));
		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	@SuppressWarnings("unchecked")
	public Cliente get(String nombre) throws DataAccessException {
		List<Cliente> list = null;
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		list = getHibernateTemplate().findByExample(cliente);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public List<Cliente> getList(ClienteQuery query) {
		// TODO Auto-generated method stub
		return null;
	}
}
