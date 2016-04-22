import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpRequest {


	public static void main(String[] args) throws UnknownHostException, IOException {
		String tipoRequisicao=args[0];
		String url=args[1];
		String parametros []  = url.split("\\?");
		String endereco[] = url.split("/");
		for(int i=0;i<parametros.length;i++){
			System.out.println(parametros[i]);
		}
		
		
		Socket s = new Socket("localhost", Integer.parseInt(args[2]));
		
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		if(parametros.length>1){
			pw.println(tipoRequisicao+" /?"+parametros[1]+" HTTP/1.1");

		}else{
			pw.println(tipoRequisicao+" / HTTP/1.1");
		}
		pw.println("Host: "+endereco[0]);
		pw.println("");
		pw.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String t;
		while((t = br.readLine()) != null) System.out.println(t);
		br.close();
		
	}
	
	public void sendGet(){
		
	}

}
