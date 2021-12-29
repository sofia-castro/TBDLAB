package  cl.tbd.ejemplo1.models;
public class Emergencia{
    private Long id;
    private String titulo;
    private String direccion;
    private String descripcion;
    private Long id_institucion;
    private Boolean activo;
    private boolean deleted;

    //***** GETTERS *****//
    public Long getId() {
        return id;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public Long getInstitucionId(){
        return id_institucion;
    }
    public Boolean getActivo(){
        return activo;
    }
    public boolean getDeleteEstado(){
        return deleted;
    }
    //***** SETTERS *****//
    public void setId(Long id){
        this.id = id;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public void setInstitucionId(Long id_institucion){
        this.id_institucion = id_institucion;
    }
    public void setActivo(boolean activo){
        this.activo=activo;
    }
    public void setDeleteEstado(boolean deleted){
        this.deleted = deleted;
    }
}    


