package logistica.common.dao;

import logistica.model.HojaRuta;
import logistica.query.HojaRutaQuery;

public class HojaRutaDAOImpl extends BaseHibernateDAO<HojaRuta, HojaRutaQuery> {

	@Override
	public Class<HojaRuta> getModelClass() {
		return HojaRuta.class;
	}
}
