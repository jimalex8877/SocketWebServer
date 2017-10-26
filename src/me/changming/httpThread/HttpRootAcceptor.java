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
				System.out.println( str );// 后续可以写方法处理requestHeader和requestBody

			bufferedWriter = new BufferedWriter( new OutputStreamWriter( socket.getOutputStream() ) );
			bufferedWriter.write( "HTTP/1.1 200 OK\n" );
			// 后续可以自定义是json格式还是text/html格式
			bufferedWriter.write( "Content-Type: application/json; charset=UTF-8\n\n" );
			bufferedWriter.write( "{\"a\": \"Hello\", \"b\": \"World\"}" );
			bufferedWriter.flush();
			bufferedWriter.close();
			bufferedReader.close();
			socket.close();
		} catch ( IOException e ) {
			e.printStackTrace();
		}
	}
}
