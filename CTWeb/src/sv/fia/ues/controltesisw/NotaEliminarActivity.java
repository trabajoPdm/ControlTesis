package sv.fia.ues.controltesisw;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class NotaEliminarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nota_eliminar);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nota_eliminar, menu);
		return true;
	}

}
