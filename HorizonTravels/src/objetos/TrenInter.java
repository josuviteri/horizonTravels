package objetos;

public class TrenInter extends Tren{


	public TrenInter(double impuestoInter, String codigoMedio) {
		super(0, impuestoInter, codigoMedio);
	}

	public double getImpuestoInter() {
		return impuestoInter;
	}

	 @Override
	    public double calcularPrecio(Viaje viaje) {
	        // Precio base + impuesto internacional
	        return getViaje().getPrecioBase() + impuestoInter ;
	    }

}
