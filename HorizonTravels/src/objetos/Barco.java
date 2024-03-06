package objetos;

import java.util.List;

public abstract class Barco extends Medio{




	public Barco(double impuestoNacional, double impuestoInter) {
		super(impuestoNacional, impuestoInter);
	}

	public abstract double calcularPrecio();

	
	
	
}
