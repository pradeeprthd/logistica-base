package logistica.cron;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logistica.common.dao.MovilDAOImpl;
import logistica.model.Movil;
import logistica.util.DateUtil;
import logistica.util.MailServiceImpl;

public class MovilVencimiento {

	private MovilDAOImpl dao;

	private MailServiceImpl mailService;

	public MovilDAOImpl getDao() {
		return dao;
	}

	public void setDao(MovilDAOImpl dao) {
		this.dao = dao;
	}

	public MailServiceImpl getMailService() {
		return mailService;
	}

	public void setMailService(MailServiceImpl mailService) {
		this.mailService = mailService;
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

		for (Movil movil : movilList) {
			if (DateUtil.isVencido(movil.getFechaVerificacionTecnica())) {
				if(movil.getFechaVerificacionTecnica().compareTo(fechaTresMeses) > 0 ){
					sb.append(" Verificación técnica: " + movil.getFechaVerificacionTecnica() + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaSenasa())) {
				if(movil.getFechaSenasa().compareTo(fechaTresMeses) > 0 ){
					sb.append(" SENASA: " + movil.getFechaSenasa() + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaOtraHabilitacion1())) {
				if(movil.getFechaOtraHabilitacion1().compareTo(fechaTresMeses) > 0 ){
					sb.append(" Otra habilitación 1: " + movil.getFechaOtraHabilitacion1() + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaOtraHabilitacion2())) {
				if(movil.getFechaOtraHabilitacion2().compareTo(fechaTresMeses) > 0 ){
					sb.append(" Otra habilitación 2: " + movil.getFechaOtraHabilitacion2() + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaOtraHabilitacion3())) {
				if(movil.getFechaOtraHabilitacion3().compareTo(fechaTresMeses) > 0 ){
					sb.append(" Otra habilitación 3: " + movil.getFechaOtraHabilitacion3() + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaOtraHabilitacion4())) {
				if(movil.getFechaOtraHabilitacion4().compareTo(fechaTresMeses) > 0 ){
					sb.append(" Otra habilitación 4: " + movil.getFechaOtraHabilitacion4() + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaSeguroHasta())) {
				if(movil.getFechaSeguroHasta().compareTo(fechaTresMeses) > 0 ){
					sb.append(" Fecha Seguro hasta: " + movil.getFechaSeguroHasta() + " \n");
				}
			}
			if (DateUtil.isVencido(movil.getFechaReciboVencimiento())) {
				if(movil.getFechaReciboVencimiento().compareTo(fechaTresMeses) > 0 ){
					sb.append(" Fecha recibo vencimiento: " + movil.getFechaReciboVencimiento() + " \n");
				}
			}
		}

		mailService.send("pavlo.rodriguez@gmail.com", "mail de prueba",
				"anda la prueba para degac");
	}

}
