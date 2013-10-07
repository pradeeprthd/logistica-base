package logistica.cron;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logistica.common.dao.BaseModelDAO;
import logistica.model.Chofer;
import logistica.model.Movil;
import logistica.model.MovilPropietarioDetalle;
import logistica.model.Propietario;
import logistica.util.DateUtil;
import logistica.util.MailServiceImpl;

public class MovilVencimiento {

	private BaseModelDAO<Movil> dao;

	private BaseModelDAO<Propietario> daoPropietario;

	private BaseModelDAO<Chofer> daoChofer;

	private MailServiceImpl mailService;

	public MailServiceImpl getMailService() {
		return mailService;
	}

	public void setMailService(MailServiceImpl mailService) {
		this.mailService = mailService;
	}

	public BaseModelDAO<Movil> getDao() {
		return dao;
	}

	public void setDao(BaseModelDAO<Movil> dao) {
		this.dao = dao;
	}

	public BaseModelDAO<Propietario> getDaoPropietario() {
		return daoPropietario;
	}

	public void setDaoPropietario(BaseModelDAO<Propietario> daoPropietario) {
		this.daoPropietario = daoPropietario;
	}

	public BaseModelDAO<Chofer> getDaoChofer() {
		return daoChofer;
	}

	public void setDaoChofer(BaseModelDAO<Chofer> daoChofer) {
		this.daoChofer = daoChofer;
	}

