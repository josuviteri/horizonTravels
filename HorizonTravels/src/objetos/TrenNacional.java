package objetos;

public class TrenNacional extends Tren{
	

	private double impuestoNacional;

	public TrenNacional(int id, String origen, String destino, String fecha, int duracion, String compania) {
		super(id, origen, destino, fecha, duracion, compania);
		// TODO Auto-generated constructor stub
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
