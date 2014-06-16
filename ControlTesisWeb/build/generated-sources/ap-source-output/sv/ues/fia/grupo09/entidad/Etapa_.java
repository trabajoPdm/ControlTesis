package sv.ues.fia.grupo09.entidad;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import sv.ues.fia.grupo09.entidad.Nota;

@Generated(value="EclipseLink-2.3.2.v20111125-r10461", date="2014-06-15T18:21:26")
@StaticMetamodel(Etapa.class)
public class Etapa_ { 

    public static volatile SingularAttribute<Etapa, Date> fechadefinalizacionetapa;
    public static volatile ListAttribute<Etapa, Nota> notaList;
    public static volatile SingularAttribute<Etapa, String> idetapa;
    public static volatile SingularAttribute<Etapa, Date> fechadeinicioetapa;

}