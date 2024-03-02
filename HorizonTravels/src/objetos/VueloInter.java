package objetos;

public class VueloInter extends Vuelo{

	private double impuestoInter;




	public VueloInter(double impuestoInter) {
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
