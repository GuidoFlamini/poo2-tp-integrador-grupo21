package tpIntegrador;

import java.util.List;
import java.util.ArrayList;
import tpIntegrador.enums.TipoDeOrganizacion;

public class Organizacion implements Observer {  // implements o extens??
    private String nombre; 
    private Ubicacion ubicacion;
    private TipoDeOrganizacion tipo; // (salud, educativa, cultural, asistencia)
    private int cantDeEmpleados;
    private List<Muestra> historialDeMuestras;
    private List<ZonaDeCobertura> zonasRegistradas;
    private FuncionalidadExterna funcionalidadNuevaMuestra;
    private FuncionalidadExterna funcionalidadValidacion;


    public Organizacion (String nombre, Ubicacion ubicacion, TipoDeOrganizacion tipo, int cantDeEmpleados) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.tipo = tipo;
        this.cantDeEmpleados = cantDeEmpleados;
        this.zonasRegistradas = new ArrayList<>();
        this.historialDeMuestras = new ArrayList<>();
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

    public int getCantDeEmpleados() {
        return cantDeEmpleados;
    }

    public List<ZonaDeCobertura> getZonasRegistradas() {
         return zonasRegistradas;
    }

    public List<Muestra> getHistorialDeMuestras() {
         return historialDeMuestras;
    }


    @Override
    public void nuevaMuestra(Muestra muestra) {
    //    if (funcionalidadNuevaMuestra != null)  // Verifica que la funcionalidad de validación esté configurada
            funcionalidadNuevaMuestra.nuevoEvento(this, null, muestra); // zona puede pasarse si se modela
    }

    @Override
    public void muestraVerificada(Muestra muestra) {
        if (//funcionalidadValidacion != null && 
            muestra.esMuestraVerificada())
            funcionalidadValidacion.nuevoEvento(this, null, muestra);
    }


    public void registrarEnZona(ZonaDeCobertura zona) {
        zonasRegistradas.add(zona);
    }

    public void registrarMuestra(Muestra muestra) {
        historialDeMuestras.add(muestra);
    }


    //public void agregarObservador(Observer observador) {
    //    observers.add(observador);
    //}
    // Se tiene que agregar este metodo en zona de Cobertura.




    public void agregarEmpleados(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        this.cantDeEmpleados += cantidad;
    }

    public void reducirEmpleados(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        if (cantidad > this.cantDeEmpleados) {
            throw new IllegalArgumentException("No se puede reducir más empleados de los que existen");
        }
        this.cantDeEmpleados -= cantidad;
    }
}
