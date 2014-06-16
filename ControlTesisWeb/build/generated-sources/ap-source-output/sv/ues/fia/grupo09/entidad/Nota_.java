package sv.ues.fia.grupo09.entidad;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.ues.fia.grupo09.entidad.Alumno;
import sv.ues.fia.grupo09.entidad.Etapa;
import sv.ues.fia.grupo09.entidad.NotaPK;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-06-15T17:45:36")
@StaticMetamodel(Nota.class)
public class Nota_ { 

    public static volatile SingularAttribute<Nota, Float> notaalumno;
    public static volatile SingularAttribute<Nota, Etapa> etapa;
    public static volatile SingularAttribute<Nota, NotaPK> notaPK;
    public static volatile SingularAttribute<Nota, Alumno> alumno;

}