package logistica.common.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logistica.model.Propietario;
import logistica.query.PropietarioQuery;
import logistica.util.FilesystemUtil;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;
import org.springframework.dao.DataAccessException;

public class PropietarioDAOImpl extends
		BaseHibernateDAO<Propietario, PropietarioQuery> {

	public static String DIRECTORIO = "/fotos";

	@Override
	public Class<Propietario> getModelClass() {
		return Propietario.class;
	}

	@SuppressWarnings("unchecked")
	public List<Propietario> getList(String nombreOID) {
		List<Propietario> list = null;

		DetachedCriteria criteria = DetachedCriteria
				.forClass(Propietario.class);
		criteria.add(Restrictions.ilike("nombre", nombreOID, MatchMode.START));
		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	@SuppressWarnings("unchecked")
	public Propietario get(String nombre) throws DataAccessException {
		List<Propietario> list = null;
		Propietario chofer = new Propietario();
		chofer.setNombre(nombre);
		list = getHibernateTemplate().findByExample(chofer);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Propietario> getList(Object query) throws DataAccessException {
		PropietarioQuery choferQuery = (PropietarioQuery) query;
		List<Propietario> list = null;

		DetachedCriteria criteria = DetachedCriteria
				.forClass(Propietario.class);
		criteria.add(Restrictions.ilike("nombre", choferQuery.getNombre(),
				MatchMode.START));

		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	public Propietario save(Propietario object) throws DataAccessException {
		if (object != null) {
			String nombre = "foto" + new Date().getTime() + ".jpg";
			if (object.getImagen() != null) {
				try {
					FilesystemUtil.escribirArchivo(DIRECTORIO, nombre,
							object.getImagen());
				} catch (Exception e) {
					Log.error("Error al guardar el archivo adjunto: ", e);
				}
			}
			object.setRutaArchivo(DIRECTORIO);
			object.setNombreArchivo(nombre);
			getHibernateTemplate().save(object);
		}
		return object;
	}

	public Propietario edit(Propietario object) throws DataAccessException {
		if (object != null) {
			String nombre = "foto" + new Date().getTime() + ".jpg";
			if (object.getImagen() != null) {
				try {
					FilesystemUtil.escribirArchivo(DIRECTORIO, nombre,
							object.getImagen());
					// borro el archivo cargado actual
					FilesystemUtil.borrarArchivo(object.getRutaArchivo(),
							object.getNombreArchivo());
				} catch (Exception e) {
					Log.error("Error al guardar el archivo adjunto: ", e);
				}
				object.setRutaArchivo(DIRECTORIO);
				object.setNombreArchivo(nombre);
			}

			getHibernateTemplate().merge(object);
		}
		return object;
	}

	public void delete(Propietario object) throws DataAccessException {
		if (object.getNombreArchivo() != null
				&& !"".equals(object.getNombreArchivo())) {
			try {
				FilesystemUtil.borrarArchivo(object.getRutaArchivo(),
						object.getNombreArchivo());
			} catch (Exception e) {
				Log.error("Error al borrar el archivo adjunto: ", e);
			}
		}
		getHibernateTemplate().delete(object);
	}

	@Override
	public List<String> getListNames() {
		List<String> list = new ArrayList<String>();
		list.add("autonomoList");
		//list.add("movilList");

		return list;
	}
}
