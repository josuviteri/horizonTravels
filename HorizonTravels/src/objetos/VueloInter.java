package objetos;

public class VueloInter extends Vuelo{



	public VueloInter(double impuestoInter) {
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
