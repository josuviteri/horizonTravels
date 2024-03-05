package objetos;

import java.util.List;

public abstract class Tren extends Medio{
	
	protected Company company;



	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}



	public abstract double calcularPrecio();

}
