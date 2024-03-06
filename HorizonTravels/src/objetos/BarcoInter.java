package objetos;

public class BarcoInter extends Barco{


	public BarcoInter(double impuestoInter) {
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
