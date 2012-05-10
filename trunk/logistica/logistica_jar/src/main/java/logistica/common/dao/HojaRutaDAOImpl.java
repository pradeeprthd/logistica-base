package logistica.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logistica.model.HojaRuta;
import logistica.query.HojaRutaQuery;
import logistica.util.DateUtil;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;

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

	@SuppressWarnings("unchecked")
	public List<HojaRuta> getList(Object query) throws DataAccessException {
		List<HojaRuta> list = new ArrayList<HojaRuta>();
		HojaRutaQuery hojaRutaQuery = (HojaRutaQuery) query;

		DetachedCriteria criteria = DetachedCriteria.forClass(getModelClass());
		criteria.addOrder(Order.desc("fechaEmision"));
		if (hojaRutaQuery.getFechaEmision() != null) {
			criteria.add(Restrictions.between("fechaEmision",
					DateUtil.getFirstTime(hojaRutaQuery.getFechaEmision()),
					DateUtil.getLastTime(hojaRutaQuery.getFechaEmision())));
		}
		if (hojaRutaQuery.getPrefijo() != null
				&& hojaRutaQuery.getPrefijo() != 0) {
			criteria.add(Restrictions.eq("prefijo", hojaRutaQuery.getPrefijo()));
		}
		if (hojaRutaQuery.getNumero() != null && hojaRutaQuery.getNumero() != 0) {
			criteria.add(Restrictions.eq("numero", hojaRutaQuery.getNumero()));
		}
		if (hojaRutaQuery.getEstadoHojaRutaEnum() != null) {
			criteria.add(Restrictions.eq("estadoHojaRutaEnum",
					hojaRutaQuery.getEstadoHojaRutaEnum()));
		}
		// la idea es que traiga el último mes como mucho
		criteria.add(Restrictions.gt("fechaEmision",
				DateUtil.getSumarRestarDias(new Date(), -30)));

		list = getHibernateTemplate().findByCriteria(criteria);

		return list;
	}
}
