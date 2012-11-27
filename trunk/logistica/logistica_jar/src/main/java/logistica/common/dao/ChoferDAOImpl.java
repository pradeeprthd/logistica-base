package logistica.common.dao;

import java.util.Date;
import java.util.List;

import logistica.model.Chofer;
import logistica.query.ChoferQuery;
import logistica.util.FilesystemUtil;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;
import org.springframework.dao.DataAccessException;

public class ChoferDAOImpl extends BaseHibernateDAO<Chofer, ChoferQuery> {

	public static String DIRECTORIO = "/fotos";

	@Override
	public Class<Chofer> getModelClass() {
		return Chofer.class;
	}

	@SuppressWarnings("unchecked")
	public List<Chofer> getList(String nombreOID) {
		List<Chofer> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Chofer.class);
		criteria.add(Restrictions.ilike("nombre", nombreOID, MatchMode.START));
		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	@SuppressWarnings("unchecked")
	public Chofer get(String nombre) throws DataAccessException {
		List<Chofer> list = null;
		Chofer chofer = new Chofer();
		chofer.setNombre(nombre);
		list = getHibernateTemplate().findByExample(chofer);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Chofer> getList(Object query) throws DataAccessException {
		ChoferQuery choferQuery = (ChoferQuery) query;
		List<Chofer> list = null;

		DetachedCriteria criteria = DetachedCriteria.forClass(Chofer.class);
		criteria.add(Restrictions.ilike("nombre", choferQuery.getNombre(),
				MatchMode.START));

		list = getHibernateTemplate().findByCriteria(criteria);
		return list;
	}

	public Chofer save(Chofer object) throws DataAccessException {
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

	public Chofer edit(Chofer object) throws DataAccessException {
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
			getHibernateTemplate().merge(object);
		}
		return object;
	}

	public void delete(Chofer object) throws DataAccessException {
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
}
