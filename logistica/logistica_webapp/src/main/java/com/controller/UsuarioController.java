package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Usuario;
import logistica.query.UsuarioQuery;
import logistica.type.RolEnum;
import logistica.util.EncriptadorUtil;

import org.apache.log4j.Logger;
import org.primefaces.model.DualListModel;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.builder.UsuarioBuilder;
import com.util.JSFUtil;
import com.view.UsuarioView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class UsuarioController extends PaginableController<Usuario> {

	private Logger log = Logger.getLogger(UsuarioController.class);
	private ClassPathXmlApplicationContext ctx;
	private BaseModelDAO<Usuario> dao;
	private Usuario usuario;
	private UsuarioQuery usuarioQuery;
	private DualListModel<RolEnum> rolList;
	private List<RolEnum> rolEnumList;

	@ManagedProperty("#{usuarioView}")
	private UsuarioView usuarioView;

	@ManagedProperty("#{usuarioBuilder}")
	private UsuarioBuilder usuarioBuilder;

	@SuppressWarnings("unchecked")
	public UsuarioController() {
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			dao = (BaseModelDAO<Usuario>) ctx.getBean("usuarioDAO");
			usuarioQuery = new UsuarioQuery();
			addEdit = false;

			// roles
			List<RolEnum> source = new ArrayList<RolEnum>();
			List<RolEnum> target = new ArrayList<RolEnum>();
			source.add(RolEnum.ROLE_USER);
			source.add(RolEnum.ADMIN_USER);

			rolEnumList = new ArrayList<RolEnum>();

			rolList = new DualListModel<RolEnum>(source, target);
		} catch (Throwable e) {
			log.error("Error al inicializar la clase UsuarioController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public UsuarioQuery getUsuarioQuery() {
		return usuarioQuery;
	}

	public UsuarioView getUsuarioView() {
		return usuarioView;
	}

	public void setUsuarioView(UsuarioView usuarioView) {
		this.usuarioView = usuarioView;
	}

	public UsuarioBuilder getUsuarioBuilder() {
		return usuarioBuilder;
	}

	public void setUsuarioBuilder(UsuarioBuilder usuarioBuilder) {
		this.usuarioBuilder = usuarioBuilder;
	}

	public DualListModel<RolEnum> getRolList() {
		return rolList;
	}

	public void setRolList(DualListModel<RolEnum> rolList) {
		this.rolList = rolList;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			usuario = (Usuario) lazyDM.getRowData();
			usuario = dao.findFULL(usuario.getID());
			setRoles(usuario);
			usuarioView = usuarioBuilder.toView(usuario);
			addEdit = true;
		} catch (Throwable e) {
			log.error("Error al editar", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public void delete(ActionEvent event) {
		try {
			usuario = (Usuario) lazyDM.getRowData();
			dao.delete(usuario);
			loadList();
		} catch (Throwable e) {
			log.error("Error al eliminar", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public void add(ActionEvent event) {
		// roles
		List<RolEnum> source = new ArrayList<RolEnum>();
		List<RolEnum> target = new ArrayList<RolEnum>();
		source.add(RolEnum.ROLE_USER);
		source.add(RolEnum.ADMIN_USER);
		rolList = new DualListModel<RolEnum>(source, target);

		addEdit = true;
		clear();
	}

	public void save(ActionEvent event) {
		try {
			usuario = usuarioBuilder.toDomain(usuarioView);
			getRoles();
			usuario.setRolEnumList(rolEnumList);

			// encripto la contraseña
			usuario.setContrasenia(EncriptadorUtil.getStringMessageDigest(
					usuario.getContrasenia(), EncriptadorUtil.SHA1));

			if (usuario.getID() != null) {
				dao.edit(usuario);
				addEdit = false;
			} else {
				dao.save(usuario);
			}
			clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);
			if (!addEdit) {
				loadList();
			}
		} catch (Throwable e) {
			log.error("Error al guardar", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public void cancel(ActionEvent event) {
		addEdit = false;
		loadList();
	}

	public void clear() {
		usuario = new Usuario();
		usuarioView = new UsuarioView();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<Usuario>() {

			@Override
			public List<Usuario> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, String> filtro = new HashMap<String, String>();
				filtro.put("usuario", usuarioQuery.getUsuario());
				return dao.getList(first, pageSize, "usuario", true, filtro);
			}
		};

		Map<String, String> filtro = new HashMap<String, String>();
		filtro.put("usuario", usuarioQuery.getUsuario());
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

	private void getRoles() {
		rolEnumList = rolList.getTarget();
	}

	private void setRoles(Usuario usuario) {

		// rolList.setTarget(list);

		// saco los roles que estan sleccionados.
		List<RolEnum> source = new ArrayList<RolEnum>();
		for (RolEnum rol : RolEnum.values()) {
			if (!usuario.getRolEnumList().contains(rol)) {
				source.add(rol);
			}
		}

		rolList = new DualListModel<RolEnum>(source, usuario.getRolEnumList());
	}
}
