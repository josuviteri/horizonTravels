package objetos;

import java.util.List;

public class Viaje {
	protected String codigo;
	protected Long fecha;
	protected Estacion origen;
	protected Estacion destino;
	protected Medio medio;
	protected double precioBase;
	protected List<Asiento> asientos;
	
	public Viaje(String codigo, Long fecha, Estacion origen, Estacion destino, Medio medio,
			double precioBase, List<Asiento> asientos) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;

		this.medio = medio;
		this.precioBase = precioBase;
		this.asientos = asientos;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	public Estacion getOrigen() {
		return origen;
	}

	public void setOrigen(Estacion origen) {
		this.origen = origen;
	}

	public Estacion getDestino() {
		return destino;
	}

	public void setDestino(Estacion destino) {
		this.destino = destino;
	}

	public Medio getMedio() {
		return medio;
	}

	public void setMedio(Medio medio) {
		this.medio = medio;
	}

	public double getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(double precioBase) {
		this.precioBase = precioBase;
	}

	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}
	
	

	
}
