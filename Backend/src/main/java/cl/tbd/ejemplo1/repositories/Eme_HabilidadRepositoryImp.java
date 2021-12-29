package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Eme_Habilidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class Eme_HabilidadRepositoryImp implements Eme_HabilidadRepository {
    @Autowired
     private Sql2o sql2o;

    
    @Override
    public Eme_Habilidad createEme_Habilidad(Eme_Habilidad eme_habilidad){
        String query = "insert into voluntario (id_emergencia,id_habilidad) values (:id_emergencia,:id_habilidad)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("id_emergencia", eme_habilidad.getIdEmergencia())
                            .addParameter("id_habilidad", eme_habilidad.getIdHabilidad())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey();
            eme_habilidad.setId(insertedid);
            return eme_habilidad;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Eme_Habilidad getEme_Habilidad(Long id){
        String query = "select * from eme_habilidad where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Eme_Habilidad.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public  List<Eme_Habilidad> getEme_Habilidades() {
        String query = "select * from eme_habilidad";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Eme_Habilidad.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteEme_Habilidad(Long id){
        String query = "update eme_habilidad set deleted = true where id = :id and deleted = false";
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
    public Eme_Habilidad updateEme_Habilidad(Eme_Habilidad eme_habilidad, Long id){
        String query = "update eme_habilidad set (id_emergencia, id_habilidad) values (:id_emergencia, :id_habilidad ) where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("id_emergencia", eme_habilidad.getIdEmergencia())
                            .addParameter("id_habilidad", eme_habilidad.getIdHabilidad())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey(Long.class);
            eme_habilidad.setId(insertedid);
            return eme_habilidad;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    
}
