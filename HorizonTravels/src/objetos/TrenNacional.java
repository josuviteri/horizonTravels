package objetos;

public class TrenNacional extends Tren{
	

	private double impuestoNacional;


	public TrenNacional(double impuestoNacional) {
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
