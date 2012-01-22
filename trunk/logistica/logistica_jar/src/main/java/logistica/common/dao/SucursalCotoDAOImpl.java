package logistica.common.dao;

import logistica.model.SucursalCoto;
import logistica.query.SucursalCotoQuery;

public class SucursalCotoDAOImpl extends
		BaseHibernateDAO<SucursalCoto, SucursalCotoQuery> {

	@Override
	public Class<SucursalCoto> getModelClass() {
		return SucursalCoto.class;
	}
}
