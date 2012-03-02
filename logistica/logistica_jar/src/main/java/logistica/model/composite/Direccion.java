package logistica.model.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import logistica.model.Localidad;

@SuppressWarnings("serial")
@Embeddable
public class Direccion implements Serializable {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "localidadID")
	private Localidad localidad;

	@Column(length = 200)
	private String calle;

	@Column(length = 20)
	private String altura;

	@Column(length = 20)
	private String piso;

	@Column(length = 20)
	private String departamento;

	@Column(length = 20)
	private String codigoPostal;

	public Direccion(Localidad localidad, String calle, String altura,
			String piso, String departamento, String codigoPostal) {
		super();
		this.localidad = localidad;
		this.calle = calle;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.codigoPostal = codigoPostal;
	}

	public Direccion() {
		this(null, null, null, null, null, null);
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getDireccionCompleta() {
		StringBuilder sb = new StringBuilder();

		if (calle != null && !"".equals(calle)) {
			sb.append("Calle: ").append(calle);
		}
		if (altura != null && !"".equals(altura)) {
			sb.append(" Altura: ").append(altura);
		}
		if (piso != null && !"".equals(piso)) {
			sb.append(" Piso: ").append(piso);
		}
		if (departamento != null && !"".equals(departamento)) {
			sb.append(" Departamento: ").append(departamento);
		}
		return sb.toString();
	}
}
