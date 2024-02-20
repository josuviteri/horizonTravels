package objetos;

public class Puesto {
    private int numero;
    private boolean disponible;

    // Constructor
    public Puesto(int numero, boolean disponible) {
        this.numero = numero;
        this.disponible = disponible;
    }

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
