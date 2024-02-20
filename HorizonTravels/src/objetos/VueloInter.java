package objetos;

public class VueloInter extends Vuelo{

	private double impuestoInter;

	public VueloInter(int id, String origen, String destino, String fecha, int duracion, String aerolinea) {
		super(id, origen, destino, fecha, duracion, aerolinea);
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
