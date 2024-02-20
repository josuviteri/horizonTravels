package objetos;

public class BarcoNacional extends Barco{

	private double impuestoNacional;


	public BarcoNacional(int id, String origen, String destino, String fecha, int duracion, String aerolinea,
			double impuestoNacional) {
		super(id, origen, destino, fecha, duracion, aerolinea);
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
