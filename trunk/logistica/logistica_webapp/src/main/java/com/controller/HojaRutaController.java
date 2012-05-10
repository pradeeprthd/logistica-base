package com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import logistica.common.dao.BaseModelDAO;
import logistica.jasper.HojaRutaPrincipalReport;
import logistica.jasper.HojaRutaReport;
import logistica.model.Chofer;
import logistica.model.Cliente;
import logistica.model.DetalleHojaRuta;
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
import logistica.type.EstadoHojaRutaEnum;
import logistica.type.UnidadMedidaEnum;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.DetalleHojaRutaBuilder;
import com.builder.HojaRutaBuilder;
import com.util.JSFUtil;
import com.view.DetalleHojaRutaView;
import com.view.HojaRutaView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class HojaRutaController extends PaginableController<HojaRuta> {
	private Logger log = Logger.getLogger(HojaRutaController.class);
	private BaseModelDAO<HojaRuta> dao;
	private BaseModelDAO<Sucursal> daoSucursal;
	private BaseModelDAO<Cliente> daoCliente;
	private BaseModelDAO<Movil> daoMovil;
	private BaseModelDAO<Chofer> daoChofer;
	private BaseModelDAO<Localidad> daoLocalidad;
	private HojaRuta hojaRuta;
	private HojaRutaQuery hojaRutaQuery;
	private DataModel<DetalleHojaRutaView> detalleHojaRutaDM;
	private List<UnidadMedidaEnum> unidadMedidaEnumList;
	private List<Sucursal> sucursalList;
	private List<Localidad> localidadList;
	private String localidadQueryString;
	private List<EstadoHojaRutaEnum> estadoHojaRutaEnumList;

	@ManagedProperty("#{hojaRutaView}")
	private HojaRutaView hojaRutaView;

	private DetalleHojaRuta detalleHojaRuta;

	@ManagedProperty("#{hojaRutaBuilder}")
	private HojaRutaBuilder hojaRutaBuilder;

	@ManagedProperty("#{detalleHojaRutaBuilder}")
	private DetalleHojaRutaBuilder detalleHojaRutaBuilder;

	@SuppressWarnings("unchecked")
	public HojaRutaController() {
		try {
			dao = (BaseModelDAO<HojaRuta>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("hojaRutaDAO");
			daoSucursal = (BaseModelDAO<Sucursal>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("sucursalDAO");
			daoCliente = (BaseModelDAO<Cliente>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("clienteDAO");
			daoMovil = (BaseModelDAO<Movil>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilDAO");
			daoChofer = (BaseModelDAO<Chofer>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("choferDAO");
			daoLocalidad = (BaseModelDAO<Localidad>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("localidadDAO");
			hojaRutaQuery = new HojaRutaQuery();
			detalleHojaRutaDM = new ListDataModel<DetalleHojaRutaView>();
			detalleHojaRuta = new DetalleHojaRuta();
			detalleHojaRuta.setUnidadMedida(UnidadMedidaEnum.BULTOS);
			unidadMedidaEnumList = Arrays.asList(UnidadMedidaEnum.values());
			sucursalList = daoSucursal.getList();
			localidadList = new ArrayList<Localidad>();
			estadoHojaRutaEnumList = Arrays.asList(EstadoHojaRutaEnum.values());
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

	public DetalleHojaRuta getDetalleHojaRuta() {
		return detalleHojaRuta;
	}

	public void setDetalleHojaRuta(DetalleHojaRuta detalleHojaRuta) {
		this.detalleHojaRuta = detalleHojaRuta;
	}

	public DataModel<DetalleHojaRutaView> getDetalleHojaRutaDM() {
		return detalleHojaRutaDM;
	}

	public DetalleHojaRutaBuilder getDetalleHojaRutaBuilder() {
		return detalleHojaRutaBuilder;
	}

	public void setDetalleHojaRutaBuilder(
			DetalleHojaRutaBuilder detalleHojaRutaBuilder) {
		this.detalleHojaRutaBuilder = detalleHojaRutaBuilder;
	}

	public List<UnidadMedidaEnum> getUnidadMedidaEnumList() {
		return unidadMedidaEnumList;
	}

	public List<Sucursal> getSucursalList() {
		return sucursalList;
	}

	public List<Localidad> getLocalidadList() {
		return localidadList;
	}

	public List<EstadoHojaRutaEnum> getEstadoHojaRutaEnumList() {
		return estadoHojaRutaEnumList;
	}

	public String getLocalidadQueryString() {
		return localidadQueryString;
	}

	public void setLocalidadQueryString(String localidadQueryString) {
		this.localidadQueryString = localidadQueryString;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void queryLocalidad(ActionEvent event) {
		Date fechaInicio = new Date();
		localidadList = completeLocalidad(localidadQueryString);
		Date fechaFin = new Date();
		Long millis = fechaFin.getTime() - fechaInicio.getTime();
		System.out.println("Millis: " + millis);
	}

	public void edit(ActionEvent event) {
		try {
			hojaRuta = (HojaRuta) lazyDM.getRowData();
			hojaRuta = dao.findFULL(hojaRuta.getID());
			detalleHojaRutaDM = new ListDataModel<DetalleHojaRutaView>(
					detalleHojaRutaBuilder.toView(hojaRuta
							.getDetalleHojaRutaList()));
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

	public void toEstadoPendiente(ActionEvent event) {
		cambiarEstado(EstadoHojaRutaEnum.PENDIENTE);
	}

	public void toEstadoFinalizado(ActionEvent event) {
		cambiarEstado(EstadoHojaRutaEnum.FINALIZADO);
	}

	public void toEstadoEnTransito(ActionEvent event) {
		cambiarEstado(EstadoHojaRutaEnum.EN_TRANSITO);
	}

	private void cambiarEstado(EstadoHojaRutaEnum estadoHojaRutaEnum) {
		try {
			hojaRuta = (HojaRuta) lazyDM.getRowData();
			hojaRuta = dao.findFULL(hojaRuta.getID());
			hojaRuta.setEstadoHojaRutaEnum(estadoHojaRutaEnum);
			dao.edit(hojaRuta);
		} catch (Throwable e) {
			log.error("Error al cambiar de estado a " + estadoHojaRutaEnum, e);
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

				// incremento el numero de hoja de ruta de la sucursal
				Sucursal sucursal = hojaRuta.getSucursal();
				sucursal.setNumeroHojaRuta(hojaRuta.getSucursal()
						.getNumeroHojaRuta() + 1);
				daoSucursal.edit(sucursal);

				// exporto a PDF
				// toPDF(hojaRuta);
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
		detalleHojaRuta = new DetalleHojaRuta();
		detalleHojaRuta.setUnidadMedida(UnidadMedidaEnum.BULTOS);
		detalleHojaRutaDM = new ListDataModel<DetalleHojaRutaView>(null);
	}

	private void loadList() {

		lazyDM = new LazyDataModel<HojaRuta>() {

			@Override
			public List<HojaRuta> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				if (hojaRutaQuery.getFechaEmision() != null) {
					filtro.put("fechaEmision", hojaRutaQuery.getFechaEmision());
				}
				if (hojaRutaQuery.getPrefijo() != null
						&& hojaRutaQuery.getPrefijo() != 0) {
					filtro.put("prefijo", hojaRutaQuery.getPrefijo());
				}
				if (hojaRutaQuery.getNumero() != null
						&& hojaRutaQuery.getNumero() != 0) {
					filtro.put("numero", hojaRutaQuery.getNumero());
				}
				if (hojaRutaQuery.getEstadoHojaRutaEnum() != null) {
					filtro.put("estadoHojaRutaEnum",
							hojaRutaQuery.getEstadoHojaRutaEnum());
				}
				return dao.getList(first, pageSize, "fechaEmision", false,
						filtro);
			}

		};

		Map<String, Object> filtro = new HashMap<String, Object>();
		if (hojaRutaQuery.getFechaEmision() != null) {
			filtro.put("fechaEmision", hojaRutaQuery.getFechaEmision());
		}
		if (hojaRutaQuery.getPrefijo() != null
				&& hojaRutaQuery.getPrefijo() != 0) {
			filtro.put("prefijo", hojaRutaQuery.getPrefijo());
		}
		if (hojaRutaQuery.getNumero() != null && hojaRutaQuery.getNumero() != 0) {
			filtro.put("numero", hojaRutaQuery.getNumero());
		}
		if (hojaRutaQuery.getEstadoHojaRutaEnum() != null) {
			filtro.put("estadoHojaRutaEnum",
					hojaRutaQuery.getEstadoHojaRutaEnum());
		}
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

	public void addDetalle(ActionEvent event) {
		hojaRutaView.getDetalleHojaRutaViewList().add(
				(DetalleHojaRutaView) detalleHojaRutaBuilder
						.toView(detalleHojaRuta));
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
		// hojaRutaView.setSucursal((Sucursal) event.getObject());
	}

	public boolean isClienteSelected() {
		return (hojaRutaView.getCliente() != null);
	}

	public void deselecionarCliente(ActionEvent event) {
		hojaRutaView.setCliente(null);
	}

	public List<Cliente> completeCliente(String query) {
		List<Cliente> clienteList = null;
		Date fechaInicio = new Date();
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

		Date fechaFin = new Date();
		Long millis = fechaFin.getTime() - fechaInicio.getTime();
		System.out.println("Millis: " + millis);
		return clienteList;
	}

	public void handleClienteSelect(SelectEvent event) {
		System.out.println("se eligio un cliente");
		Cliente cliente = (Cliente) event.getObject();
		hojaRutaView
				.setDireccion(cliente.getDireccion().getDireccionCompleta());
		hojaRutaView.setLocalidad(cliente.getDireccion().getLocalidad());
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
		// hojaRutaView.setChofer((Chofer) event.getObject());
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
		// hojaRutaView.setMovil((Movil) event.getObject());
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
		// hojaRutaView.setLocalidad((Localidad) event.getObject());
	}

	public void handleSelectDetalle(SelectEvent event) {
		System.out.println("se eligio una localidad en el detalle");
	}

	public void deselecionarDetalleLocalidad(ActionEvent event) {
		detalleHojaRuta.setLocalidad(null);
	}

	public void valueChangeMethod(ValueChangeEvent e) {
		String valor = e.getNewValue().toString();
		System.out.println(valor);
	}

	public void handleDateSelect(DateSelectEvent event) {
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void toPDF(HojaRuta hojaRuta) {
		try {

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletContext sc = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String realpath = sc.getRealPath(File.separator
					+ "resource/jasper/HojaRuta.jasper");

			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(realpath);

			JRDataSource datasource = new JRBeanCollectionDataSource(
					getReporteHojaRuta(hojaRuta));
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					new HashMap(), datasource);

			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment; filename=HojaRuta.pdf");

			/* JasperExportManager.exportReportToPdfFile(print, "hojaRuta.pdf"); */

			JasperExportManager.exportReportToPdfStream(print,
					response.getOutputStream());

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Throwable e) {
			log.error("Error al exportar a PDF: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al exportar a PDF", ""));
			System.out.println("error al exportar: " + e.getMessage());
		}
	}

	public void toPDF(ActionEvent actionEvent) {
		hojaRuta = (HojaRuta) lazyDM.getRowData();
		hojaRuta = dao.findFULL(hojaRuta.getID());
		toPDF(hojaRuta);
		JSFUtil.reloadPage();
	}

	private List<HojaRutaReport> getReporteHojaRuta(HojaRuta hojaRuta) {
		List<HojaRutaReport> list = new ArrayList<HojaRutaReport>();
		if (hojaRuta.getDetalleHojaRutaList() == null
				|| hojaRuta.getDetalleHojaRutaList().size() == 0) {
			HojaRutaReport hojaRutaReport = new HojaRutaReport(
					hojaRuta.getPrefijo() + "-" + hojaRuta.getNumero(),
					hojaRuta.getFechaEmision(), hojaRuta.getCliente()
							.getNombre(), hojaRuta.getMovil().getNumeroMovil()
							.toString()
							+ "-" + hojaRuta.getMovil().getPatente(), hojaRuta
							.getChofer().getNombre(),
					hojaRuta.getNumeroRemito(), hojaRuta.getDireccion(),
					hojaRuta.getLocalidad().getDescripcion(), 0, 0, 0,
					hojaRuta.getObservaciones(), "", "", "", null, null, null);
			list.add(hojaRutaReport);
		} else {
			Integer cantidadBultos = 0;
			Integer cantidadKilogramos = 0;
			Integer cantidadMetrosCubicos = 0;
			// calculo los totales
			for (DetalleHojaRuta detalle : hojaRuta.getDetalleHojaRutaList()) {
				if (detalle.getUnidadMedida().equals(UnidadMedidaEnum.BULTOS)) {
					cantidadBultos = cantidadBultos + detalle.getCantidad();
				} else if (detalle.getUnidadMedida().equals(
						UnidadMedidaEnum.KILOGRAMOS)) {
					cantidadKilogramos = cantidadKilogramos
							+ detalle.getCantidad();
				} else if (detalle.getUnidadMedida().equals(
						UnidadMedidaEnum.METROS_CUBICOS)) {
					cantidadMetrosCubicos = cantidadMetrosCubicos
							+ detalle.getCantidad();
				}
			}

			for (DetalleHojaRuta detalle : hojaRuta.getDetalleHojaRutaList()) {
				HojaRutaReport hojaRutaReport = new HojaRutaReport(
						hojaRuta.getPrefijo() + "-" + hojaRuta.getNumero(),
						hojaRuta.getFechaEmision(), hojaRuta.getCliente()
								.getNombre(), hojaRuta.getMovil()
								.getNumeroMovil().toString()
								+ "-" + hojaRuta.getMovil().getPatente(),
						hojaRuta.getChofer().getNombre(),
						hojaRuta.getNumeroRemito(), hojaRuta.getDireccion(),
						hojaRuta.getLocalidad().getDescripcion(),
						cantidadBultos, cantidadKilogramos,
						cantidadMetrosCubicos, hojaRuta.getObservaciones(),
						detalle.getDireccion(), detalle.getLocalidad()
								.getDescripcion(), detalle.getUnidadMedida()
								.toString(), detalle.getCantidad(),
						detalle.getFechaDesde(), detalle.getFechaHasta());
				list.add(hojaRutaReport);
			}
		}
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void principalToExcel(List<HojaRuta> hojaRutaList) {
		try {

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletContext sc = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String realpath = sc.getRealPath(File.separator
					+ "resource/jasper/HojaRutaPrincipal.jasper");

			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(realpath);

			JRDataSource datasource = new JRBeanCollectionDataSource(
					getReportePrincipalHojaRuta(hojaRutaList));
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					new HashMap(), datasource);

			// Exporta el informe a excel
			JRXlsExporter exporterXLS = new JRXlsExporter();
			exporterXLS
					.setParameter(JRXlsExporterParameter.JASPER_PRINT, print);
			exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,
					response.getOutputStream());
			exporterXLS.exportReport();

			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition",
					"attachment; filename=HojaRuta.xls");

			/*
			 * JasperExportManager.exportReportToPdfFile(print, "hojaRuta.pdf");
			 */
			
			 /*response.setContentType("application/pdf");
				response.addHeader("Content-Disposition",
						"attachment; filename=HojaRuta.pdf");
			 JasperExportManager.exportReportToPdfStream(print,
			 response.getOutputStream());*/
			 
			
			 

			FacesContext.getCurrentInstance().responseComplete();
		} catch (Throwable e) {
			log.error("Error al exportar a EXCEL: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al exportar a EXCEL", ""));
			System.out.println("error al exportar: " + e.getMessage());
		}
	}

	public void principalToExcel(ActionEvent actionEvent) {
		principalToExcel(dao.getList(hojaRutaQuery));
		JSFUtil.reloadPage();
	}

	private Collection<HojaRutaPrincipalReport> getReportePrincipalHojaRuta(
			List<HojaRuta> hojaRutaList) {

		List<HojaRutaPrincipalReport> list = new ArrayList<HojaRutaPrincipalReport>();

		for (HojaRuta hojaRuta : hojaRutaList) {
			list.add(new HojaRutaPrincipalReport(hojaRuta.getID().toString(),
					hojaRuta.getSucursal().getNombre(), hojaRuta
							.getFechaEmision(), hojaRuta.getPrefijo() + " - "
							+ hojaRuta.getNumero(), hojaRuta.getCliente()
							.getNombre(), hojaRuta.getChofer().getNombre(),
					hojaRuta.getMovil().getNumeroMovil() + " - "
							+ hojaRuta.getMovil().getPatente(), hojaRuta
							.getNumeroRemito(), hojaRuta
							.getEstadoHojaRutaEnum().toString()));
		}

		return list;
	}

}
