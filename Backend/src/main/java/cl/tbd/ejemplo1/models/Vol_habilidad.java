package  cl.tbd.ejemplo1.models;

public class Vol_habilidad{
   
    private Long id;
    private Long id_voluntario;
    private Long id_habilidad;
    private boolean deleted;

    //***** GETTERS *****//

    public Long getId(){
        return id;
    }

    public Long getVoluntarioId(){
        return id_voluntario;
    }

    public Long getHabilidadId(){
        return id_habilidad;
    }

    public boolean getDeleteEstado(){
        return deleted;
    }

    //***** SETTERS *****//
    public void setId(Long id){
        this.id = id;
    }

    public void setVoluntarioId(Long id){
        this.id_voluntario = id;
    }

    public void setHabilidadId(Long id){
        this.id_habilidad = id;
    }

    public void setDelete(boolean deleted){
        this.deleted = deleted;
    }
}