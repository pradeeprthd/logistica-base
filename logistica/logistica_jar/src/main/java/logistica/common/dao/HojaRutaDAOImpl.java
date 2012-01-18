package logistica.common.dao;

import java.util.ArrayList;
import java.util.List;

import logistica.model.HojaRuta;
import logistica.query.HojaRutaQuery;

public class HojaRutaDAOImpl extends BaseHibernateDAO<HojaRuta, HojaRutaQuery> {

	@Override
	public Class<HojaRuta> getModelClass() {
		return HojaRuta.class;
	}
	
	@Override
	public List<String> getListNames() {
		List<String> list = new ArrayList<String>();
		list.add("detalleHojaRutaList");
		return list;
	}
}
