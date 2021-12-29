package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class TareaRepositoryImp implements TareaRepository{

    @Autowired
    private Sql2o sql2o;


    @Override
    public Tarea createTarea(Tarea tarea){
        String query = "INSERT into tarea (titulo,descripcion,id_estado,id_emergencia,cant_voluntarios) values (:titulo,:descripcion,:id_estado,:id_emergencia,:cant_voluntarios)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query,true).addParameter("titulo", tarea.getTitulo())
                            .addParameter("descripcion", tarea.getDescripcion())
                            .addParameter("id_estado", tarea.getEstadoId())
                            .addParameter("id_emergencia", tarea.getEmergenciaId())
                            .addParameter("cant_voluntarios", tarea.getCant_Voluntarios())
                            .executeUpdate().getKey();
            tarea.setId(insertedid);
            return tarea;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public Tarea getTarea(Long id){
        String query = "select * from tarea where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Tarea.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tarea> getTareas(){
        String query = "select * from tarea";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Tarea.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }

    @Override
    public boolean deleteTarea(Long id){
         String query = "update tarea set deleted = true where id = :id and deleted = false";
         try(Connection conn = sql2o.open()){
            id = conn.createQuery(query).addParameter("id",id).executeUpdate().getKey(Long.class);
         }
         catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
     }

    @Override 
    public Tarea updateTarea(Tarea tarea, Long id){
        String query = "update tarea where id = :id and deleted = false and set (titulo, descripcion, id_estado, id_emergencia, cant_voluntario) values (:titulo, :descripcion,  :id_estado, :id_emergencia, :cant_voluntario) where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("titulo", tarea.getTitulo())
                            .addParameter("descripcion", tarea.getDescripcion())
                            .addParameter("id_estado", tarea.getEstadoId())
                            .addParameter("id_emergencia", tarea.getEmergenciaId())
                            .addParameter("cant_voluntarios", tarea.getCant_Voluntarios())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey(Long.class);
            tarea.setId(insertedid);
            return tarea;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
