package logistica.common.dao;

import logistica.model.OtrosServicios;
import logistica.query.OtrosServiciosQuery;

public class OtrosServiciosDAOImpl extends
		BaseHibernateDAO<OtrosServicios, OtrosServiciosQuery> {

	@Override
	public Class<OtrosServicios> getModelClass() {
		return OtrosServicios.class;
	}
}
