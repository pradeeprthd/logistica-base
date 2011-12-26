package logistica.common.dao;

import logistica.model.Proveedor;
import logistica.query.ProveedorQuery;

public class ProveedorDAOImpl extends
		BaseHibernateDAO<Proveedor, ProveedorQuery> {

	@Override
	public Class<Proveedor> getModelClass() {
		return Proveedor.class;
	}
}
