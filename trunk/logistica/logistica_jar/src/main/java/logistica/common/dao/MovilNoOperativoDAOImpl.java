package logistica.common.dao;

import logistica.model.MovilNoOperativo;
import logistica.query.MovilNoOperativoQuery;

public class MovilNoOperativoDAOImpl extends
		BaseHibernateDAO<MovilNoOperativo, MovilNoOperativoQuery> {

	@Override
	public Class<MovilNoOperativo> getModelClass() {
		return MovilNoOperativo.class;
	}
}
