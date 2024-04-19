package objetos;

public class BarcoInter extends Barco{


	public BarcoInter(double impuestoInter, String codigoMedio) {
		super(0, impuestoInter, codigoMedio);
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
