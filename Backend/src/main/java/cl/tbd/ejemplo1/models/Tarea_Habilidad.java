package cl.tbd.ejemplo1.models;

public class Tarea_Habilidad{
   
    private Long id;
    private Long id_tarea;
    private Long id_eme_habilidad;
    private boolean deleted;

    //***** GETTERS *****//
    public Long getId(){
        return id;
    }

    public Long getTareaId(){
        return id_tarea;
    }

    public Long getEmeHabilidadId(){
        return id_eme_habilidad;
    }

    public boolean getDelete(){
        return deleted;
    }

    //***** SETTERS *****//
    public void setId(Long id){
        this.id = id;
    }

    public void setTareaId(Long id_tarea){
        this.id_tarea = id_tarea;
    }

    public void setEmeHabilidadId(Long id_eme_habilidad){
        this.id_eme_habilidad = id_eme_habilidad;
    }

    public void setDelete(boolean deleted){
        this.deleted = deleted;
    }

}
