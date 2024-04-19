package objetos;

import java.util.List;

public abstract class Barco extends Medio{




	public Barco(double impuestoNacional, double impuestoInter, String codigoMedio) {
		super(impuestoNacional, impuestoInter, codigoMedio);
	}

	public abstract double calcularPrecio();

	
	
	
}
