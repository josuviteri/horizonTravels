package objetos;

public class BarcoInter extends Barco{


	private double impuestoInter;



	public BarcoInter(double impuestoInter) {
		super();
		this.impuestoInter = impuestoInter;
	}

	public double getImpuestoInter() {
		return impuestoInter;
	}

	public void setImpuestoInter(double impuestoInter) {
		this.impuestoInter = impuestoInter;
	}
	
	 @Override
	    public double calcularPrecio() {
	        // Precio base + impuesto internacional
	        return 500 + impuestoInter;
	    }

}
