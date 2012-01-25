package logistica.common.dao;

import java.util.ArrayList;
import java.util.List;

import logistica.model.DiagramacionDiaria;
import logistica.query.DiagramacionDiariaQuery;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;

public class DiagramacionDiariaDAOImpl extends
		BaseHibernateDAO<DiagramacionDiaria, DiagramacionDiariaQuery> {

	@Override
	public Class<DiagramacionDiaria> getModelClass() {
		return DiagramacionDiaria.class;
	}

	@Override
	public List<String> getListNames() {
		List<String> list = new ArrayList<String>();
		list.add("detalleSucursalList");
		return list;
	}

	@SuppressWarnings("unchecked")
	public DiagramacionDiaria get(String nombre) throws DataAccessException {
		DiagramacionDiaria diagramacionDiaria = null;
		DetachedCriteria criteria = DetachedCriteria
				.forClass(DiagramacionDiaria.class);
		criteria.addOrder(Order.desc("fecha"));
		List<DiagramacionDiaria> list = getHibernateTemplate().findByCriteria(
				criteria, 0, 1);

		if (list != null && list.size() > 0) {
			diagramacionDiaria = list.get(0);
		}

		return diagramacionDiaria;
	}
}
