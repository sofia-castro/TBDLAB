package cl.tbd.ejemplo1.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import cl.tbd.ejemplo1.models.Voluntarios;


@Repository
public class VoluntarioRepositoryImp implements VoluntarioRepository {

    @Autowired
     private Sql2o sql2o;

    
    @Override
    public Voluntarios createVoluntario(Voluntarios voluntario){
        String query = "INSERT INTO voluntario (nombre,disponibilidad,telefono,direccion,correo_electronico) values (:nombre,:disponibilidad,:telefono,:direccion,:correo_electronico)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query,true)
                            .addParameter("disponibilidad", voluntario.getDisponibilidad())
                            .addParameter("nombre", voluntario.getNombre())
                            .addParameter("telefono", voluntario.getTelefono())
                            .addParameter("direccion", voluntario.getDireccion())
                            .addParameter("correo_electronico", voluntario.getCorreo_electronico())
                            .executeUpdate().getKey(Long.class);
        
            voluntario.setId(insertedid);
            return voluntario;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Voluntarios getVoluntario(Long id){
        String query = "select * from voluntario where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Voluntarios.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Voluntarios> getAllVoluntarios() {
        String query = "select * from voluntario";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Voluntarios.class);
         }
         catch (Exception e){
             System.out.println(e.getMessage());
             return null;
         }
     }

    @Override
    public boolean deleteVoluntario(Long id){
        String query = "update voluntario set deleted=true where id=:id and deleted=false";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query).addParameter("id",id).executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Voluntarios updateVoluntario( Voluntarios voluntario,Long id){
        String query = "update voluntario set (nombre,disponibilidad,telefono,direccion,correo_electronico) values (:nombre,:disponibilidad,:telefono,:direccion,:correo_electronico) where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query,true).addParameter("nombre", voluntario.getNombre())
            .addParameter("id",voluntario.getId())
            .addParameter("disponibilidad", voluntario.getDisponibilidad())
            .addParameter("telefono", voluntario.getTelefono())
            .addParameter("direccion", voluntario.getDireccion())
            .addParameter("correo_electronico", voluntario.getCorreo_electronico())
            .executeUpdate().getKey(Long.class);
            voluntario.setId(insertedid);
        
            return voluntario;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

     }
    
}
