package objetos;

public class TrenInter extends Tren{
	 
	private double impuestoInter;

	public TrenInter(int id, String origen, String destino, String fecha, int duracion, String compania) {
		super(id, origen, destino, fecha, duracion, compania);
		// TODO Auto-generated constructor stub
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
