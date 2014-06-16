package sv.ues.fia.grupo09.entidad;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.ues.fia.grupo09.entidad.Nota;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-06-15T17:45:36")
@StaticMetamodel(Alumno.class)
public class Alumno_ { 

    public static volatile SingularAttribute<Alumno, String> nombre;
    public static volatile SingularAttribute<Alumno, String> carnet;
    public static volatile SingularAttribute<Alumno, String> direccionalumno;
    public static volatile SingularAttribute<Alumno, String> apellidoalumno;
    public static volatile SingularAttribute<Alumno, String> emailalumno;
    public static volatile ListAttribute<Alumno, Nota> notaList;
    public static volatile SingularAttribute<Alumno, String> telefonoalumno;
    public static volatile SingularAttribute<Alumno, String> sexoalumno;

}