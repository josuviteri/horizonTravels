package objetos;



public abstract class Vuelo extends Medio{
    protected Viaje viaje;
	 public Vuelo(double impuestoNacional, double impuestoInter, String codigoMedio) {
	        super(impuestoNacional, impuestoInter, codigoMedio);
	    }


	public abstract double calcularPrecio(Viaje viaje);
	
}
