package me.changming;

import me.changming.httpThread.HttpRootAcceptor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main( String[] args ) {
		ServerSocket serverSocket = null;
		try {
			// 监听8080端口
			serverSocket = new ServerSocket( 8080 );
			Socket socket = serverSocket.accept();

			while ( socket != null ) {
				new HttpRootAcceptor( socket ).start();
				socket = serverSocket.accept();
			}
		} catch ( IOException e ) {
			e.printStackTrace();
		} finally {
			if ( serverSocket != null ) {
				try {
					serverSocket.close();
				} catch ( IOException e ) {
					e.printStackTrace();
				}
			}
		}
	}
}
