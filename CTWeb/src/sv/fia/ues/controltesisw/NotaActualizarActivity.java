package sv.fia.ues.controltesisw;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NotaActualizarActivity extends Activity {

	EditText idEtapa;
	EditText Carnet;
	EditText notaAlumno;
	BDG9 helper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_nota_actualizar);
	
	idEtapa=(EditText)findViewById(R.id.editidEtapa);
	Carnet=(EditText)findViewById(R.id.editCarnet);
	notaAlumno=(EditText)findViewById(R.id.editnotaAlumno);
	notaAlumno.setText("0");
	helper=new BDG9(this);
	}
	public void ActualizarNota(View v){
		Nota nota=new Nota();
		String resultado="";
		
		
		 if(Character.isDigit(notaAlumno.getText().charAt(0)))
			{ 
				nota.setIdEtapa(idEtapa.getText().toString());
				nota.setCarnet(Carnet.getText().toString());
				nota.setNotaalumno(Float.valueOf(notaAlumno.getText().toString()));
				
				if(nota.getNotaalumno()>= 0 && nota.getNotaalumno()<=10){
					if(verificarDatos(nota)){
						helper.abrir();
						resultado=helper.actualizarNota(nota);
						helper.cerrar();
					}else
						resultado="Error al ingresar los datos, todos los campos deben estar completados";
				}
				else
					resultado="Nota invalida digite por favor una nota valida Entre 0.0 y 10.0";	
			}
		 else
			 resultado="La Nota DEBE ser un Valor Numerico Entre 0.0 y 10.0";
		
		TextView mensaje=new TextView(this);
		mensaje.setText(resultado);
		Dialog d=new Dialog(this);
		d.setTitle("Resultado de la Operacion");
		d.setContentView(mensaje);
		d.show();
		
	}
	public void limpiar(View v) {
		idEtapa.setText("");
		Carnet.setText("");
		notaAlumno.setText("0");
	}
	@SuppressLint("NewApi")
	public boolean verificarDatos(Nota n){
		if( n.getCarnet().isEmpty() || n.getIdEtapa().isEmpty() ){
			return false;
		}else
			return true;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nota_actualizar, menu);
		return true;
	}

}
