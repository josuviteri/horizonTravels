package objetos;

public class TrenInter extends Tren{


	public TrenInter(double impuestoInter) {
		super(0, impuestoInter);
	}

	public double getImpuestoInter() {
		return impuestoInter;
	}

	 @Override
	    public double calcularPrecio() {
	        // Precio base + impuesto internacional
	        return getViaje().getPrecioBase() + impuestoInter ;
	    }

}
