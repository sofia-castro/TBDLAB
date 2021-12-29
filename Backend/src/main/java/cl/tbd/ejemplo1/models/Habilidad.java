package  cl.tbd.ejemplo1.models;

public class Habilidad{
    private Long id;
    private String nombreH;
    private String descripcion;
    private boolean deleted;

    //***** GETTERS *****//

    public Long getId(){
        return id;
    }

    public String getNombreH(){
        return nombreH;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public boolean getDeleted(){
        return deleted;
    }
    //***** SETTERS *****//
    public void setId(Long id){
        this.id = id;
    }

    public void setNombreH(String nombreH){
        this.nombreH = nombreH;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    public void setDelete(boolean deleted){
        this.deleted = deleted;
    }

}
