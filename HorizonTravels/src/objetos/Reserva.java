package objetos;

public class Reserva {

	protected int id;
    protected String origen;
    protected String destino;
    protected String fecha;
    protected int duracion;

    public Reserva(int id, String origen, String destino, String fecha, int duracion) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.duracion = duracion;
    }
    
    public Reserva() {

    }
    
    
}
