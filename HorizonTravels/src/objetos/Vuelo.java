package objetos;



public abstract class Vuelo extends Medio{
    protected Viaje viaje;
	 public Vuelo(double impuestoNacional, double impuestoInter) {
	        super(impuestoNacional, impuestoInter);
	    }


	public abstract double calcularPrecio();
	
}
