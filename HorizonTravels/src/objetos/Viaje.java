package objetos;

import java.util.ArrayList;
import java.util.List;

public class Viaje implements Comparable<Viaje>{
	protected String codigo;
	protected Long fecha;
	protected Estacion origen;
	protected Estacion destino;
	protected Company company;
	protected double precioBase;
	protected List<Asiento> asientos;
	
	public Viaje(String codigo, Long fecha, Estacion origen, Estacion destino, Company company,
			double precioBase, List<Asiento> asientos) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.origen = origen;
		this.destino = destino;

		this.company = company;
		this.precioBase = precioBase;
		this.asientos = new ArrayList<>();
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	//comparar precio completo 
    @Override
    public int compareTo(Viaje otroViaje) {
        return Double.compare(this.company.getMedio().calcularPrecio(this), otroViaje.company.getMedio().calcularPrecio(otroViaje));
    }

	
}
