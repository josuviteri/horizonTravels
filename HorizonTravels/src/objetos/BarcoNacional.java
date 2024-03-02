package objetos;

public class BarcoNacional extends Barco{

	private double impuestoNacional;



	
	public BarcoNacional(double impuestoNacional) {
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
