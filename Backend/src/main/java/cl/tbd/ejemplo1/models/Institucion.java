package cl.tbd.ejemplo1.models;
public class Institucion{
 
    private Long id;
    private String nombre_institucion;
    private String direccion_institucion;
    private String telefono_institucion;
    private String dominio_institucion;
    private boolean deleted;

    //***** GETTERS *****//

    public Long getId() {
        return id;
    }

    public String getNombre_institucion() {
        return nombre_institucion;
    }

    public String getDireccion_institucion() {
        return direccion_institucion;
    }

    public String getDominio_institucion() {
        return dominio_institucion;
    }

    public String getTelefono(){
        return telefono_institucion;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    //***** SETTERS *****//

    public void setId(Long id){
        this.id = id;
    }

    public void setNombre_institucion(String nombre_institucion){
        this.nombre_institucion = nombre_institucion;
    }

    public void setDireccion_institucion(String direccion_institucion){
        this.direccion_institucion = direccion_institucion;
    }

    public void setDominio_institucion(String dominio_institucion){
        this.dominio_institucion = dominio_institucion;
    }

    public void setTelefono_institucion(String telefono_institucion){
        this.telefono_institucion = telefono_institucion;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


}