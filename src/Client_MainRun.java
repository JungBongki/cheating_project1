import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client_MainRun {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		InetAddress _ia = null;
		try {
			_ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		System.out.println(_ia.getHostName());
		System.out.println(_ia.getHostAddress());
		System.out.println("이게법니까");
		int _userPort = 60600;
		String _clientData = null;
		Socket socket = null;
		byte[] _bytes = new byte[1000];
		
		String _serverIP = "192.168.14.20";
		int _serverPort = 60606; 
		String _serverData = null;
		InputStream _is = null;
		OutputStream _os = null;
		
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress(_serverIP, _serverPort));
			_is = socket.getInputStream();
			_serverData = new String(_bytes, 0, _is.read(_bytes));
			System.out.println("�������� ���� �� : " + _serverData);
			do{
				_clientData = sc.nextLine();
				_os = socket.getOutputStream();
				_bytes = _clientData.getBytes("UTF-8");
				_os.write(_bytes);
				_os.flush();
				///////////////
				_is = socket.getInputStream();
				_serverData = new String(_bytes, 0, _is.read(_bytes));
				System.out.println("�������� ���� �� : " + _serverData);
			}while(!_clientData.equals("/q"));
			
			_is.close();
			_os.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
