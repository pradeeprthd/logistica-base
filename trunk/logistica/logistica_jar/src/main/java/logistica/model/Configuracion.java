package logistica.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import logistica.common.BaseModel;

@Entity
@SuppressWarnings("serial")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Configuracion extends BaseModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "ConfiguracionSEQ")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "id")
	@Column(unique = true)
	private Long id;

	@Column(length = 100)
	private String nombreEmpresa;

	@Column(length = 100)
	private String leyendaTicket;

	public String getLeyendaTicket() {
		return leyendaTicket;
	}

	public void setLeyendaTicket(String leyendaTicket) {
		this.leyendaTicket = leyendaTicket;
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

}
