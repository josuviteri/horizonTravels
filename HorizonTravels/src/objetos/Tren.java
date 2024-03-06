package objetos;


public abstract class Tren extends Medio{

    protected Viaje viaje;
	public Tren(double impuestoNacional, double impuestoInter) {
		super(impuestoNacional, impuestoInter);

	}

	public abstract double calcularPrecio();

}
