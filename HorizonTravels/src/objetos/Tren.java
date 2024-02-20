package objetos;

import java.util.List;

public abstract class Tren extends Reserva{
	
	private String compania;
	protected List<Puesto> listaPuestos; 
	
	public Tren(int id, String origen, String destino, String fecha, int duracion, String compania) {
		super(id, origen, destino, fecha, duracion);
		this.compania = compania;
	}
	 
	
	
	 public String getCompania() {
		return compania;
	}



	public void setCompania(String compania) {
		this.compania = compania;
	}



	public List<Puesto> getListaPuestos() {
		return listaPuestos;
	}



	public void setListaPuestos(List<Puesto> listaPuestos) {
		this.listaPuestos = listaPuestos;
	}



	public abstract double calcularPrecio();

}
