package objetos;

public abstract class Medio {
    private Viaje viaje;
    protected String codigoMedio;


	protected double impuestoNacional;
    protected double impuestoInter;
    
    public Medio(double impuestoNacional, double impuestoInter) {
        this.impuestoNacional = impuestoNacional;
		this.impuestoInter = impuestoInter;
    }

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public double getImpuestoNacional() {
		return impuestoNacional;
	}

	public double getImpuestoInter() {
		return impuestoInter;
	}
	
    public String getCodigoMedio() {
		return codigoMedio;
	}

	public void setCodigoMedio(String codigoMedio) {
		this.codigoMedio = codigoMedio;
	}

	public abstract double calcularPrecio();
	
}
