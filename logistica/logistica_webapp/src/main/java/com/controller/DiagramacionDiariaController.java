package com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import logistica.common.dao.BaseModelDAO;
import logistica.jasper.DiagramacionDiariaReport;
import logistica.model.DetalleAsignacion;
import logistica.model.DetalleSucursal;
import logistica.model.DiagramacionDiaria;
import logistica.model.Movil;
import logistica.model.MovilNoOperativo;
import logistica.model.OtrosServicios;
import logistica.model.SucursalCoto;
import logistica.query.DiagramacionDiariaQuery;
import logistica.query.MovilNoOperativoQuery;
import logistica.query.MovilQuery;
import logistica.query.OtrosServiciosQuery;
import logistica.util.DateUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.DetalleAsignacionBuilder;
import com.builder.DiagramacionDiariaBuilder;
import com.util.JSFUtil;
import com.view.DetalleAsignacionView;
import com.view.DetalleSucursalView;
import com.view.DiagramacionDiariaView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DiagramacionDiariaController extends
		PaginableController<DiagramacionDiaria> {
	private Logger log = Logger.getLogger(DiagramacionDiariaController.class);
	private BaseModelDAO<DiagramacionDiaria> dao;
	private BaseModelDAO<MovilNoOperativo> daoMovilNoOperativo;
	private BaseModelDAO<OtrosServicios> daoOtrosServicios;
	private BaseModelDAO<Movil> daoMovil;
	private BaseModelDAO<SucursalCoto> daoSucursalCoto;
	private DiagramacionDiaria diagramacionDiaria;
	private DiagramacionDiariaQuery diagramacionDiariaQuery;
	private List<OtrosServicios> otrosServiciosList;
	private List<MovilNoOperativo> movilNoOperativoList;
	private List<Movil> movilSeleccionadoList;
	private List<SucursalCoto> sucursalCotoList;
	private DetalleAsignacionView detalleAsignacionView;
	private DetalleAsignacionView detalleAsignacionViewOld;
	private String novedad;
	private List<String> novedades;
	private DataModel<String> novedadDM;

	private DetalleSucursalView detalleSucursalView;

	@ManagedProperty("#{diagramacionDiariaView}")
	private DiagramacionDiariaView diagramacionDiariaView;

	@ManagedProperty("#{diagramacionDiariaBuilder}")
	private DiagramacionDiariaBuilder diagramacionDiariaBuilder;

	@ManagedProperty("#{detalleAsignacionBuilder}")
	private DetalleAsignacionBuilder detalleAsignacionBuilder;

	@SuppressWarnings("unchecked")
	public DiagramacionDiariaController() {
		try {
			dao = (BaseModelDAO<DiagramacionDiaria>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("diagramacionDiariaDAO");
			daoMovilNoOperativo = (BaseModelDAO<MovilNoOperativo>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilNoOperativoDAO");
			daoOtrosServicios = (BaseModelDAO<OtrosServicios>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("otrosServiciosDAO");
			daoMovil = (BaseModelDAO<Movil>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilDAO");
			daoSucursalCoto = (BaseModelDAO<SucursalCoto>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("sucursalCotoDAO");
			diagramacionDiaria = new DiagramacionDiaria();
			diagramacionDiaria.setFecha(new Date());
			cargarMovilesNoOperativos(new Date());
			diagramacionDiariaQuery = new DiagramacionDiariaQuery();
			detalleAsignacionView = new DetalleAsignacionView();
			movilSeleccionadoList = new ArrayList<Movil>();
			novedadDM = new ListDataModel<String>();
			sucursalCotoList = daoSucursalCoto.getList();
			addEdit = false;
		} catch (Throwable e) {
			log.error(
					"Error al inicializar la clase DiagramacionDiariaController",
					e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public DiagramacionDiariaView getDiagramacionDiariaView() {
		return diagramacionDiariaView;
	}

	public void setDiagramacionDiariaView(
			DiagramacionDiariaView diagrmacionDiariaView) {
		this.diagramacionDiariaView = diagrmacionDiariaView;
	}

	public DiagramacionDiariaBuilder getDiagramacionDiariaBuilder() {
		return diagramacionDiariaBuilder;
	}

	public void setDiagramacionDiariaBuilder(
			DiagramacionDiariaBuilder diagramacionDiariaBuilder) {
		this.diagramacionDiariaBuilder = diagramacionDiariaBuilder;
	}

	public DetalleAsignacionBuilder getDetalleAsignacionBuilder() {
		return detalleAsignacionBuilder;
	}

	public void setDetalleAsignacionBuilder(
			DetalleAsignacionBuilder detalleAsignacionBuilder) {
		this.detalleAsignacionBuilder = detalleAsignacionBuilder;
	}

	public DiagramacionDiariaQuery getDiagramacionDiariaQuery() {
		return diagramacionDiariaQuery;
	}

	public DetalleAsignacionView getDetalleAsignacionView() {
		return detalleAsignacionView;
	}

	public void setDetalleAsignacionView(
			DetalleAsignacionView detalleAsignacionView) {
		try {
			this.detalleAsignacionView = (DetalleAsignacionView) detalleAsignacionView
					.clone();
			this.detalleAsignacionViewOld = (DetalleAsignacionView) detalleAsignacionView
					.clone();

			RequestContext.getCurrentInstance().addPartialUpdateTarget(
					"form:movilDialogID");

			RequestContext.getCurrentInstance().addPartialUpdateTarget(
					"form:fleteDialogID");

		} catch (CloneNotSupportedException e) {
		}
	}

	public void setDetalleAsignacionViewToDelete(
			DetalleAsignacionView detalleAsignacionView) {
		try {
			this.detalleAsignacionView = (DetalleAsignacionView) detalleAsignacionView
					.clone();

			deleteAndReloadListView();

			RequestContext.getCurrentInstance().addPartialUpdateTarget(
					"form:panel");

		} catch (CloneNotSupportedException e) {
		}
	}

	public DetalleSucursalView getDetalleSucursalView() {
		return detalleSucursalView;
	}

	public void setDetalleSucursalView(DetalleSucursalView detalleSucursalView) {
		this.detalleSucursalView = detalleSucursalView;
	}

	public void setDetalleSucursalViewAdd(
			DetalleSucursalView detalleSucursalView) {
		this.detalleSucursalView = detalleSucursalView;
		addDetalleAsignacion();

		RequestContext.getCurrentInstance()
				.addPartialUpdateTarget("form:panel");
	}

	public String getNovedad() {
		return novedad;
	}

	public void setNovedad(String novedad) {
		this.novedad = novedad;
	}

	public DataModel<String> getNovedadDM() {
		return novedadDM;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			diagramacionDiaria = (DiagramacionDiaria) lazyDM.getRowData();
			diagramacionDiaria = dao.findFULL(diagramacionDiaria.getID());
			diagramacionDiariaView = diagramacionDiariaBuilder
					.toView(diagramacionDiaria);
			novedades = diagramacionDiaria.getNovedades();
			novedadDM = new ListDataModel<String>(novedades);

			novedadDM = new ListDataModel<String>(
					diagramacionDiaria.getNovedades());
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
			diagramacionDiaria = (DiagramacionDiaria) lazyDM.getRowData();
			dao.delete(diagramacionDiaria);
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
		crearDiagramacionDiaria();
	}

	public void save(ActionEvent event) {
		try {
			diagramacionDiaria = diagramacionDiariaBuilder
					.toDomain(diagramacionDiariaView);
			diagramacionDiaria.setNovedades(novedades);
			if (diagramacionDiaria.getID() != null) {
				dao.edit(diagramacionDiaria);
			} else {
				dao.save(diagramacionDiaria);
			}
			// clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);

		} catch (DataIntegrityViolationException e) {
			JSFUtil.saveMessage(
					"Error al guardar: Solo puede existir una diagramacion para una fecha.",
					FacesMessage.SEVERITY_ERROR);
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
		// lazyDM = null;
	}

	public void clear() {
		diagramacionDiaria = new DiagramacionDiaria();
		diagramacionDiaria.setFecha(new Date());
		diagramacionDiariaView = new DiagramacionDiariaView();
		detalleAsignacionView = new DetalleAsignacionView();
		movilSeleccionadoList.clear();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<DiagramacionDiaria>() {

			@Override
			public List<DiagramacionDiaria> load(int first, int pageSize,
					String sortField, SortOrder sortOrder,
					Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				if (diagramacionDiariaQuery.getFecha() != null) {
					filtro.put("fecha", diagramacionDiariaQuery.getFecha());
				}
				return dao.getList(first, pageSize, "fecha", false, filtro);
			}

		};

		Map<String, Object> filtro = new HashMap<String, Object>();
		if (diagramacionDiariaQuery.getFecha() != null) {
			filtro.put("fecha", diagramacionDiariaQuery.getFecha());
		}
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

	public boolean isMovilSelected() {
		return (detalleAsignacionView.getMovil() != null);
	}

	public void deseleccionarMovil(ActionEvent event) {
		detalleAsignacionView.setMovil(null);
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

			// Quito de la lista los moviles no operativos
			for (MovilNoOperativo mno : movilNoOperativoList) {
				movilList.remove(mno.getMovil());
			}

			// Quito los moviles que estan en otros servicios
			for (OtrosServicios os : otrosServiciosList) {
				movilList.remove(os.getMovil());
			}

			// Quito los moviles ya selecionados
			for (Movil movil : movilSeleccionadoList) {
				movilList.remove(movil);
			}

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
	}

	public void handleDateSelect(DateSelectEvent event) {
		Date fecha = event.getDate();
		// clear();

		// Cargo los miviles no operativo para la fecha y los que estan en otros
		// servicios
		cargarMovilesNoOperativos(fecha);

		// borro los movile no operativos para esa fecha
		limpiarIDSMovilesNoOperativosYSeleccionados(diagramacionDiariaView);

		RequestContext.getCurrentInstance()
				.addPartialUpdateTarget("form:panel");
	}

	private void cargarMovilesNoOperativos(Date fecha) {

		Date fechaSinHora = DateUtil.getFirstTime(fecha);
		MovilNoOperativoQuery mnoQuery = new MovilNoOperativoQuery();
		mnoQuery.setFecha(fechaSinHora);
		movilNoOperativoList = daoMovilNoOperativo.getList(mnoQuery);
		OtrosServiciosQuery osquery = new OtrosServiciosQuery();
		osquery.setFecha(fechaSinHora);
		otrosServiciosList = daoOtrosServicios.getList(osquery);
	}

	private void crearDiagramacionDiariaDesdeCoto() {
		diagramacionDiaria = new DiagramacionDiaria();
		diagramacionDiaria.setFecha(new Date());
		List<DetalleAsignacion> detalleAsignacionList = new ArrayList<DetalleAsignacion>();
		List<DetalleSucursal> detalleSucursalList = new ArrayList<DetalleSucursal>();
		for (SucursalCoto sucursalCoto : sucursalCotoList) {
			if (sucursalCoto.getCantidadMoviles() > 0) {
				int contador = 0;
				while (contador < sucursalCoto.getCantidadMoviles()) {
					detalleAsignacionList.add(new DetalleAsignacion());
					contador++;
				}
				detalleSucursalList.add(new DetalleSucursal(null, sucursalCoto,
						sucursalCoto.getCantidadMoviles(),
						detalleAsignacionList));
				detalleAsignacionList = new ArrayList<DetalleAsignacion>();
			}
		}
		diagramacionDiaria.setDetalleSucursalList(detalleSucursalList);
	}

	private void crearDiagramacionDiaria() {
		diagramacionDiaria = dao.get("dummy");
		if (diagramacionDiaria != null) {
			diagramacionDiaria = dao.findFULL(diagramacionDiaria.getID());
		} else {
			crearDiagramacionDiariaDesdeCoto();
		}
		diagramacionDiariaView = diagramacionDiariaBuilder
				.toView(diagramacionDiaria);

		// le pongo la fecha del dia
		diagramacionDiariaView.setFecha(DateUtil.getFirstTime(new Date()));

		// limpio los idsy los moviles que estan no operatios para la fecha
		diagramacionDiariaView = limpiarIDSMovilesNoOperativosYSeleccionados(diagramacionDiariaView);
		
		// limpio el código de coto
		//diagramacionDiariaView = limpiarCodigoCoto(diagramacionDiariaView);

		// ordeno por sucursal
		Collections.sort(diagramacionDiariaView.getDetalleSucursalViewList());
	}

	private DiagramacionDiariaView limpiarIDSMovilesNoOperativosYSeleccionados(
			DiagramacionDiariaView diagramacionDiariaView) {
		movilSeleccionadoList = new ArrayList<Movil>();
		if (diagramacionDiariaView != null) {
			diagramacionDiariaView.setId(null);
			for (DetalleSucursalView detalleSucursal : diagramacionDiariaView
					.getDetalleSucursalViewList()) {
				detalleSucursal.setId(null);
				for (DetalleAsignacionView detalleAsignacion : detalleSucursal
						.getDetalleAsignacionViewList()) {
					detalleAsignacion.setId(null);
					// me fijo el movil esta no operativo para esa fecha
					if (isMovilNoOperativo(detalleAsignacion.getMovil())) {
						detalleAsignacion.setMovil(null);
					} else {
						movilSeleccionadoList.add(detalleAsignacion.getMovil());
					}
				}
			}
		}
		return diagramacionDiariaView;
	}
	
	private DiagramacionDiariaView limpiarCodigoCoto(
			DiagramacionDiariaView diagramacionDiariaView) {
		if (diagramacionDiariaView != null) {
			for (DetalleSucursalView detalleSucursal : diagramacionDiariaView
					.getDetalleSucursalViewList()) {
				for (DetalleAsignacionView detalleAsignacion : detalleSucursal
						.getDetalleAsignacionViewList()) {
					detalleAsignacion.setCodigoCoto(null);
				}
			}
		}
		return diagramacionDiariaView;
	}

	private boolean isMovilNoOperativo(Movil movil) {
		boolean ret = false;

		if (movil != null) {
			// Quito de la lista los moviles no operativos
			for (MovilNoOperativo mno : movilNoOperativoList) {
				ret = mno.getMovil().equals(movil);
				if (ret) {
					break;
				}
			}

			// Quito los moviles que estan en otros servicios
			if (!ret) {
				for (OtrosServicios os : otrosServiciosList) {
					ret = os.getMovil().equals(movil);
					if (ret) {
						break;
					}
				}
			}
		}
		return ret;
	}

	public void seleccionarMovil(ActionEvent event) {
		detalleAsignacionView.setDescripcionFlete(null);
		detalleAsignacionView.setHorarioPedidoFlete(null);
		detalleAsignacionView.setHorarioSalida(null);
		detalleAsignacionView.setNombreAgenciaFlete(null);
		reloadListView();
	}

	public void seleccionarFlete(ActionEvent event) {
		detalleAsignacionView.setMovil(null);
		reloadListView();
	}

	public void selectFleteMovil(ActionEvent event) {
		detalleAsignacionView = new DetalleAsignacionView();
	}

	private void reloadListView() {
		DetalleSucursalView detalleSucursalAux = null;
		if (diagramacionDiariaView != null) {
			for (DetalleSucursalView detalleSucursal : diagramacionDiariaView
					.getDetalleSucursalViewList()) {
				if (detalleSucursal.equals(detalleSucursalView)) {
					detalleSucursalAux = detalleSucursal;
					break;
				}
			}
			if (detalleSucursalAux != null) {
				detalleSucursalAux.getDetalleAsignacionViewList().remove(
						detalleAsignacionViewOld);
				detalleSucursalAux.getDetalleAsignacionViewList().add(
						detalleAsignacionView);

				diagramacionDiariaView.getDetalleSucursalViewList().remove(
						detalleSucursalAux);
				diagramacionDiariaView.getDetalleSucursalViewList().add(
						detalleSucursalAux);
			}

			movilSeleccionadoList = new ArrayList<Movil>();
			// veo cuales moviles estan seleccionados
			for (DetalleSucursalView detalleSucursal : diagramacionDiariaView
					.getDetalleSucursalViewList()) {
				for (DetalleAsignacionView detalleAsignacion : detalleSucursal
						.getDetalleAsignacionViewList()) {
					if (detalleAsignacion.getMovil() != null) {
						movilSeleccionadoList.add(detalleAsignacion.getMovil());
					}
				}
			}
		}
		// ordeno por sucursal
		Collections.sort(diagramacionDiariaView.getDetalleSucursalViewList());
	}

	private void deleteAndReloadListView() {
		DetalleSucursalView detalleSucursalAux = null;
		if (diagramacionDiariaView != null) {
			for (DetalleSucursalView detalleSucursal : diagramacionDiariaView
					.getDetalleSucursalViewList()) {
				if (detalleSucursal.equals(detalleSucursalView)) {
					detalleSucursalAux = detalleSucursal;
					break;
				}
			}
			if (detalleSucursalAux != null) {
				detalleSucursalAux.getDetalleAsignacionViewList().remove(
						detalleAsignacionView);

				diagramacionDiariaView.getDetalleSucursalViewList().remove(
						detalleSucursalAux);
				diagramacionDiariaView.getDetalleSucursalViewList().add(
						detalleSucursalAux);
			}

			movilSeleccionadoList = new ArrayList<Movil>();
			// veo cuales moviles estan seleccionados
			for (DetalleSucursalView detalleSucursal : diagramacionDiariaView
					.getDetalleSucursalViewList()) {
				for (DetalleAsignacionView detalleAsignacion : detalleSucursal
						.getDetalleAsignacionViewList()) {
					if (detalleAsignacion.getMovil() != null) {
						movilSeleccionadoList.add(detalleAsignacion.getMovil());
					}
				}
			}
		}
		// ordeno por sucursal
		Collections.sort(diagramacionDiariaView.getDetalleSucursalViewList());
	}

	private void addDetalleAsignacion() {
		DetalleSucursalView detalleSucursalAux = null;
		if (diagramacionDiariaView != null) {
			for (DetalleSucursalView detalleSucursal : diagramacionDiariaView
					.getDetalleSucursalViewList()) {
				if (detalleSucursal.equals(detalleSucursalView)) {
					detalleSucursalAux = detalleSucursal;
					break;
				}
			}
			if (detalleSucursalAux != null) {
				detalleSucursalAux.getDetalleAsignacionViewList().add(
						new DetalleAsignacionView());

				diagramacionDiariaView.getDetalleSucursalViewList().remove(
						detalleSucursalAux);
				diagramacionDiariaView.getDetalleSucursalViewList().add(
						detalleSucursalAux);
			}

			movilSeleccionadoList = new ArrayList<Movil>();
			// veo cuales moviles estan seleccionados
			for (DetalleSucursalView detalleSucursal : diagramacionDiariaView
					.getDetalleSucursalViewList()) {
				for (DetalleAsignacionView detalleAsignacion : detalleSucursal
						.getDetalleAsignacionViewList()) {
					if (detalleAsignacion.getMovil() != null) {
						movilSeleccionadoList.add(detalleAsignacion.getMovil());
					}
				}
			}
		}
		// ordeno por sucursal
		Collections.sort(diagramacionDiariaView.getDetalleSucursalViewList());
	}

	public void addNovedad(ActionEvent event) {
		if (novedades == null) {
			novedades = new ArrayList<String>();
		}
		novedades.add(novedad);
		novedadDM = new ListDataModel<String>(novedades);
	}

	public void deleteNovedad(ActionEvent event) {
		String detalle = novedadDM.getRowData();
		novedades.remove(detalle);
		novedadDM = new ListDataModel<String>(novedades);
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void toPDF(DiagramacionDiaria diagramacionDiaria) {
		try {

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletContext sc = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String realpath = sc.getRealPath(File.separator
					+ "resource/jasper/diagramacionDiaira.jasper");

			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(realpath);

			JRDataSource datasource = new JRBeanCollectionDataSource(
					getReporte(diagramacionDiaria));
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					new HashMap(), datasource);

			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment; filename=DiagramacionDiaria.pdf");

			/*
			 * JasperExportManager.exportReportToPdfFile(print,
			 * "DiagramacionDiaria.pdf");
			 */

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
		DiagramacionDiaria diagramcionDiaria = (DiagramacionDiaria) lazyDM
				.getRowData();
		diagramcionDiaria = dao.findFULL(diagramcionDiaria.getID());
		toPDF(diagramcionDiaria);
	}

	private List<DiagramacionDiariaReport> getReporte(
			DiagramacionDiaria diagramacionDiaria) {
		List<DiagramacionDiariaReport> diagramacionDiariaList = new ArrayList<DiagramacionDiariaReport>();
		String novedades = "";

		for (String novedad : diagramacionDiaria.getNovedades()) {
			if (novedades.length() > 1) {
				novedades = novedades + "//" + novedad;
			} else {
				novedades = novedad;
			}
		}

		for (DetalleSucursal detalleSucursal : diagramacionDiaria
				.getDetalleSucursalList()) {
			for (DetalleAsignacion detalleAsignacion : detalleSucursal
					.getDetalleAsignacionList()) {
				String movil = null;
				if (detalleAsignacion.getMovil() != null) {
					movil = detalleAsignacion.getMovil().getNumeroMovil() + "-"
							+ detalleAsignacion.getMovil().getPatente();
				}

				DiagramacionDiariaReport ddr = new DiagramacionDiariaReport(
						diagramacionDiaria.getFecha(), novedades,
						detalleSucursal.getSucursalCoto().getNumeroSucursal(),
						detalleSucursal.getSucursalCoto().getNombre(), movil,
						detalleAsignacion.getDescripcionFlete(),
						detalleAsignacion.getHorarioEntrada(),
						detalleAsignacion.getHorarioSalida(),
						detalleAsignacion.getHorarioPedidoFlete(),
						detalleAsignacion.getNombreAgenciaFlete(),
						detalleAsignacion.getCodigoCoto());
				diagramacionDiariaList.add(ddr);
			}
		}

		Collections.sort(diagramacionDiariaList);

		return diagramacionDiariaList;
	}
}
