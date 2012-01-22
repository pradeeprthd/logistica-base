package logistica.common.dao;

import java.util.ArrayList;
import java.util.List;

import logistica.model.DiagramacionDiaria;
import logistica.query.DiagramacionDiariaQuery;

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
		list.add("novedades");
		return list;
	}
}
