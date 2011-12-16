package logistica.common.dao;

import logistica.model.Usuario;
import logistica.query.UsuarioQuery;

public class UsuarioDAOImpl extends BaseHibernateDAO<Usuario, UsuarioQuery> {

	@Override
	public Class<Usuario> getModelClass() {
		return Usuario.class;
	}
}
