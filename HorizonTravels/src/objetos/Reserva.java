package objetos;

import java.sql.Date;
import java.util.List;

public class Reserva {
	
    protected Viaje viaje;
    protected Date fecha;
    protected int duracion;
    protected List<String> pasajeros;
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public List<String> getPasajeros() {
		return pasajeros;
	}
	public void setPasajeros(List<String> pasajeros) {
		this.pasajeros = pasajeros;
	}



    
    
}
