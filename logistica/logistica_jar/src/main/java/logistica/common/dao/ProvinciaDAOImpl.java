package logistica.common.dao;

import logistica.model.Provincia;
import logistica.query.BaseQuery;

public class ProvinciaDAOImpl extends BaseHibernateDAO<Provincia, BaseQuery> {

	@Override
	public Class<Provincia> getModelClass() {
		return Provincia.class;
	}
}
