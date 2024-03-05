package objetos;

public class Asiento {
	protected Viaje viaje;
    protected boolean disponibilidad;
    protected String pasajero;
	
    
  //constructor asiento vacio
    public Asiento(Viaje viaje) {
		super();
		this.viaje = viaje;
		this.disponibilidad = true;
		this.pasajero = null;
	}
	
//	constructor asiento ocupado
	public Asiento(Viaje viaje, String pasajero) {
		super();
		this.viaje = viaje;
		this.disponibilidad = false;
		this.pasajero = pasajero;
	}
	
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public boolean isDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public String getPasajero() {
		return pasajero;
	}
	public void setPasajero(String pasajero) {
		this.pasajero = pasajero;
	}

    
    
}
