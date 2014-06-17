package sv.fia.ues.controltesisw;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EtapaEliminarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_etapa_eliminar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.etapa_eliminar, menu);
		return true;
	}

}
