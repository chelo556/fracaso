package com.example.prototipo;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import android.util.SparseArray;
import android.widget.ExpandableListView;

public class Actividad2 extends Activity {

	  // more efficient than HashMap for mapping integers to objects
	  SparseArray<Group> groups = new SparseArray<Group>();
	  
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actividad2);

		//obteniendo dirección de la actividad anterior
		Bundle bundle = getIntent().getExtras();
		String mytxt = bundle.getString("direccion");
	    
		//haciendo petición post
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
        httpHandler handler = new httpHandler();
        
        //resultado petición post
        String json = handler.indice("http://"+mytxt);
		//Toast.makeText(this, text, Toast.LENGTH_LONG).show();	
		
		//Parseando datos
		createData(json);

		// presentando datos
	    ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
	    MyExpandableListAdapter adapter = new MyExpandableListAdapter(this,groups);
	    listView.setAdapter(adapter);
		
	    
	}

	  public void createData(String json) {  
		  try{
			 
			  JSONObject jObject = new JSONObject(json);
			  
			  JSONArray jArray = jObject.optJSONArray("articulos");
			
			  Group group = new Group("articulos");
			  int aux= jArray.length();
			
			  for (int i=0; i < aux; i++){
			
		          JSONObject oneObject = jArray.getJSONObject(i);
		          
		          // Pulling items from the array
		          String titulo = oneObject.optString("titulo").toString();		       
		          
		          //String oneObjectsItem2 = oneObject.getString("fecha");
		          //Toast.makeText(this, oneObjectsItem2, Toast.LENGTH_LONG).show();
		          
		          titulo= titulo.trim();
		          char[] caracteres = titulo.toCharArray();
		          caracteres[0] = Character.toUpperCase(caracteres[0]);
		          titulo = new String(caracteres);
		          
		          group.children.add(titulo);
		          //break;
			  }
			  groups.append(0, group); 
		  	}catch(Exception e){Toast.makeText(this, "Intentelo de nuevo", 1).show();}		  
		  	
		  	/*
		      Group group2 = new Group("Test ");
		      for (int i = 0; i < 5; i++) {
		        group2.children.add(json);
		      }
		      groups.append(1, group2);
		    */
		      
		      /* base para agregar otro campo o categoria
		    //for (int j = 0; j < 5; j++) {
		      Group group2 = new Group("Test ");
		      for (int i = 0; i < 5; i++) {
		        group2.children.add(json);
		      }
		      groups.append(1, group2);
		    //} 
		       * */
		  
	  }
		    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actividad2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
