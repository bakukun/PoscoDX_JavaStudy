package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class TCPClient {

	public static void main(String[] args) {
		Socket socket = null;
		
		try {
			
			// 1. 소켓 생성
			socket = new Socket();
			
			// 1-1. 소켓 버퍼 사이즈
			int rcvBufferSize = socket.getReceiveBufferSize();
			int sndBufferSize = socket.getSendBufferSize();
			System.out.println(rcvBufferSize + " " + sndBufferSize);
			
			//버퍼 사이즈를 조정할 수는 있지만, 먼저 조절하는 편은 아니다.
			
			// 1.2 소켓 버퍼 사이즈 변경
			socket.setReceiveBufferSize(1024 * 10);
			socket.setSendBufferSize(1024 * 10);

			
			System.out.println(socket.getReceiveBufferSize() + " " + socket.getSendBufferSize());
			
			// 1-3. SO_NODELAY(Nagle Algorithm OFF)
			socket.setTcpNoDelay(true);
			
			// 1-4. SO_TIMEOUT
			socket.setSoTimeout(3000);
			
			
			// 2. 서버 연결
			socket.connect(new InetSocketAddress("127.0.0.1", 60000));
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"), true); //autoFlush == true
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8")); //Flush가 없어도 돼
			
			//3. IO Stream 받아오기
			InputStream iStream = socket.getInputStream();
			OutputStream oStream = socket.getOutputStream();
			
			//4. 쓰기
			String data = "Hello world";
			oStream.write(data.getBytes("utf-8"));
			
			//5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = iStream.read(buffer);

			if (readByteCount == -1) { // 꽉 차있다 == 소켓을 닫은 것
				// closed by clinet
				System.out.println("[server] closed by client");
			}
			
			data = new String(buffer, 0, readByteCount, "utf-8");
			System.out.println("[client] received:" + data);
		} catch (SocketTimeoutException e) {
			System.out.println("[server] Socket TimeOut Exception : " + e);	
		} catch (SocketException e) {
			System.out.println("[server] Socket Exception : " + e);	
		} catch (IOException e) {
			System.out.println("[server] IO error : " + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {
					socket.close();
					System.out.println("[client] 소켓닫음");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