	public void notificarVencimientos() {

		/*
		 * 
		 * Se va a crear una arquitectura para el envío automático de mails para
		 * el aviso de los siguientes vencimientos:
		 * 
		 * chofer: Libreta sanitaria, Registro
		 * 
		 * propietario: Cédula verde
		 * 
		 * Movil:
		 * 
		 * Verificación técnica, Senasa, Habilitaciones (4), Seguro y Recibo del
		 * seguro.
		 * 
		 * Este mail debe informar los próximos vencimientos con tres días de
		 * anticipación y los vencimientos ya ocurridos con un plazo de tres
		 * meses.
		 * 
		 * El mail va a ser diario.
		 */

		List<Movil> movilList = new ArrayList<Movil>();
		movilList = dao.getList();
		StringBuilder sb = new StringBuilder();

		Date fechaTresMeses = DateUtil.getSumarRestarDias(new Date(), -90);
		Date fechaTresDias = DateUtil.getSumarRestarDias(new Date(), 7);

		sb.append("Vencimientos al "
				+ DateUtil.dateToString(new Date(), "dd/MM/yyyy") + "\n\n\n");

		sb.append("Vencimientos de móviles \n\n");
		for (Movil movil : movilList) {

			// movil = dao.findFULL(movil.getID());

			if (DateUtil.isVencido(movil.getFechaVerificacionTecnica())) {
				if (movil.getFechaVerificacionTecnica().compareTo(
						fechaTresMeses) > 0) {
					sb.append(" Movil: "
							+ movil.getNumeroMovil()
							+ ". Verificación técnica: "
							+ DateUtil.dateToString(
									movil.getFechaVerificacionTecnica(),
									"dd/MM/yyyy") + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaSenasa())) {
				if (movil.getFechaSenasa().compareTo(fechaTresMeses) > 0) {
					sb.append(" Movil: "
							+ movil.getNumeroMovil()
							+ ". SENASA: "
							+ DateUtil.dateToString(movil.getFechaSenasa(),
									"dd/MM/yyyy") + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaOtraHabilitacion1())) {
				if (movil.getFechaOtraHabilitacion1().compareTo(fechaTresMeses) > 0) {
					sb.append(" Movil: "
							+ movil.getNumeroMovil()
							+ ". Otra habilitación 1: "
							+ DateUtil.dateToString(
									movil.getFechaOtraHabilitacion1(),
									"dd/MM/yyyy") + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaOtraHabilitacion2())) {
				if (movil.getFechaOtraHabilitacion2().compareTo(fechaTresMeses) > 0) {
					sb.append(" Movil: "
							+ movil.getNumeroMovil()
							+ ". Otra habilitación 2: "
							+ DateUtil.dateToString(
									movil.getFechaOtraHabilitacion2(),
									"dd/MM/yyyy") + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaOtraHabilitacion3())) {
				if (movil.getFechaOtraHabilitacion3().compareTo(fechaTresMeses) > 0) {
					sb.append(" Movil: "
							+ movil.getNumeroMovil()
							+ ". Otra habilitación 3: "
							+ DateUtil.dateToString(
									movil.getFechaOtraHabilitacion3(),
									"dd/MM/yyyy") + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaOtraHabilitacion4())) {
				if (movil.getFechaOtraHabilitacion4().compareTo(fechaTresMeses) > 0) {
					sb.append(" Movil: "
							+ movil.getNumeroMovil()
							+ ". Otra habilitación 4: "
							+ DateUtil.dateToString(
									movil.getFechaOtraHabilitacion4(),
									"dd/MM/yyyy") + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaSeguroHasta())) {
				if (movil.getFechaSeguroHasta().compareTo(fechaTresMeses) > 0) {
					sb.append(" Movil: "
							+ movil.getNumeroMovil()
							+ ". Fecha Seguro hasta: "
							+ DateUtil.dateToString(
									movil.getFechaSeguroHasta(), "dd/MM/yyyy")
							+ " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaReciboVencimiento())) {
				if (movil.getFechaReciboVencimiento().compareTo(fechaTresMeses) > 0) {
					sb.append(" Movil: "
							+ movil.getNumeroMovil()
							+ ". Fecha recibo vencimiento: "
							+ DateUtil.dateToString(
									movil.getFechaReciboVencimiento(),
									"dd/MM/yyyy") + " \n");
				}
			}

		}

		sb.append("\nVencimientos de propietarios \n\n");
		List<Propietario> propietarioList = daoPropietario.getList();
		for (Propietario propietario : propietarioList) {

			propietario = daoPropietario.findFULL(propietario.getID());

			for (MovilPropietarioDetalle mpd : propietario
					.getMovilPropietarioDetalleList()) {
				if (DateUtil.isVencido(mpd.getFechaCedulaVerde())) {
					if (mpd.getFechaCedulaVerde().compareTo(fechaTresMeses) > 0) {
						sb.append(" Propietario: "
								+ propietario.getNombre()
								+ ". Fecha cédula verde: "
								+ DateUtil.dateToString(
										mpd.getFechaCedulaVerde(), "dd/MM/yyyy")
								+ " \n");
					}
				}
			}

		}

		sb.append("\nVencimientos de choferes \n\n");
		List<Chofer> choferList = daoChofer.getList();
		for (Chofer chofer : choferList) {

			if (DateUtil.isVencido(chofer.getFechaLibreataSanitaria())) {
				if (chofer.getFechaLibreataSanitaria()
						.compareTo(fechaTresMeses) > 0) {
					sb.append(" Chofer: "
							+ chofer.getNombre()
							+ ". Fecha libreta sanitaria: "
							+ DateUtil.dateToString(
									chofer.getFechaLibreataSanitaria(),
									"dd/MM/yyyy") + " \n");
				}
			}

		}

		sb.append("\nPróximos vencimientos \n\n\n");

		sb.append("\nPróximos vencimientos de móviles \n\n");
		for (Movil movil : movilList) {

			if (movil.getFechaVerificacionTecnica() != null) {
				if (DateUtil.dateToString(fechaTresDias, "dd/MM/yyyy").equals(
						DateUtil.dateToString(
								movil.getFechaVerificacionTecnica(),
								"dd/MM/yyyy"))) {
					sb.append(" Movil: "
							+ movil.getNumeroMovil()
							+ ". Verificación técnica: "
							+ DateUtil.dateToString(
									movil.getFechaVerificacionTecnica(),
									"dd/MM/yyyy") + " \n");
				}
			}

			if (movil.getFechaSenasa() != null
					&& DateUtil.dateToString(fechaTresDias, "dd/MM/yyyy")
							.equals(DateUtil.dateToString(
									movil.getFechaSenasa(), "dd/MM/yyyy"))) {
				sb.append(" Movil: "
						+ movil.getNumeroMovil()
						+ ". SENASA: "
						+ DateUtil.dateToString(movil.getFechaSenasa(),
								"dd/MM/yyyy") + " \n");
			}

			if (movil.getFechaOtraHabilitacion1() != null
					&& DateUtil.dateToString(fechaTresDias, "dd/MM/yyyy")
							.equals(DateUtil.dateToString(
									movil.getFechaOtraHabilitacion1(),
									"dd/MM/yyyy"))) {
				sb.append(" Movil: "
						+ movil.getNumeroMovil()
						+ ". Otra habilitación 1: "
						+ DateUtil.dateToString(
								movil.getFechaOtraHabilitacion1(), "dd/MM/yyyy")
						+ " \n");
			}

			if (movil.getFechaOtraHabilitacion2() != null
					&& DateUtil.dateToString(fechaTresDias, "dd/MM/yyyy")
							.equals(DateUtil.dateToString(
									movil.getFechaOtraHabilitacion2(),
									"dd/MM/yyyy"))) {
				sb.append(" Movil: "
						+ movil.getNumeroMovil()
						+ ". Otra habilitación 2: "
						+ DateUtil.dateToString(
								movil.getFechaOtraHabilitacion2(), "dd/MM/yyyy")
						+ " \n");
			}

			if (movil.getFechaOtraHabilitacion3() != null
					&& DateUtil.dateToString(fechaTresDias, "dd/MM/yyyy")
							.equals(DateUtil.dateToString(
									movil.getFechaOtraHabilitacion3(),
									"dd/MM/yyyy"))) {
				sb.append(" Movil: "
						+ movil.getNumeroMovil()
						+ ". Otra habilitación 3: "
						+ DateUtil.dateToString(
								movil.getFechaOtraHabilitacion3(), "dd/MM/yyyy")
						+ " \n");
			}

			if (movil.getFechaOtraHabilitacion4() != null
					&& DateUtil.dateToString(fechaTresDias, "dd/MM/yyyy")
							.equals(DateUtil.dateToString(
									movil.getFechaOtraHabilitacion4(),
									"dd/MM/yyyy"))) {
				sb.append(" Movil: "
						+ movil.getNumeroMovil()
						+ ". Otra habilitación 4: "
						+ DateUtil.dateToString(
								movil.getFechaOtraHabilitacion4(), "dd/MM/yyyy")
						+ " \n");
			}

			if (movil.getFechaSeguroHasta() != null
					&& DateUtil.dateToString(fechaTresDias, "dd/MM/yyyy")
							.equals(DateUtil.dateToString(
									movil.getFechaSeguroHasta(), "dd/MM/yyyy"))) {
				sb.append(" Movil: "
						+ movil.getNumeroMovil()
						+ ". Fecha Seguro hasta: "
						+ DateUtil.dateToString(movil.getFechaSeguroHasta(),
								"dd/MM/yyyy") + " \n");
			}

			if (movil.getFechaReciboVencimiento() != null
					&& DateUtil.dateToString(fechaTresDias, "dd/MM/yyyy")
							.equals(DateUtil.dateToString(
									movil.getFechaReciboVencimiento(),
									"dd/MM/yyyy"))) {
				sb.append(" Movil: "
						+ movil.getNumeroMovil()
						+ ". Fecha recibo vencimiento: "
						+ DateUtil.dateToString(
								movil.getFechaReciboVencimiento(), "dd/MM/yyyy")
						+ " \n");
			}

		}

		sb.append("\nPróximos vencimientos de propietarios \n\n");
		for (Propietario propietario : propietarioList) {

			propietario = daoPropietario.findFULL(propietario.getID());

			for (MovilPropietarioDetalle mpd : propietario
					.getMovilPropietarioDetalleList()) {
				if (mpd.getFechaCedulaVerde() != null
						&& DateUtil
								.dateToString(fechaTresDias, "dd/MM/yyyy")
								.equals(DateUtil.dateToString(
										mpd.getFechaCedulaVerde(), "dd/MM/yyyy"))) {
					sb.append(" Propietario: "
							+ propietario.getNombre()
							+ ".  Fecha cédula verde: "
							+ DateUtil.dateToString(mpd.getFechaCedulaVerde(),
									"dd/MM/yyyy") + " \n");
				}
			}

		}

		sb.append("\nPróximos vencimientos de choferes \n\n");

		for (Chofer chofer : choferList) {
			if (chofer.getFechaLibreataSanitaria() != null
					&& DateUtil.dateToString(fechaTresDias, "dd/MM/yyyy")
							.equals(DateUtil.dateToString(
									chofer.getFechaLibreataSanitaria(),
									"dd/MM/yyyy"))) {
				sb.append(" Chofer: "
						+ chofer.getNombre()
						+ ".  Fecha libreta sanitaria: "
						+ DateUtil.dateToString(
								chofer.getFechaLibreataSanitaria(),
								"dd/MM/yyyy") + " \n");
			}
		}

		mailService.send(
				"luciano@degac.com",
				"Vencimientos de móviles "
						+ DateUtil.dateToString(new Date(), "dd/MM/yyyy"),
				sb.toString());
	}
}
