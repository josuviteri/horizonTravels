package objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company implements Serializable{
	
	private String codigo;
	private String nombre;
	private String pais;
	private List<Viaje> viajes;
	
	public Company(String codigo, String nombre, String pais) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.pais = pais;
		this.viajes = new ArrayList<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}


}
