package objetos;

public class BarcoNacional extends Barco{


	public BarcoNacional(double impuestoNacional, String codigoMedio) {
		super(impuestoNacional, 0, codigoMedio);
	}

	public double getImpuestoNacional() {
		return impuestoNacional;
	}


	 @Override
	    public double calcularPrecio(Viaje viaje) {
	        // Precio base + impuesto internacional
	        return getViaje().getPrecioBase() + impuestoNacional;
	    }
	
	
}
