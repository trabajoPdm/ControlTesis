package sv.fia.ues.controltesisw;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class NotaInsertarActivity extends Activity {

	 private EditText Carnet;
	 private EditText idEtapa;
	 private EditText notaAlumno;
	 
	  
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nota_insertar);
		 idEtapa=	(EditText)findViewById(R.id.editidEtapa);
	Carnet=(EditText)findViewById(R.id.editCarnet);
	notaAlumno=(EditText)findViewById(R.id.editnotaAlumno);
	notaAlumno.setText("0");
	
	
	}
	 
	 
	 public void insertarNota(View v){//esta en el layaut
		 
		 String resultado;
		
		 if(Character.isDigit(notaAlumno.getText().charAt(0)))
			{ 
			Nota nota =new Nota();
			nota.setIdEtapa(idEtapa.getText().toString());
			nota.setCarnet(Carnet.getText().toString());
			nota.setNotaalumno(Float.valueOf(notaAlumno.getText().toString()));
					if(nota.getNotaalumno()>= 0 && nota.getNotaalumno()<=10){
						
							if(verificarDatos(nota)){
							   //conexion
							}else
								resultado="Error al ingresar los datos, todos los campos deben estar completados";
				       }
					else
					resultado="La Nota es Invalidad debe estar Entre 0.0 y 10.0";
			
			}
		 else
			 resultado="La Nota DEBE ser un Valor Numerico Entre 0.0 y 10.0";
		 
			TextView mensaje=new TextView(this);
	       // mensaje.setText(resultado);
	        Dialog d=new Dialog(this);
	        d.setTitle("Resultado de la Operacion");
	        d.setContentView(mensaje);
	        d.show();
   }
		
	

	 public void limpiarTexto(View v){
			idEtapa.setText("");
			Carnet.setText("");
			notaAlumno.setText("0");
			
	 }
	 
	 @SuppressLint("NewApi")
	 public boolean verificarDatos(Nota n){
	 	if(n.getCarnet().isEmpty() || n.getIdEtapa().isEmpty() ){
	 		return false;
	 	}else
	 		return true;
	 }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nota_insertar, menu);
		return true;
	}

}
