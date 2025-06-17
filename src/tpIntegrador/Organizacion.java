package tpIntegrador;

import java.util.List;
import java.util.ArrayList;
import tpIntegrador.enums.TipoDeOrganizacion;

public class Organizacion {
    private String nombre;
    private Ubicacion ubicacion;
    private TipoDeOrganizacion tipo; // (salud, educativa, cultural, asistencia)
    private Integer cantDeEmpleados;
    private List<ZonaDeCobertura> zonasRegistradas;


    public Organizacion (String nombre, Ubicacion ubicacion, TipoDeOrganizacion tipo, Integer cantDeEmpleados) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.cantDeEmpleados = cantDeEmpleados;
        this.zonasRegistradas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Ubicacion getUbicacion() {
        return ubicacion; 
    }

    public TipoDeOrganizacion getTipoDeOrganizacion() {
        return tipo;
    }

    public Integer getCantDeEmpleados() {
        return cantDeEmpleados;
    }

    public List<ZonaDeCobertura> getZonasRegistradas() {
         return zonasRegistradas;
    }
}
