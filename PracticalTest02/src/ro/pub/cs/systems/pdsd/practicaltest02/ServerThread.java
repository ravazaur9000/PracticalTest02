package ro.pub.cs.systems.pdsd.practicaltest02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.client.ClientProtocolException;

import android.provider.SyncStateContract.Constants;
import android.util.Log;

public class ServerThread extends Thread
{

    final public static String TAG = "[PracticalTest02]";

    final public static boolean DEBUG = true;
    private int port = 0;
    private ServerSocket serverSocket = null;
    private String data = null;
	 private boolean isRunning;
	 

	    public ServerThread(int port) {
	        this.port = port;
	        try {
	            this.serverSocket = new ServerSocket(port);
	        } catch (IOException ioException) {
	            Log.e(TAG, "An exception has occurred: " + ioException.getMessage());
	            if (DEBUG) {
	                ioException.printStackTrace();
	            }
	        }
	        this.data = "";
	    }
	 
	    public void startServer() {
	      isRunning = true;
	      start();
	    }
	    
	    public synchronized String getData() {
	        return this.data;
	    }
	    
	    public void setPort(int port) {
	        this.port = port;
	    }

	    public int getPort() {
	        return port;
	    }

	    public void setServerSocker(ServerSocket serverSocket) {
	        this.serverSocket = serverSocket;
	    }
	    public ServerSocket getServerSocket() {
	        return serverSocket;
	    }
	    
	    
	    public void stopThread() {
	        if (serverSocket != null) {
	            interrupt();
	            try {
	                serverSocket.close();
	            } catch (IOException ioException) {
	                Log.e(TAG, "An exception has occurred: " + ioException.getMessage());
	                if (DEBUG) {
	                    ioException.printStackTrace();
	                }
	            }
	        }
	    }
	 
	    public void stopServer() {
	      isRunning = false;
	      new Thread(new Runnable() {
	        @Override
	        public void run() {
	          try {
	            if (serverSocket != null) {
	              serverSocket.close();
	            }
	            Log.v(TAG, "stopServer() method invoked "+serverSocket);
	          } catch(IOException ioException) {
	            Log.e(TAG, "An exception has occurred: "+ioException.getMessage());
	            if (DEBUG) {
	              ioException.printStackTrace();
	            }
	          }
	        }
	      }).start();
	    }
	 
	    @Override
	    public void run() {
	        try {
	            while (!Thread.currentThread().isInterrupted()) {
	                Log.i(TAG, "[SERVER] Waiting for a connection...");
	                Socket socket = serverSocket.accept();
	                Log.i(TAG, "[SERVER] A connection request was received from " + socket.getInetAddress() + ":" + socket.getLocalPort());
	               CommunicationThread communicationThread = new CommunicationThread(this, socket);
	                communicationThread.start();
	            }
	        } catch (ClientProtocolException clientProtocolException) {
	            Log.e(TAG, "An exception has occurred: " + clientProtocolException.getMessage());
	            if (DEBUG) {
	                clientProtocolException.printStackTrace();
	            }
	        } catch (IOException ioException) {
	            Log.e(TAG, "An exception has occurred: " + ioException.getMessage());
	            if (DEBUG) {
	                ioException.printStackTrace();
	            }
	        }
	    }
}
