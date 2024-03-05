package objetos;

public abstract class Medio {

	protected Viaje viaje;

    public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public abstract double calcularPrecio();
	
}
