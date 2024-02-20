package objetos;

import java.util.List;

public abstract class Vuelo extends Reserva{

	private String aerolinea;
	protected List<Puesto> listaPuestos; 
	
	public Vuelo(int id, String origen, String destino, String fecha, int duracion, String aerolinea) {
	        super(id, origen, destino, fecha, duracion);
	        this.aerolinea = aerolinea;
	}
	


	 public String getAerolinea() {
		return aerolinea;
	}



	public void setAerolinea(String aerolinea) {
		this.aerolinea = aerolinea;
	}



	public List<Puesto> getAsientos() {
		return listaPuestos;
	}



	public void setAsientos(List<Puesto> listaPuestos) {
		this.listaPuestos = listaPuestos;
	}



	public abstract double calcularPrecio();
	
}
