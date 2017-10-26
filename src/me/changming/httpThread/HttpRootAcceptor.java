package me.changming.httpThread;

import java.io.*;
import java.net.Socket;

public class HttpRootAcceptor extends Thread {
	private Socket socket;

	public HttpRootAcceptor( Socket socket ) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		BufferedWriter bufferedWriter = null;
		try {
			bufferedReader = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

			String str = null;
			while ( ( str = bufferedReader.readLine() ) != null && !str.equals( "" ) )
				System.out.println( str );

			bufferedWriter = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
			bufferedWriter.write( "HTTP/1.1 200 OK\n" );
			bufferedWriter.write( "Content-Type: application/json; charset=UTF-8\n\n" );
			bufferedWriter.write( "{\"a\": \"Hello\", \"b\": \"World\"}" );
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			if ( bufferedReader != null ) {
				try {
					bufferedReader.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
			if ( bufferedWriter != null ) {
				try {
					bufferedWriter.flush();
					bufferedWriter.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
			if ( socket != null ) {
				try {
					socket.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
		}
	}
}
