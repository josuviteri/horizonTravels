package objetos;

public class VueloNacional extends Vuelo{

	
	public VueloNacional(double impuestoNacional, String codigoMedio) {
		super(impuestoNacional, 0, codigoMedio);
	}
	
	public double getImpuestoNacional() {
		return impuestoNacional;
	}

	 
	 @Override
	    public double calcularPrecio() {
	        // Precio base + impuesto internacional
	        return getViaje().getPrecioBase() + impuestoNacional;
	    }
}
