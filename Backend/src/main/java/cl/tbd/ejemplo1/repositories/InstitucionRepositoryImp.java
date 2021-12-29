package cl.tbd.ejemplo1.repositories;
import cl.tbd.ejemplo1.models.Institucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class InstitucionRepositoryImp implements InstitucionRepository{

    @Autowired
    private Sql2o sql2o;

    @Override
    public Institucion createInstitucion(Institucion institucion){
        String query = "insert into institucion (nombre_institucion,direccion_institucion,telefono_institucion,dominio_institucion) values (:nombre_institucion,:direccion_institucion,:telefono_institucion,:dominio_institucion)";
        try(Connection conn = sql2o.open()){
            Long insertedid = (Long) conn.createQuery(query).addParameter("nombre_institucion", institucion.getNombre_institucion())
                            .addParameter("direccion_institucion", institucion.getDireccion_institucion())
                            .addParameter("telefono_institucion", institucion.getTelefono())
                            .addParameter("dominio_institucion", institucion.getDominio_institucion())
                            .addParameter("deleted",false)
                            .executeUpdate().getKey();
            institucion.setId(insertedid);
            return institucion;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Institucion getInstitucion(Long id){
        String query = "select * from institucion where id = :id and deleted = false";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).addParameter("id",id).executeAndFetchFirst(Institucion.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<Institucion> getInstituciones() {
        String query = "select * from institucion";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(query).executeAndFetch(Institucion.class);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteInstitucion(Long id){
        String query = "update institucion set deleted = true where id = :id and deleted = false";
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
     public Institucion updateInstitucion(Institucion institucion, Long id)
     {
          String query = "update institucion (nombre_institucion,direccion_institucion,telefono_institucion,dominio_institucion) values (:nombre_institucion,:direccion_institucion,:telefono_institucion,:dominio_institucion)";
        try(Connection conn = sql2o.open()){
            conn.createQuery(query).addParameter("nombre_institucion", institucion.getNombre_institucion())
            .addParameter("direccion_institucion", institucion.getDireccion_institucion())
            .addParameter("telefono_institucion", institucion.getTelefono())
            .addParameter("dominio_institucion", institucion.getDominio_institucion())
            .executeUpdate();
            return institucion;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }


     }

}
