package  cl.tbd.ejemplo1.models;
public class Ranking {
   
    private Long id;
    private Integer valor;
    private Long id_tarea;
    private Long id_voluntario;
    private Boolean deleted;
    //***** GETTERS *****//
    public Long getId() {
        return id;
    }
    public Integer getvalor(){
        return valor;
    }
    public Long getId_tarea(){
        return id_tarea;
    }
    public Long getId_voluntario(){
        return id_voluntario;
    }
    public Boolean getDeleted() {
        return deleted;
    }

    //***** SETTERS *****//
    public void setId(Long id) {
        this.id = id;
    }

    public void setvalor(Integer valor) {
        this.valor = valor;
    }
    
    public void setId_tarea(Long id_tarea) {
        this.id_tarea= id_tarea;
    }
    
    public void setId_voluntario(Long id_voluntario) {
        this.id_voluntario= id_voluntario;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted= deleted;
    }
}
