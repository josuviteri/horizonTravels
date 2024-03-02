package objetos;

public class Estacion {
	private String codigo;
	private String nombre;
	private String ciudad;
	private String pais;
	
	public Estacion(String codigo, String nombre, String ciudad, String pais) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.pais = pais;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}
	
	
	
}
