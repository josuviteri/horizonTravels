package objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Company implements Serializable{
	
	private String codigo;
	private String nombre;
	private Medio medio;
	private ArrayList<String> listaViajes;
	
	public Company(String codigo, String nombre, Medio medio) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.medio = medio;
		this.setListaViajes(new ArrayList<String>());
	}
	
	public Company(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.medio = null;
		this.setListaViajes(new ArrayList<String>());
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
	

	public Medio getMedio() {
		return medio;
	}

	public void setMedio(Medio medio) {
		this.medio = medio;
	}

	public ArrayList<String> getListaViajes() {
		return listaViajes;
	}

	public void setListaViajes(ArrayList<String> listaViajes) {
		this.listaViajes = listaViajes;
	}

}
