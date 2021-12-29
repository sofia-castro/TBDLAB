package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Tarea_Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository

public class  Tarea_HabilidadRepositoryImp implements Tarea_HabilidadRepository {

    @Autowired
    private Sql2o sql2o;


    @Override
    public Tarea_Habilidad createTarea_Habilidad(Tarea_Habilidad tarea_habilidad){
        String query = "insert into tarea_habilidad (id_tarea, id_eme_habilidad) values (:id_tarea, :id_eme_habilidad)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("id_tarea", tarea_habilidad.getTareaId())
                            .addParameter("id_eme_habilidad", tarea_habilidad.getEmeHabilidadId())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey();
            tarea_habilidad.setId(insertedid);
            return tarea_habilidad;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public Tarea_Habilidad getTarea_Habilidad(Long id){
        String query = "select * from tarea_habilidad where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Tarea_Habilidad.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Tarea_Habilidad> getAllTarea_Habilidad(){
        String query = "select * from tarea_habilidad";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Tarea_Habilidad.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }

    @Override
    public boolean deleteTarea_Habilidad(Long id){
         String query = "update tarea_habilidad set deleted = true where id = :id and deleted = false";
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
    public Tarea_Habilidad updateTarea_Habilidad(Tarea_Habilidad tarea_habilidad, Long id){
        String query = "update tarea_habilidad where id = :id and deleted = false and set (titulo, descripcion, id_estado, id_emergencia, cant_voluntario) values (:titulo, :descripcion,  :id_estado, :id_emergencia, :cant_voluntario)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("id_tarea", tarea_habilidad.getTareaId())
                            .addParameter("id_eme_habilidad", tarea_habilidad.getEmeHabilidadId())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey();
            tarea_habilidad.setId(insertedid);
            return tarea_habilidad;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}