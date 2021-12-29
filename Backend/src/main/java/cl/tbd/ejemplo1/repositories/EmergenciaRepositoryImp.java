package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Emergencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class EmergenciaRepositoryImp implements EmergenciaRepository{
    @Autowired
    private Sql2o sql2o;

    
    @Override
    public Emergencia createEmergencia(Emergencia emergencia){
        String query = "INSERT INTO emergencia (titulo,descripcion,direccion,id_institucion,activo) values (:titulo,:direccion,:descripcion,:id_institucion,:activo)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query,true).addParameter("titulo", emergencia.getTitulo())
                            .addParameter("descripcion", emergencia.getDescripcion())
                            .addParameter("id_institucion", emergencia.getInstitucionId())
                            .addParameter("direccion", emergencia.getDireccion())
                            .addParameter("activo", emergencia.getActivo())
                            .executeUpdate().getKey();
            emergencia.setId(insertedid);
            return emergencia;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public Emergencia getEmergencia(Long id){
        String query = "select * from emergencia where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Emergencia.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public List<Emergencia> getEmergencias() {
        String query = "select * from emergencia";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Emergencia.class);
         }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteEmergencia(Long id){
        String query = "update emergencia set deleted = true where id = :id and deleted = false";
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
    public Emergencia updateEmergencia( Emergencia emergencia,Long id){
        String query = "update emergencia set (titulo,descripcion,direccion,id_institucion,activo) values (:titulo,:direccion,:descripcion,:id_institucion,:activo) where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query).addParameter("titulo", emergencia.getTitulo())
            .addParameter("descripcion", emergencia.getDescripcion())
            .addParameter("id_institucion", emergencia.getInstitucionId())
            .addParameter("direccion", emergencia.getDireccion())
            .addParameter("activo", emergencia.getActivo())
            .executeUpdate();
        
            return emergencia;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}