package objetos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Viaje{
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
//  @Override
//  public int compareTo(Viaje otroViaje) {
//      return Double.compare(this.company.getMedio().calcularPrecio(this), otroViaje.company.getMedio().calcularPrecio(otroViaje));
//  }
  
//  @Override
//  public int compareTo(Viaje otroViaje) {
//      return Double.compare(this.getFecha(), otroViaje.getFecha());
//  }


	    public static Comparator<Viaje> byPrecio() {
	        return new Comparator<Viaje>() {
	            @Override
	            public int compare(Viaje v1, Viaje v2) {
	                return Double.compare(v1.getCompany().getMedio().calcularPrecio(v1), v2.getCompany().getMedio().calcularPrecio(v2));
	            }
	        };
	    }

	    public static Comparator<Viaje> byFecha() {
	        return new Comparator<Viaje>() {
	            @Override
	            public int compare(Viaje v1, Viaje v2) {
	                return Double.compare(v1.getFecha(), v2.getFecha());
	            }
	        };
	    }

//		@Override
//		public int compareTo(Viaje o) {
//			// TODO Auto-generated method stub
//			return 0;
//		}

	
}
