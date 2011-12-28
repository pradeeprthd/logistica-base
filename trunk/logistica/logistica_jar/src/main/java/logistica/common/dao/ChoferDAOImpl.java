package logistica.common.dao;

import logistica.model.Chofer;
import logistica.query.ChoferQuery;

public class ChoferDAOImpl extends BaseHibernateDAO<Chofer, ChoferQuery> {

	@Override
	public Class<Chofer> getModelClass() {
		return Chofer.class;
	}
}
