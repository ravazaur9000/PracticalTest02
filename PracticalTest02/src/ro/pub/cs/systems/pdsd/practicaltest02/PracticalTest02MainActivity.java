package ro.pub.cs.systems.pdsd.practicaltest02;

import android.app.Activity;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PracticalTest02MainActivity extends Activity {
	private EditText serverPortEditText = null;
	private Button connectButton = null;

	// Client widgets
	private EditText clientAddressEditText = null;
	private EditText clientPortEditText = null;
	private EditText CheieEditText= null;
	private EditText ValoareEditText= null;
	private Button getButton = null;
	private Button putButton = null;


	private ServerThread serverThread = null;
	private ClientThread clientThread = null;
	private ConnectButtonClickListener connectButtonClickListener = new ConnectButtonClickListener();
	private GetButtonClickListener getButtonClickListener = new GetButtonClickListener();
	private PutButtonClickListener putButtonClickListener = new PutButtonClickListener();
	
	private class ConnectButtonClickListener implements Button.OnClickListener {
	    final public static String TAG = "[PracticalTest02]";

	    final public static boolean DEBUG = true;
	    @Override
	    public void onClick(View view) {
	        String serverPort = serverPortEditText.getText().toString();
	        if (serverPort == null || serverPort.isEmpty()) {
	            Toast.makeText(
	                    getApplicationContext(),
	                    "Server port should be filled!",
	                    Toast.LENGTH_SHORT
	            ).show();
	            return;
	        }

	        serverThread = new ServerThread (Integer.parseInt(serverPort));
	        if (serverThread.getServerSocket() != null) {
	            serverThread.start();
	        } else {
	            Log.e(TAG, "[MAIN ACTIVITY] Could not creat server thread!");
	        }

	    }
	}
	
	private class GetButtonClickListener implements Button.OnClickListener {
	    final public static String TAG = "[PracticalTest02]";

	    final public static boolean DEBUG = true;
	    @Override
	    public void onClick(View view) {
	        String serverPort = serverPortEditText.getText().toString();
	        if (serverPort == null || serverPort.isEmpty()) {
	            Toast.makeText(
	                    getApplicationContext(),
	                    "Server port should be filled!",
	                    Toast.LENGTH_SHORT
	            ).show();
	            return;
	        }

	        serverThread = new ServerThread (Integer.parseInt(serverPort));
	        if (serverThread.getServerSocket() != null) {
	            serverThread.start();
	        } else {
	            Log.e(TAG, "[MAIN ACTIVITY] Could not creat server thread!");
	        }

	    }
	}
	
	private class PutButtonClickListener implements Button.OnClickListener {
	    final public static String TAG = "[PracticalTest02]";

	    final public static boolean DEBUG = true;
	    @Override
	    public void onClick(View view) {
	        String serverPort = serverPortEditText.getText().toString();
	        if (serverPort == null || serverPort.isEmpty()) {
	            Toast.makeText(
	                    getApplicationContext(),
	                    "Server port should be filled!",
	                    Toast.LENGTH_SHORT
	            ).show();
	            return;
	        }

	        serverThread = new ServerThread (Integer.parseInt(serverPort));
	        if (serverThread.getServerSocket() != null) {
	            serverThread.start();
	        } else {
	            Log.e(TAG, "[MAIN ACTIVITY] Could not creat server thread!");
	        }

	    }
	}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test02_main);
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical_test02_main, menu);
        
        serverPortEditText = (EditText)findViewById(R.id.editText1);
        connectButton = (Button)findViewById(R.id.button1);
        clientAddressEditText=(EditText)findViewById(R.id.editText2);
        clientPortEditText=(EditText)findViewById(R.id.editText3);
        CheieEditText=(EditText)findViewById(R.id.editText4);
        ValoareEditText=(EditText)findViewById(R.id.editText5);
        getButton = (Button)findViewById(R.id.button2);
        putButton = (Button)findViewById(R.id.button3);
        
        
        connectButton.setOnClickListener(connectButtonClickListener);
        getButton.setOnClickListener(getButtonClickListener);
        putButton.setOnClickListener(putButtonClickListener);
        
        
        return true;
    }
    
    @Override
    protected void onDestroy() {
        if (serverThread != null) {
            serverThread.stopThread();
        }
        super.onDestroy();
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
