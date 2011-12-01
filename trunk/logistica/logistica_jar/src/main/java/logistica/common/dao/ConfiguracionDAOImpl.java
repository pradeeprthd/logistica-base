package logistica.common.dao;

import logistica.model.Configuracion;
import logistica.query.BaseQuery;

public class ConfiguracionDAOImpl extends
		BaseHibernateDAO<Configuracion, BaseQuery> {

	public Class<Configuracion> getModelClass() {
		return Configuracion.class;
	}
}
