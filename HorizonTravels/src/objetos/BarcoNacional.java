package objetos;

public class BarcoNacional extends Barco{


	public BarcoNacional(double impuestoNacional) {
		super(impuestoNacional, 0);
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
