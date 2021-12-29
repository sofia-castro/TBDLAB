package  cl.tbd.ejemplo1.models;

public class Estado_Tarea{

    private Long id;
    private boolean estado;
    private boolean deleted;


    //***** GETTERS *****/

    public Long getId(){
        return id;
    }

    public boolean getEstado(){
        return estado;
    }

    public boolean getDelete(){
        return deleted;
    }

    //***** SETTERS *****//

    public void setId(Long id){
        this.id = id;
    }

    public void setEstado(boolean status){
        this.estado = status;
    }

    public void setDelete(boolean deleted){
        this.deleted = deleted;
    }
}