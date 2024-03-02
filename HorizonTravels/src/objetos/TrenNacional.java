package objetos;

public class TrenNacional extends Tren{
	

	private double impuestoNacional;


	public TrenNacional(double impuestoNacional) {
		super();
		this.impuestoNacional = impuestoNacional;
	}

	public double getImpuestoNacional() {
		return impuestoNacional;
	}

	public void setImpuestoNacional(double impuestoNacional) {
		this.impuestoNacional = impuestoNacional;
	}

	 @Override
	    public double calcularPrecio() {
	        // Precio base + impuesto internacional
	        return 500 + impuestoNacional;
	    }
}
