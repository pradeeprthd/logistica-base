package logistica.common.dao;

import java.util.ArrayList;
import java.util.List;

import logistica.model.Usuario;
import logistica.query.UsuarioQuery;

public class UsuarioDAOImpl extends BaseHibernateDAO<Usuario, UsuarioQuery> {

	@Override
	public Class<Usuario> getModelClass() {
		return Usuario.class;
	}

	@Override
	public List<String> getListNames() {
		List<String> list = new ArrayList<String>();
		list.add("rolEnumList");
		return list;
	}
}
