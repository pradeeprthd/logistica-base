package com.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Chofer;
import logistica.model.Cliente;
import logistica.model.HojaRuta;
import logistica.model.Localidad;
import logistica.model.Movil;
import logistica.model.Sucursal;
import logistica.query.ChoferQuery;
import logistica.query.ClienteQuery;
import logistica.query.HojaRutaQuery;
import logistica.query.LocalidadQuery;
import logistica.query.MovilQuery;
import logistica.query.SucursalQuery;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.builder.HojaRutaBuilder;
import com.util.JSFUtil;
import com.view.DetalleHojaRutaView;
import com.view.HojaRutaView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class HojaRutaController extends PaginableController<HojaRuta> {
	private Logger log = Logger.getLogger(HojaRutaController.class);
	private ClassPathXmlApplicationContext ctx;
	private BaseModelDAO<HojaRuta> dao;
	private BaseModelDAO<Sucursal> daoSucursal;
	private BaseModelDAO<Cliente> daoCliente;
	private BaseModelDAO<Movil> daoMovil;
	private BaseModelDAO<Chofer> daoChofer;
	private BaseModelDAO<Localidad> daoLocalidad;
	private HojaRuta hojaRuta;
	private HojaRutaQuery hojaRutaQuery;
	private DataModel<DetalleHojaRutaView> detalleHojaRutaDM;

	@ManagedProperty("#{hojaRutaView}")
	private HojaRutaView hojaRutaView;

	@ManagedProperty("#{hojaRutaBuilder}")
	private HojaRutaBuilder hojaRutaBuilder;

	@SuppressWarnings("unchecked")
	public HojaRutaController() {
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			dao = (BaseModelDAO<HojaRuta>) ctx.getBean("hojaRutaDAO");
			daoSucursal = (BaseModelDAO<Sucursal>) ctx.getBean("sucursalDAO");
			daoCliente = (BaseModelDAO<Cliente>) ctx.getBean("clienteDAO");
			daoMovil = (BaseModelDAO<Movil>) ctx.getBean("movilDAO");
			daoChofer = (BaseModelDAO<Chofer>) ctx.getBean("choferDAO");
			daoLocalidad = (BaseModelDAO<Localidad>) ctx
					.getBean("localidadDAO");
			hojaRutaQuery = new HojaRutaQuery();
			detalleHojaRutaDM = new ListDataModel<DetalleHojaRutaView>();
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase HojaRutaController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public HojaRutaView getHojaRutaView() {
		return hojaRutaView;
	}

	public void setHojaRutaView(HojaRutaView hojaRutaView) {
		this.hojaRutaView = hojaRutaView;
	}

	public HojaRutaBuilder getHojaRutaBuilder() {
		return hojaRutaBuilder;
	}

	public void setHojaRutaBuilder(HojaRutaBuilder hojaRutaBuilder) {
		this.hojaRutaBuilder = hojaRutaBuilder;
	}

	public HojaRuta getHojaRuta() {
		return hojaRuta;
	}

	public HojaRutaQuery getHojaRutaQuery() {
		return hojaRutaQuery;
	}

	public DataModel<DetalleHojaRutaView> getDetalleHojaRutaDM() {
		return detalleHojaRutaDM;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			hojaRuta = (HojaRuta) lazyDM.getRowData();
			hojaRutaView = hojaRutaBuilder.toView(hojaRuta);
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
			hojaRuta = (HojaRuta) lazyDM.getRowData();
			dao.delete(hojaRuta);
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
		addEdit = true;
		clear();
	}

	public void save(ActionEvent event) {
		try {
			hojaRuta = hojaRutaBuilder.toDomain(hojaRutaView);
			if (hojaRuta.getID() != null) {
				dao.edit(hojaRuta);
				addEdit = false;
			} else {
				hojaRuta.setFechaEmision(new Date());
				hojaRuta.setPrefijo(hojaRuta.getSucursal().getNumeroSucursal());
				hojaRuta.setNumero(hojaRuta.getSucursal().getNumeroHojaRuta() + 1);
				dao.save(hojaRuta);

				// incremento el número de hoja de ruta de la sucursal
				Sucursal sucursal = hojaRuta.getSucursal();
				sucursal.setNumeroHojaRuta(hojaRuta.getSucursal()
						.getNumeroHojaRuta() + 1);
				daoSucursal.save(sucursal);
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
		lazyDM = null;
	}

	public void clear() {
		hojaRuta = new HojaRuta();
		hojaRutaView = new HojaRutaView();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<HojaRuta>() {

			@Override
			public List<HojaRuta> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, String> filtro = new HashMap<String, String>();
				if (hojaRutaQuery.getFechaEmision() != null) {
					filtro.put("fechaEmision", hojaRutaQuery.getFechaEmision()
							.toString());
				}
				if (hojaRutaQuery.getPrefijo() != null
						&& hojaRutaQuery.getPrefijo() != 0) {
					filtro.put("prefijo", hojaRutaQuery.getPrefijo().toString());
				}
				if (hojaRutaQuery.getNumero() != null
						&& hojaRutaQuery.getNumero() != 0) {
					filtro.put("numero", hojaRutaQuery.getNumero().toString());
				}
				return dao.getList(first, pageSize, "id", true, filtro);
			}

		};

		Map<String, String> filtro = new HashMap<String, String>();
		if (hojaRutaQuery.getFechaEmision() != null) {
			filtro.put("fechaEmision", hojaRutaQuery.getFechaEmision()
					.toString());
		}
		if (hojaRutaQuery.getPrefijo() != null
				&& hojaRutaQuery.getPrefijo() != 0) {
			filtro.put("prefijo", hojaRutaQuery.getPrefijo().toString());
		}
		if (hojaRutaQuery.getNumero() != null && hojaRutaQuery.getNumero() != 0) {
			filtro.put("numero", hojaRutaQuery.getNumero().toString());
		}
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

	public void addDetalle(ActionEvent event) {
		hojaRutaView.getDetalleHojaRutaViewList()
				.add(new DetalleHojaRutaView());
		detalleHojaRutaDM = new ListDataModel<DetalleHojaRutaView>(
				hojaRutaView.getDetalleHojaRutaViewList());
	}

	public void deleteDetalle(ActionEvent event) {
		DetalleHojaRutaView detalle = detalleHojaRutaDM.getRowData();
		hojaRutaView.getDetalleHojaRutaViewList().remove(detalle);
		detalleHojaRutaDM = new ListDataModel<DetalleHojaRutaView>(
				hojaRutaView.getDetalleHojaRutaViewList());
	}

	public boolean isSucursalSelected() {
		return (hojaRutaView.getSucursal() != null);
	}

	public void deselecionarSucursal(ActionEvent event) {
		hojaRutaView.setSucursal(null);
	}

	public List<Sucursal> completeSucursal(String query) {
		List<Sucursal> sucursalList = null;
		try {
			SucursalQuery sucursalQuery = new SucursalQuery(null, query, null);
			sucursalList = daoSucursal.getList(sucursalQuery);

		} catch (Throwable e) {
			log.error("Error en le metodo completeSucursal: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
		return sucursalList;
	}

	public void handleSucursalSelect(SelectEvent event) {
		System.out.println("se eligio una Sucursal");
		hojaRutaView.setSucursal((Sucursal) event.getObject());
	}

	public boolean isClienteSelected() {
		return (hojaRutaView.getCliente() != null);
	}

	public void deselecionarCliente(ActionEvent event) {
		hojaRutaView.setCliente(null);
	}

	public List<Cliente> completeCliente(String query) {
		List<Cliente> clienteList = null;
		try {
			ClienteQuery clienteQuery = new ClienteQuery(null, query);
			clienteList = daoCliente.getList(clienteQuery);

		} catch (Throwable e) {
			log.error("Error en le metodo completeCliente: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
		return clienteList;
	}

	public void handleClienteSelect(SelectEvent event) {
		System.out.println("se eligio un cliente");
		hojaRutaView.setCliente((Cliente) event.getObject());
	}

	public boolean isChoferSelected() {
		return (hojaRutaView.getChofer() != null);
	}

	public void deseleccionarChofer(ActionEvent event) {
		hojaRutaView.setChofer(null);
	}

	public List<Chofer> completeChofer(String query) {
		List<Chofer> choferList = null;
		try {
			ChoferQuery choferQuery = new ChoferQuery(null, query);
			choferList = daoChofer.getList(choferQuery);

		} catch (Throwable e) {
			log.error("Error en le metodo completeChofer: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
		return choferList;
	}

	public void handleChoferSelect(SelectEvent event) {
		System.out.println("se eligio un chofer");
		hojaRutaView.setChofer((Chofer) event.getObject());
	}

	public boolean isMovilSelected() {
		return (hojaRutaView.getMovil() != null);
	}

	public void deseleccionarMovil(ActionEvent event) {
		hojaRutaView.setMovil(null);
	}

	public List<Movil> completeMovil(String query) {
		List<Movil> movilList = null;
		try {
			Long numeroMovil = null;
			try {
				if (query != null) {
					numeroMovil = Long.parseLong(query);
				}
			} catch (Exception e) {
			}
			MovilQuery movilQuery = new MovilQuery(null, query, numeroMovil);
			movilList = daoMovil.getList(movilQuery);

		} catch (Throwable e) {
			log.error("Error en le metodo completeMovil: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
		return movilList;
	}

	public void handleMovilSelect(SelectEvent event) {
		System.out.println("se eligio un movil");
		hojaRutaView.setMovil((Movil) event.getObject());
	}

	public List<Localidad> completeLocalidad(String query) {
		List<Localidad> localidadList = null;
		try {
			LocalidadQuery localidadQuery = new LocalidadQuery(null, query,
					null);
			localidadList = daoLocalidad.getList(localidadQuery);

		} catch (Throwable e) {
			log.error("Error en le metodo completeLocalidad: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
		return localidadList;
	}

	public boolean isLocalidadSelected() {
		return (hojaRutaView.getLocalidad() != null);
	}

	public void deselecionarLocalidad(ActionEvent event) {
		hojaRutaView.setLocalidad(null);
	}

	public void handleSelect(SelectEvent event) {
		System.out.println("se eligio una localidad");
		hojaRutaView.setLocalidad((Localidad) event.getObject());
	}
}
