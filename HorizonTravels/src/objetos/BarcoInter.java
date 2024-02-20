package objetos;

public class BarcoInter extends Barco{


	private double impuestoInter;

	public BarcoInter(int id, String origen, String destino, String fecha, int duracion, String compania) {
		super(id, origen, destino, fecha, duracion, compania);
		// TODO Auto-generated constructor stub
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
