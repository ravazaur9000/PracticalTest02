package ro.pub.cs.systems.pdsd.practicaltest02;

import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;









import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



//import ro.pub.cs.systems.pdsd.practicaltest02.model.WeatherForecastInformation;

public class CommunicationThread extends Thread {
	 final public static String TAG = "[PracticalTest02]";
	 final public static String WEB_SERVICE_ADDRESS = "http://www.timeapi.org/utc/now";
	    final public static boolean DEBUG = true;
    private ServerThread serverThread;
    private Socket socket;

    public CommunicationThread(ServerThread serverThread, Socket socket) {
        this.serverThread = serverThread;
        this.socket = socket;
    }
    
    @Override
    public void run() {
      if (socket != null) {
        try {
          BufferedReader bufferedReader = Utilities.getReader(socket);
          PrintWriter    printWriter    = Utilities.getWriter(socket);
          if (bufferedReader != null && printWriter != null) {
            Log.i(TAG, "[COMMUNICATION THREAD] Waiting for parameters from client (city / information type)!");
            String city            = bufferedReader.readLine();
            String informationType = bufferedReader.readLine();
         
            try {
            	  HttpClient httpClient = new DefaultHttpClient();
            	  HttpGet httpGet = new HttpGet(WEB_SERVICE_ADDRESS);
            	  HttpResponse httpGetResponse = httpClient.execute(httpGet);
            	  HttpEntity httpGetEntity = httpGetResponse.getEntity();
            	  if (httpGetEntity != null) {  
            	    // do something with the response
            		   
            	    Log.i(TAG, EntityUtils.toString(httpGetEntity));
            	  }            
            	} catch (Exception exception) {
            	  Log.e(TAG, exception.getMessage());
            	  if (DEBUG) {
            	    exception.printStackTrace();
            	  }
            	}
              
    }
 
 