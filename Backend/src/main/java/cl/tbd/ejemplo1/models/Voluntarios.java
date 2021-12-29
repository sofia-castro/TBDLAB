package cl.tbd.ejemplo1.models;
public class Voluntarios{
 
    private Long id;
    private String nombre;
    private Boolean disponibilidad;
    private String telefono;
    private String direccion;
    private String correo_electronico;
    private Boolean deleted;    
    
    //***** GETTERS *****//
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public String getTelefono(){
        return telefono;
    }

    public String getDireccion(){
        return direccion;
    }

    public String getCorreo_electronico(){
        return correo_electronico;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Boolean getDeleted(){
        return deleted;
    }

    //***** SETTERS *****//
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDisponibilidad(Boolean disponibilidad) {

        this.disponibilidad = disponibilidad;
    }
    
    public void setTelefono(String telefono){
        this.telefono= telefono;
    }
    
    public void setDireccion(String direccion){
        this.direccion=direccion;
    }
   
    public void setCorreo_electronico(String correo){
        this.correo_electronico = correo;
    }
   
    public void setDeleted(Boolean deleted){
        this.deleted = deleted;
    }
    
}