package com.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
import logistica.jasper.HistorialMovilChoferReport;
import logistica.jasper.MovilReport;
import logistica.jasper.ResumenDetalladoDetalleReport;
import logistica.jasper.ResumenDetalladoReport;
import logistica.jasper.ResumenGeneralReport;
import logistica.model.Autonomo;
import logistica.model.Chofer;
import logistica.model.Form170;
import logistica.model.Form817;
import logistica.model.HistorialMovilChofer;
import logistica.model.Movil;
import logistica.model.Nomina;
import logistica.model.Propietario;
import logistica.model.Recibo;
import logistica.query.ChoferQuery;
import logistica.query.MovilQuery;
import logistica.type.AsignacionMovilEnum;
import logistica.type.CoberturaAdicionalEnum;
import logistica.type.EstadoEnum;
import logistica.type.ParentezcoEnum;
import logistica.type.TipoCombustibleEnum;
import logistica.type.TipoUsoEnum;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.jsf.FacesContextUtils;

import com.builder.AutonomoBuilder;
import com.builder.Form170Builder;
import com.builder.Form817Builder;
import com.builder.MovilBuilder;
import com.builder.MovilReportBuilder;
import com.builder.NominaBuilder;
import com.builder.ReciboBuilder;
import com.util.JSFUtil;
import com.view.AutonomoView;
import com.view.Form170View;
import com.view.Form817View;
import com.view.MovilView;
import com.view.NominaView;
import com.view.ReciboView;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class MovilController extends PaginableController<Movil> {
	private Logger log = Logger.getLogger(MovilController.class);
	private BaseModelDAO<Movil> dao;
	private BaseModelDAO<Chofer> daoChofer;
	private BaseModelDAO<Propietario> daoPropietario;
	private BaseModelDAO<HistorialMovilChofer> daoHistorialMovilChofer;
	private Movil movil;
	private Movil movilOriginal;
	private Movil movilReport;
	private MovilQuery movilQuery;
	private List<AsignacionMovilEnum> asignacionMovilEnumList;
	private List<TipoCombustibleEnum> tipoCombustibleEnumList;
	private List<ParentezcoEnum> parentezcoEnumList;
	private List<EstadoEnum> estadoEnumList;
	private List<TipoUsoEnum> tipoUsoEnumList;
	private List<CoberturaAdicionalEnum> coberturaAdicionalEnumList;
	private List<HistorialMovilChofer> historialMovilChoferList;
	private Autonomo patcom;
	private DataModel<AutonomoView> patcomDM;
	private Form817 form817;
	private DataModel<Form817View> form817DM;
	private Form170 form170;
	private DataModel<Form170View> form170DM;
	private Recibo recibo;
	private DataModel<ReciboView> reciboDM;
	private Nomina nomina;
	private DataModel<NominaView> nominaDM;
	private String nota;
	private List<String> notas;
	private DataModel<String> notasDM;
	private String notaControl;
	private List<String> notasControl;
	private DataModel<String> notasControlDM;

	@ManagedProperty("#{autonomoBuilder}")
	private AutonomoBuilder autonomoBuilder;

	@ManagedProperty("#{form817Builder}")
	private Form817Builder form817Builder;

	@ManagedProperty("#{form170Builder}")
	private Form170Builder form170Builder;

	@ManagedProperty("#{reciboBuilder}")
	private ReciboBuilder reciboBuilder;

	@ManagedProperty("#{nominaBuilder}")
	private NominaBuilder nominaBuilder;

	@ManagedProperty("#{movilView}")
	private MovilView movilView;

	@ManagedProperty("#{movilBuilder}")
	private MovilBuilder movilBuilder;

	@ManagedProperty("#{movilReportBuilder}")
	private MovilReportBuilder movilReportBuilder;

	@SuppressWarnings("unchecked")
	public MovilController() {
		try {
			dao = (BaseModelDAO<Movil>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("movilDAO");
			daoChofer = (BaseModelDAO<Chofer>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("choferDAO");
			daoPropietario = (BaseModelDAO<Propietario>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("propietarioDAO");
			daoHistorialMovilChofer = (BaseModelDAO<HistorialMovilChofer>) FacesContextUtils
					.getWebApplicationContext(FacesContext.getCurrentInstance())
					.getBean("historialMovilChoferDAO");

			movilQuery = new MovilQuery();
			asignacionMovilEnumList = Arrays.asList(AsignacionMovilEnum
					.values());
			tipoCombustibleEnumList = Arrays.asList(TipoCombustibleEnum
					.values());
			tipoUsoEnumList = Arrays.asList(TipoUsoEnum.values());
			coberturaAdicionalEnumList = Arrays.asList(CoberturaAdicionalEnum
					.values());
			parentezcoEnumList = Arrays.asList(ParentezcoEnum.values());
			estadoEnumList = Arrays.asList(EstadoEnum.values());
			patcomDM = new ListDataModel<AutonomoView>();
			patcom = new Autonomo();
			form817DM = new ListDataModel<Form817View>();
			form817 = new Form817();
			form170DM = new ListDataModel<Form170View>();
			form170 = new Form170();
			reciboDM = new ListDataModel<ReciboView>();
			recibo = new Recibo();
			nominaDM = new ListDataModel<NominaView>();
			nomina = new Nomina();
			notasDM = new ListDataModel<String>();
			notasControlDM = new ListDataModel<String>();
			addEdit = false;
		} catch (Throwable e) {
			log.error("Error al inicializar la clase MovilController", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al realizar la operacion", ""));
		}
	}

	public MovilView getMovilView() {
		return movilView;
	}

	public void setMovilView(MovilView movilView) {
		this.movilView = movilView;
	}

	public MovilBuilder getMovilBuilder() {
		return movilBuilder;
	}

	public void setMovilBuilder(MovilBuilder movilBuilder) {
		this.movilBuilder = movilBuilder;
	}

	public Movil getMovil() {
		return movil;
	}

	public MovilQuery getMovilQuery() {
		return movilQuery;
	}

	public List<AsignacionMovilEnum> getAsignacionMovilEnumList() {
		return asignacionMovilEnumList;
	}

	public List<ParentezcoEnum> getParentezcoEnumList() {
		return parentezcoEnumList;
	}

	public List<EstadoEnum> getEstadoEnumList() {
		return estadoEnumList;
	}

	public Autonomo getPatcom() {
		return patcom;
	}

	public DataModel<AutonomoView> getPatcomDM() {
		return patcomDM;
	}

	public ReciboBuilder getReciboBuilder() {
		return reciboBuilder;
	}

	public void setReciboBuilder(ReciboBuilder reciboBuilder) {
		this.reciboBuilder = reciboBuilder;
	}

	public Recibo getRecibo() {
		return recibo;
	}

	public DataModel<ReciboView> getReciboDM() {
		return reciboDM;
	}

	public DataModel<NominaView> getNominaDM() {
		return nominaDM;
	}

	public DataModel<Form817View> getForm817DM() {
		return form817DM;
	}

	public Form817 getForm817() {
		return form817;
	}

	public Form170 getForm170() {
		return form170;
	}

	public Nomina getNomina() {
		return nomina;
	}

	public DataModel<Form170View> getForm170DM() {
		return form170DM;
	}

	public AutonomoBuilder getAutonomoBuilder() {
		return autonomoBuilder;
	}

	public void setAutonomoBuilder(AutonomoBuilder autonomoBuilder) {
		this.autonomoBuilder = autonomoBuilder;
	}

	public Form817Builder getForm817Builder() {
		return form817Builder;
	}

	public void setForm817Builder(Form817Builder form817Builder) {
		this.form817Builder = form817Builder;
	}

	public Form170Builder getForm170Builder() {
		return form170Builder;
	}

	public void setForm170Builder(Form170Builder form170Builder) {
		this.form170Builder = form170Builder;
	}

	public NominaBuilder getNominaBuilder() {
		return nominaBuilder;
	}

	public void setNominaBuilder(NominaBuilder nominaBuilder) {
		this.nominaBuilder = nominaBuilder;
	}

	public List<TipoCombustibleEnum> getTipoCombustibleEnumList() {
		return tipoCombustibleEnumList;
	}

	public List<TipoUsoEnum> getTipoUsoEnumList() {
		return tipoUsoEnumList;
	}

	public List<CoberturaAdicionalEnum> getCoberturaAdicionalEnumList() {
		return coberturaAdicionalEnumList;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public List<String> getNotas() {
		return notas;
	}

	public void setNotas(List<String> notas) {
		this.notas = notas;
	}

	public DataModel<String> getNotasDM() {
		return notasDM;
	}

	public void setNotasDM(DataModel<String> notasDM) {
		this.notasDM = notasDM;
	}

	public String getNotaControl() {
		return notaControl;
	}

	public void setNotaControl(String notaControl) {
		this.notaControl = notaControl;
	}

	public List<String> getNotasControl() {
		return notasControl;
	}

	public void setNotasControl(List<String> notasControl) {
		this.notasControl = notasControl;
	}

	public DataModel<String> getNotasControlDM() {
		return notasControlDM;
	}

	public void setNotasControlDM(DataModel<String> notasControlDM) {
		this.notasControlDM = notasControlDM;
	}

	public MovilReportBuilder getMovilReportBuilder() {
		return movilReportBuilder;
	}

	public void setMovilReportBuilder(MovilReportBuilder movilReportBuilder) {
		this.movilReportBuilder = movilReportBuilder;
	}

	public void query(ActionEvent event) {
		loadList();
	}

	public void edit(ActionEvent event) {
		try {
			movil = (Movil) lazyDM.getRowData();
			movilOriginal = (Movil) lazyDM.getRowData();
			movil = dao.findFULL(movil.getID());
			patcomDM = new ListDataModel<AutonomoView>(
					autonomoBuilder.toView(movil.getPatcomList()));
			form817DM = new ListDataModel<Form817View>(
					form817Builder.toView(movil.getForm817List()));
			form170DM = new ListDataModel<Form170View>(
					form170Builder.toView(movil.getForm170List()));
			reciboDM = new ListDataModel<ReciboView>(reciboBuilder.toView(movil
					.getReciboList()));
			nominaDM = new ListDataModel<NominaView>(nominaBuilder.toView(movil
					.getNominaList()));
			movilView = movilBuilder.toView(movil);
			notas = movil.getNotas();
			notasDM = new ListDataModel<String>(notas);
			notasControl = movil.getNotasControl();
			notasControlDM = new ListDataModel<String>(notasControl);
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
			movil = (Movil) lazyDM.getRowData();
			movil.setEstado(EstadoEnum.INACTIVO);
			movil.setFechaEgreso(new Date());
			dao.edit(movil);
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
			movil = movilBuilder.toDomain(movilView);
			movil.setNotas(notas);
			movil.setNotasControl(notasControl);

			if (movil.getID() != null) {
				if (movil.getEstado() == EstadoEnum.INACTIVO) {
					movil.setFechaEgreso(new Date());
				} else {
					movil.setFechaEgreso(null);
				}
				dao.edit(movil);
				addEdit = false;

				// historialMovilChoferList =
				// daoHistorialMovilChofer.getList(movil);
				if (movil.getChofer1() != null
						&& movilOriginal.getChofer1() != null
						&& movil.getChofer1().getID().intValue() != movilOriginal
								.getChofer1().getID().intValue()) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movilOriginal, movilOriginal.getChofer1(),
							EstadoEnum.INACTIVO, new Date()));

					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movil, movil.getChofer1(), EstadoEnum.ACTIVO,
							new Date()));
				} else if (movilOriginal.getChofer1() == null
						&& movil.getChofer1() != null) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movil, movil.getChofer1(), EstadoEnum.ACTIVO,
							new Date()));
				} else if (movilOriginal.getChofer1() != null
						&& movil.getChofer1() == null) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movilOriginal, movilOriginal.getChofer1(),
							EstadoEnum.INACTIVO, new Date()));
				}

				/*
				 * for(HistorialMovilChofer hmc : historialMovilChoferList){
				 * if(hmc.getChofer().getID().intValue() ==
				 * movil.getChofer1().getID().intValue() ){
				 * if(!hmc.getEstado().equals(EstadoEnum.ACTIVO)){
				 * daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
				 * movil, movil.getChofer1(), EstadoEnum.ACTIVO, new Date())); }
				 * } }
				 */

				// chofer 2

				if (movil.getChofer2() != null
						&& movilOriginal.getChofer2() != null
						&& movil.getChofer2().getID().intValue() != movilOriginal
								.getChofer2().getID().intValue()) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movilOriginal, movilOriginal.getChofer2(),
							EstadoEnum.INACTIVO, new Date()));

					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movil, movil.getChofer2(), EstadoEnum.ACTIVO,
							new Date()));
				} else if (movilOriginal.getChofer2() == null
						&& movil.getChofer2() != null) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movil, movil.getChofer2(), EstadoEnum.ACTIVO,
							new Date()));
				} else if (movilOriginal.getChofer2() != null
						&& movil.getChofer2() == null) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movilOriginal, movilOriginal.getChofer2(),
							EstadoEnum.INACTIVO, new Date()));
				}

				// chofer 3
				if (movil.getChofer3() != null
						&& movilOriginal.getChofer3() != null
						&& movil.getChofer3().getID().intValue() != movilOriginal
								.getChofer3().getID().intValue()) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movilOriginal, movilOriginal.getChofer3(),
							EstadoEnum.INACTIVO, new Date()));

					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movil, movil.getChofer3(), EstadoEnum.ACTIVO,
							new Date()));
				} else if (movilOriginal.getChofer3() == null
						&& movil.getChofer3() != null) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movil, movil.getChofer3(), EstadoEnum.ACTIVO,
							new Date()));
				} else if (movilOriginal.getChofer3() != null
						&& movil.getChofer3() == null) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movilOriginal, movilOriginal.getChofer3(),
							EstadoEnum.INACTIVO, new Date()));
				}

			} else {
				movil.setFechaIngreso(new Date());
				dao.save(movil);

				if (movil.getChofer1() != null) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movil, movil.getChofer1(), EstadoEnum.ACTIVO,
							new Date()));
				}
				if (movil.getChofer2() != null) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movil, movil.getChofer2(), EstadoEnum.ACTIVO,
							new Date()));
				}
				if (movil.getChofer3() != null) {
					daoHistorialMovilChofer.save(new HistorialMovilChofer(null,
							movil, movil.getChofer3(), EstadoEnum.ACTIVO,
							new Date()));
				}
			}
			clear();
			JSFUtil.saveMessage("Elemento guardado con exito",
					FacesMessage.SEVERITY_INFO);
			if (!addEdit) {
				loadList();
			}
		} catch (DataIntegrityViolationException e) {
			JSFUtil.saveMessage(
					"Error al guardar: La patente y el numero de movil deben ser unicos en el sistema.",
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
		lazyDM = null;
	}

	public void clear() {
		movil = new Movil();
		movilView = new MovilView();
	}

	private void loadList() {

		lazyDM = new LazyDataModel<Movil>() {

			@Override
			public List<Movil> load(int first, int pageSize, String sortField,
					SortOrder sortOrder, Map<String, String> filters) {

				Map<String, Object> filtro = new HashMap<String, Object>();
				filtro.put("patente", movilQuery.getPatente());
				if (movilQuery.getNumeroMovil() != null
						&& movilQuery.getNumeroMovil() != 0) {
					filtro.put("numeroMovil", movilQuery.getNumeroMovil());
				}
				return dao
						.getList(first, pageSize, "numeroMovil", true, filtro);
			}

		};
		Map<String, Object> filtro = new HashMap<String, Object>();
		filtro.put("patente", movilQuery.getPatente());
		if (movilQuery.getNumeroMovil() != null
				&& movilQuery.getNumeroMovil() != 0) {
			filtro.put("numeroMovil", movilQuery.getNumeroMovil());
		}
		lazyDM.setRowCount(dao.count(filtro).intValue());
	}

	public boolean isChofer1Selected() {
		return (movilView.getChofer1() != null);
	}

	public void deseleccionarChofer1(ActionEvent event) {
		movilView.setChofer1(null);
	}

	public boolean isChofer2Selected() {
		return (movilView.getChofer2() != null);
	}

	public void deseleccionarChofer2(ActionEvent event) {
		movilView.setChofer2(null);
	}

	public boolean isChofer3Selected() {
		return (movilView.getChofer3() != null);
	}

	public void deseleccionarChofer3(ActionEvent event) {
		movilView.setChofer3(null);
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

	public void addPatcom(ActionEvent event) {
		movilView.getPatcomList().add(
				(AutonomoView) autonomoBuilder.toView(patcom));
		patcomDM = new ListDataModel<AutonomoView>(movilView.getPatcomList());
	}

	public void deletePatcom(ActionEvent event) {
		AutonomoView detalle = patcomDM.getRowData();
		movilView.getPatcomList().remove(detalle);
		patcomDM = new ListDataModel<AutonomoView>(movilView.getPatcomList());
	}

	public void addForm817(ActionEvent event) {
		movilView.getForm817List().add(
				(Form817View) form817Builder.toView(form817));
		form817DM = new ListDataModel<Form817View>(movilView.getForm817List());
	}

	public void deleteForm817(ActionEvent event) {
		Form817View detalle = form817DM.getRowData();
		movilView.getForm817List().remove(detalle);
		form817DM = new ListDataModel<Form817View>(movilView.getForm817List());
	}

	public void addForm170(ActionEvent event) {
		movilView.getForm170List().add(
				(Form170View) form170Builder.toView(form170));
		form170DM = new ListDataModel<Form170View>(movilView.getForm170List());
	}

	public void deleteForm170(ActionEvent event) {
		Form170View detalle = form170DM.getRowData();
		movilView.getForm170List().remove(detalle);
		form170DM = new ListDataModel<Form170View>(movilView.getForm170List());
	}

	public void addRecibo(ActionEvent event) {
		movilView.getReciboList()
				.add((ReciboView) reciboBuilder.toView(recibo));
		reciboDM = new ListDataModel<ReciboView>(movilView.getReciboList());
	}

	public void deleteRecibo(ActionEvent event) {
		ReciboView detalle = reciboDM.getRowData();
		movilView.getReciboList().remove(detalle);
		reciboDM = new ListDataModel<ReciboView>(movilView.getReciboList());
	}

	public void addNomina(ActionEvent event) {
		movilView.getNominaList()
				.add((NominaView) nominaBuilder.toView(nomina));
		nominaDM = new ListDataModel<NominaView>(movilView.getNominaList());
	}

	public void deleteNomina(ActionEvent event) {
		NominaView detalle = nominaDM.getRowData();
		movilView.getNominaList().remove(detalle);
		nominaDM = new ListDataModel<NominaView>(movilView.getNominaList());
	}

	public void addNota(ActionEvent event) {
		if (notas == null) {
			notas = new ArrayList<String>();
		}

		if (nota != null && nota.length() < 256) {
			notas.add(nota);
			notasDM = new ListDataModel<String>(notas);
		}
	}

	public void deleteNota(ActionEvent event) {
		String detalle = notasDM.getRowData();
		notas.remove(detalle);
		notasDM = new ListDataModel<String>(notas);
	}

	public void addNotaControl(ActionEvent event) {
		if (notasControl == null) {
			notasControl = new ArrayList<String>();
		}

		if (notaControl != null && notaControl.length() < 256) {
			notasControl.add(notaControl);
			notasControlDM = new ListDataModel<String>(notasControl);
		}
	}

	public void deleteNotaControl(ActionEvent event) {
		String detalle = notasControlDM.getRowData();
		notasControl.remove(detalle);
		notasControlDM = new ListDataModel<String>(notasControl);
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void caratulaToPDF(Movil movil) {
		try {

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletContext sc = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String realpath = sc.getRealPath(File.separator
					+ "resource/jasper/caratula.jasper");

			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(realpath);

			JRDataSource datasource = new JRBeanCollectionDataSource(
					getReporteMovil(movil));
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					new HashMap(), datasource);

			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment; filename=Caratula.pdf");

			/* JasperExportManager.exportReportToPdfFile(print, "hojaRuta.pdf"); */

			JasperExportManager.exportReportToPdfStream(print,
					response.getOutputStream());

			// FacesContext.getCurrentInstance().responseComplete();
		} catch (Throwable e) {
			log.error("Error al exportar a PDF: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al exportar a PDF", ""));
			System.out.println("error al exportar: " + e.getMessage());
		}
	}

	private List<MovilReport> getReporteMovil(Movil movil) {
		List<MovilReport> list = new ArrayList<MovilReport>();

		MovilReport movilReport = movilReportBuilder.toView(movil);
		list.add(movilReport);

		return list;
	}

	public void caratulaToPDF(ActionEvent actionEvent) {
		movilReport = (Movil) lazyDM.getRowData();
		movilReport = dao.findFULL(movilReport.getID());
		List<Propietario> propietarioList = daoPropietario.getList(
				movilReport.getID(), 0l);

		if (propietarioList != null && propietarioList.size() > 0) {
			movilReport.setPropietario(propietarioList.get(0));
		}

		caratulaToPDF(movilReport);
		JSFUtil.reloadPage();
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void resumenGeneralToPDF() {
		try {

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletContext sc = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String realpath = sc.getRealPath(File.separator
					+ "resource/jasper/resumenGeneral.jasper");

			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(realpath);

			JRDataSource datasource = new JRBeanCollectionDataSource(
					getReporteResumenGeneral());
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					new HashMap(), datasource);

			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment; filename=ResumenGeneral.pdf");

			/* JasperExportManager.exportReportToPdfFile(print, "hojaRuta.pdf"); */

			JasperExportManager.exportReportToPdfStream(print,
					response.getOutputStream());

			// FacesContext.getCurrentInstance().responseComplete();
		} catch (Throwable e) {
			log.error("Error al exportar a PDF: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al exportar a PDF", ""));
			System.out.println("error al exportar: " + e.getMessage());
		}
	}

	private List<ResumenGeneralReport> getReporteResumenGeneral() {
		List<ResumenGeneralReport> list = new ArrayList<ResumenGeneralReport>();
		List<Object[]> listObject = new ArrayList<Object[]>();

		listObject = dao.getListQuery("");

		Integer total = 0;
		for (Object o[] : listObject) {
			list.add(new ResumenGeneralReport(
					o[0] != null ? ((AsignacionMovilEnum) o[0]).toString() : "",
					((Long) o[1]).intValue(), 0));
			total = total + ((Long) o[1]).intValue();
		}

		for (ResumenGeneralReport r : list) {
			r.setTotalGeneral(total);
		}

		return list;
	}

	public void resumenGeneralToPDF(ActionEvent actionEvent) {
		resumenGeneralToPDF();
		JSFUtil.reloadPage();
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void resumenDetalladoToPDF() {
		try {

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletContext sc = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String realpath = sc.getRealPath(File.separator
					+ "resource/jasper/resumenDetallado.jasper");

			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(realpath);

			JRDataSource datasource = new JRBeanCollectionDataSource(
					getReporteResumenDetallado());
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					new HashMap(), datasource);

			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment; filename=ResumenDetallado.pdf");

			/* JasperExportManager.exportReportToPdfFile(print, "hojaRuta.pdf"); */

			JasperExportManager.exportReportToPdfStream(print,
					response.getOutputStream());

			// FacesContext.getCurrentInstance().responseComplete();
		} catch (Throwable e) {
			log.error("Error al exportar a PDF: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al exportar a PDF", ""));
			System.out.println("error al exportar: " + e.getMessage());
		}
	}

	private List<ResumenDetalladoReport> getReporteResumenDetallado() {
		List<ResumenDetalladoReport> list = new ArrayList<ResumenDetalladoReport>();
		List<ResumenDetalladoDetalleReport> listDetalle = new ArrayList<ResumenDetalladoDetalleReport>();
		List<Object[]> listObject = new ArrayList<Object[]>();

		listObject = dao.getList2Query("");

		Integer total = 0;
		String asignacion = null;
		Integer contador = 0;
		for (Object o[] : listObject) {

			contador++;

			if (asignacion == null) {
				asignacion = o[0] != null ? ((AsignacionMovilEnum) o[0])
						.toString() : "";
				listDetalle.add(new ResumenDetalladoDetalleReport((Long) o[1],
						(String) o[2], (String) o[3], (String) o[4],
						(String) o[5], (String) o[6], (String) o[7], total));
				total = 1;
			} else if (asignacion
					.equals(o[0] != null ? ((AsignacionMovilEnum) o[0])
							.toString() : "")) {
				listDetalle.add(new ResumenDetalladoDetalleReport((Long) o[1],
						(String) o[2], (String) o[3], (String) o[4],
						(String) o[5], (String) o[6], (String) o[7], total));
				total++;
			} else {
				for (ResumenDetalladoDetalleReport rddr : listDetalle) {
					rddr.setTotal(total);
				}

				list.add(new ResumenDetalladoReport(asignacion, listDetalle));

				listDetalle = new ArrayList<ResumenDetalladoDetalleReport>();

				asignacion = o[0] != null ? ((AsignacionMovilEnum) o[0])
						.toString() : "";

				listDetalle.add(new ResumenDetalladoDetalleReport((Long) o[1],
						(String) o[2], (String) o[3], (String) o[4],
						(String) o[5], (String) o[6], (String) o[7], total));
				total = 1;
			}

			if (listObject.size() == contador) {
				for (ResumenDetalladoDetalleReport rddr : listDetalle) {
					rddr.setTotal(total);
				}

				list.add(new ResumenDetalladoReport(asignacion, listDetalle));

				listDetalle = new ArrayList<ResumenDetalladoDetalleReport>();
			}

		}

		return list;
	}

	public void resumenDetalladoToPDF(ActionEvent actionEvent) {
		resumenDetalladoToPDF();
		JSFUtil.reloadPage();
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void listadoComunToPDF() {
		try {

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletContext sc = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String realpath = sc.getRealPath(File.separator
					+ "resource/jasper/ListadoComun.jasper");

			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(realpath);

			JRDataSource datasource = new JRBeanCollectionDataSource(
					getListadoComun());
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					new HashMap(), datasource);

			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment; filename=ListadoComun.pdf");

			/* JasperExportManager.exportReportToPdfFile(print, "hojaRuta.pdf"); */

			JasperExportManager.exportReportToPdfStream(print,
					response.getOutputStream());

			// FacesContext.getCurrentInstance().responseComplete();
		} catch (Throwable e) {
			log.error("Error al exportar a PDF: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al exportar a PDF", ""));
			System.out.println("error al exportar: " + e.getMessage());
		}
	}

	private List<MovilReport> getListadoComun() {
		List<MovilReport> list = new ArrayList<MovilReport>();
		List<Movil> movilList = new ArrayList<Movil>();

		movilList = dao.getList();

		for (Movil movil : movilList) {
			List<Propietario> propietarioList = daoPropietario.getList(
					movil.getID(), 0l);

			if (propietarioList != null && propietarioList.size() > 0) {
				movil.setPropietario(propietarioList.get(0));
			}
		}

		for (Movil movil : movilList) {
			MovilReport movilReport = movilReportBuilder.toView(movil);
			list.add(movilReport);
		}

		return list;
	}

	public void listadoComunToPDF(ActionEvent actionEvent) {
		listadoComunToPDF();
		JSFUtil.reloadPage();
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public void historialChoferesToPDF(Movil movil) {
		try {

			HttpServletResponse response = (HttpServletResponse) FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			ServletContext sc = (ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext();

			String realpath = sc.getRealPath(File.separator
					+ "resource/jasper/historialMovilChofer.jasper");

			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(realpath);

			JRDataSource datasource = new JRBeanCollectionDataSource(
					getReporteMovilChofer(movil));
			JasperPrint print = JasperFillManager.fillReport(jasperReport,
					new HashMap(), datasource);

			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment; filename=HistorialMovilChofer.pdf");

			/* JasperExportManager.exportReportToPdfFile(print, "hojaRuta.pdf"); */

			JasperExportManager.exportReportToPdfStream(print,
					response.getOutputStream());

			// FacesContext.getCurrentInstance().responseComplete();
		} catch (Throwable e) {
			log.error("Error al exportar a PDF: ", e);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error al exportar a PDF", ""));
			System.out.println("error al exportar: " + e.getMessage());
		}
	}

	private List<HistorialMovilChoferReport> getReporteMovilChofer(Movil movil) {
		List<HistorialMovilChoferReport> list = new ArrayList<HistorialMovilChoferReport>();

		List<HistorialMovilChofer> listModel = daoHistorialMovilChofer
				.getList(movil);
		for (HistorialMovilChofer hmc : listModel) {
			list.add(new HistorialMovilChoferReport(hmc.getMovil()
					.getNumeroMovil(), hmc.getMovil().getPatente(), hmc
					.getFecha(), hmc.getEstado().toString(), hmc.getChofer()
					.getNombre(), hmc.getChofer().getDni()));
		}

		return list;
	}

	public void historialChoferesToPDF(ActionEvent actionEvent) {
		movilReport = (Movil) lazyDM.getRowData();
		movilReport = dao.findFULL(movilReport.getID());

		historialChoferesToPDF(movilReport);
		JSFUtil.reloadPage();
	}
}
