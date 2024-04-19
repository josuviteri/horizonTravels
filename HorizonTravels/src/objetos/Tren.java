package objetos;


public abstract class Tren extends Medio{

    protected Viaje viaje;
	public Tren(double impuestoNacional, double impuestoInter, String codigoMedio) {
		super(impuestoNacional, impuestoInter, codigoMedio);

	}

	public abstract double calcularPrecio();

}
