package tpIntegrador;

public class Usuario {
	private String nombre;
	private boolean esEspecialista;
	private UsuarioState categoria;
	
	public Usuario(String nombre, boolean esEspecialista) {
		this.nombre = nombre;
		this.esEspecialista = esEspecialista;
		if(esEspecialista) {
			categoria = new UsuarioExperto();
		} else {
			categoria = new UsuarioBasico();
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean esEspecialista() {
		return esEspecialista;
	}
	
	public String getCategoria() {
		return categoria.getCategoriaPara(this);
	}
}
