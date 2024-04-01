package objetos;

public class Asiento {
	protected Viaje viaje;
    protected boolean disponibilidad;
    protected String pasajero;
    protected Integer id;
	
    protected static Integer ultimoId = 0;

  //constructor asiento vacio
    public Asiento(Viaje viaje) {
		super();
		this.viaje = viaje;
		this.disponibilidad = true;
		this.pasajero = null;
        this.id = ++ultimoId;

	}
	
//	constructor asiento ocupado
	public Asiento(Viaje viaje, String pasajero) {
		super();
		this.viaje = viaje;
		this.disponibilidad = false;
		this.pasajero = pasajero;
        this.id = ++ultimoId; 
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
    public Integer getId() {
        return id;
    }

    
    
}
