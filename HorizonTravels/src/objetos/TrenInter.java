package objetos;

public class TrenInter extends Tren{
	 
	private double impuestoInter;


	 


	public TrenInter(double impuestoInter) {
		super();
		this.impuestoInter = impuestoInter;
	}

	public double getImpuestoNacional() {
		return impuestoInter;
	}

	public void setImpuestoNacional(double impuestoInter) {
		this.impuestoInter = impuestoInter;
	}




	 @Override
	    public double calcularPrecio() {
	        // Precio base + impuesto internacional
	        return 500 + impuestoInter;
	    }

}
