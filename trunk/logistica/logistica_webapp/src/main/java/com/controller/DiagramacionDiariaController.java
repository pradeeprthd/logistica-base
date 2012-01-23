package com.controller;

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

import logistica.common.dao.BaseModelDAO;
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

import org.apache.log4j.Logger;
import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.builder.DetalleAsignacionBuilder;
import com.builder.DiagramacionDiariaBuilder;
import com.util.JSFUtil;
import com.view.DetalleAsignacionView;
import com.view.DiagramacionDiariaView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class DiagramacionDiariaController extends
		PaginableController<DiagramacionDiaria> {
	private Logger log = Logger.getLogger(DiagramacionDiariaController.class);
	private ClassPathXmlApplicationContext ctx;
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

	@ManagedProperty("#{diagramacionDiariaView}")
	private DiagramacionDiariaView diagramacionDiariaView;

	private DetalleAsignacionView detalleAsignacionView;

	@ManagedProperty("#{diagramacionDiariaBuilder}")
	private DiagramacionDiariaBuilder diagramacionDiariaBuilder;

	@ManagedProperty("#{detalleAsignacionBuilder}")
	private DetalleAsignacionBuilder detalleAsignacionBuilder;

	@SuppressWarnings("unchecked")
	public DiagramacionDiariaController() {
		try {
			ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
			dao = (BaseModelDAO<DiagramacionDiaria>) ctx
					.getBean("diagramacionDiariaDAO");
			daoMovilNoOperativo = (BaseModelDAO<MovilNoOperativo>) ctx
					.getBean("movilNoOperativoDAO");
			daoOtrosServicios = (BaseModelDAO<OtrosServicios>) ctx
					.getBean("otrosServiciosDAO");
			daoMovil = (BaseModelDAO<Movil>) ctx.getBean("movilDAO");
			daoSucursalCoto = (BaseModelDAO<SucursalCoto>) ctx
					.getBean("sucursalCotoDAO");
			diagramacionDiaria = new DiagramacionDiaria();
			diagramacionDiaria.setFecha(new Date());
			cargarMovilesNoOperativos(new Date());
			diagramacionDiariaQuery = new DiagramacionDiariaQuery();
			detalleAsignacionView = new DetalleAsignacionView();
			movilSeleccionadoList = new ArrayList<Movil>();
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
		this.detalleAsignacionView = detalleAsignacionView;
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
		// clear();
	}

	public void save(ActionEvent event) {
		try {
			diagramacionDiaria = diagramacionDiariaBuilder
					.toDomain(diagramacionDiariaView);
			if (diagramacionDiaria.getID() != null) {
				dao.edit(diagramacionDiaria);
				addEdit = false;
			} else {
				dao.save(diagramacionDiaria);
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
		movilSeleccionadoList.add((Movil) event.getObject());
	}

	public void handleDateSelect(DateSelectEvent event) {
		Date fecha = event.getDate();
		clear();

		// Cargo los miviles no operativo para la fecha y los que estan en otros
		// servicios
		cargarMovilesNoOperativos(fecha);
	}

	private void cargarMovilesNoOperativos(Date fecha) {
		MovilNoOperativoQuery mnoQuery = new MovilNoOperativoQuery();
		mnoQuery.setFecha(fecha);
		movilNoOperativoList = daoMovilNoOperativo.getList(mnoQuery);
		OtrosServiciosQuery osquery = new OtrosServiciosQuery();
		osquery.setFecha(fecha);
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

		// ordeno por sucursal
		Collections.sort(diagramacionDiariaView.getDetalleSucursalViewList());
	}

	public void seleccionarMovil(ActionEvent event) {
		System.out
				.println("aca se debe seleccionar recargar las listas segun los que eligio en el dialog");
	}
	
	public void seleccionarFlete(ActionEvent event) {
		System.out
				.println("aca se debe seleccionar recargar las listas segun los que eligio en el dialog");
	}

}
