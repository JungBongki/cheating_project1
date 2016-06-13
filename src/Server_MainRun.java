import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server_MainRun {

	public static void main(String[] args) {
		
		String _serverData = null;
		InetAddress _ia = null;
		try {
			_ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		System.out.println(_ia.getHostName());
		System.out.println(_ia.getHostAddress());
		System.out.println("접속에 성공하였습니다. 채팅을 시작합니다.");

		

		Scanner sc = new Scanner(System.in);
		_serverData = sc.nextLine();
		
		int _userPort = 60606;

		ServerSocket _userSS = null;
		Socket _socket = null;
		String _clientData = null;
		InputStream _is = null;
		OutputStream _os = null;
		byte[] _bytes = new byte[1000];
		
		try {
			_userSS = new ServerSocket();
			_userSS.bind(new InetSocketAddress(_ia.getHostAddress(), _userPort));
			_socket = _userSS.accept(); //대기상태

			do {
				_os = _socket.getOutputStream();
				_bytes = _serverData.getBytes("UTF-8");
				_os.write(_bytes);
				_os.flush();
				////////////////////////////////
				_is = _socket.getInputStream();
				_clientData = new String(_bytes, 0, _is.read(_bytes));
				System.out.println("클라이언트로부터의 데이터 : " + _clientData);
				_serverData = sc.nextLine();
			} while (!_serverData.equals("/q"));
			/////////////////////////////////////////////////
			
			_os.close();
			_is.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				_socket.close();
				_userSS.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
