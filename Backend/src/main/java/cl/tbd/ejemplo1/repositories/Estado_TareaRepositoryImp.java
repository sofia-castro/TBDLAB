package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Estado_Tarea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Estado_TareaRepositoryImp implements Estado_TareaRepository{

    @Autowired
    private Sql2o sql2o;

    
    @Override
    public Estado_Tarea createEstado_Tarea(Estado_Tarea estado_tarea){
        String query = "insert into estado_tarea (estado) values (:estado)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("estado", estado_tarea.getEstado())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey();
            estado_tarea.setId(insertedid);
            return estado_tarea;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Estado_Tarea getEstado_Tarea(Long id){
        String query = "select * from estado_tarea where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Estado_Tarea.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Estado_Tarea> getEstado_Tareas() {
        String query = "select * from estado_tarea";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Estado_Tarea.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }

     @Override
     public boolean deleteEstado_Tarea(Long id){
         String query = "update estado_tarea set deleted = true where id = :id and deleted = false";
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
    public Estado_Tarea updateEstado_Tarea( Estado_Tarea estado_tarea, Long id){
        String query = "update estado_tarea where id = :id and deleted = false and set (estado) values (:estado)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("estado", estado_tarea.getEstado())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey();
            estado_tarea.setId(insertedid);
            return estado_tarea;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }

     }
    
}
