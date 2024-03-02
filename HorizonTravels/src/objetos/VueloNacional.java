package objetos;

public class VueloNacional extends Vuelo{



	public VueloNacional(double impuestoNacional) {
		super();
		this.impuestoNacional = impuestoNacional;
	}


	private double impuestoNacional;

	 
	 @Override
	    public double calcularPrecio() {
	        // Precio base + impuesto internacional
	        return 500 + impuestoNacional;
	    }
}
