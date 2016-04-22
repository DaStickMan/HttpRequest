import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class SocketServer  implements Runnable{

	/**
	 * @param args
	 * @throws IOException 
	 */
	//Construtor seta o socket
	SocketServer(Socket socket){
		this.socket = socket;
	}
	
	public Socket socket;
	static ArrayList chaves = new ArrayList();
	static ArrayList<String> valor = new ArrayList<String>();
	static boolean parametroEncontrado=false;
	static boolean parametroInvalido=false;
	static ServerSocket server;
	public static void main(String[] args) throws IOException, InterruptedException {
	server = new ServerSocket(12345);
        System.out.println("Porta 12345 aberta ...."); 
       
	   while (true) {
                    Socket socketNovo = server.accept();
                    //Ao aceitar a requisição, abre uma nova thread
                    SocketServer sckt = new SocketServer(socketNovo);
                    Thread t = new Thread(sckt);
                    t.start();
                    t.join();                
            }


	}
	private static void funcaoDelete(String chave) {
		
		for(int j=0;j<chaves.size();j++){
			if(chave.equals(chaves.get(j))){
				parametroEncontrado=true;
				chaves.remove(j);
			}
		}
		
		
	}
	private static void funcaoPut(String chave,String body) {
		String parametros[];
		String valores[] = null;
		int auxj=0;
		if(chave.isEmpty()){
			parametros = body.split("&");
			
			for(int i=0;i<parametros.length;i++){				
				valores = parametros[i].split("=");	
				
				if(valores[0].equals("chave")){
					for(int j=0;j<chaves.size();j++){	
						if(valores[1].equals(chaves.get(j))){
							parametroEncontrado=true;
							auxj=j;
						}
					}
				}
				if(valores[0].equals("valor")){
					if(parametroEncontrado){
						valor.add(auxj,valores[1]);
					}
				}		
			}
		parametroInvalido=false;
	}else{
                    
                    for(int j=0;j<chaves.size();j++){	
			if(chave.equals(chaves.get(j))){
				parametroEncontrado=true;
				auxj=j;
			}
                    }
                    valores = body.split("=");
                    if(parametroEncontrado && !valores[1].isEmpty()){
			valor.add(auxj,valores[1]);
                        parametroInvalido=false;
                    }else{
                        parametroInvalido=true;
                    }
	}
		
		
		
	}
	public static String funcaoGet(String chave){
		String bodyEncontrado="VALOR NÃO ENCONTRADO";
		int contador=0;
		for(int j=0;j<chaves.size();j++){	
			if(chaves.get(j).equals(chave)){
                                parametroEncontrado=true;
				bodyEncontrado=valor.get(j);
				
			}else{
				contador++;
			}
		}		
		if(contador==chaves.size()){
			parametroEncontrado=false;
		}
		return ""+bodyEncontrado+"";
		
	}
	public static void funcaoPost(String chave,String body){
	String parametros[];
	String valores[] = null;
	if(chave.isEmpty()){
			parametros = body.split("&");
			
			for(int i=0;i<parametros.length;i++){				
				valores = parametros[i].split("=");	
				
				if(valores[0].equals("chave")){
					for(int j=0;j<chaves.size();j++){	
						if(valores[1].equals(chaves.get(j))){
							parametroEncontrado=true;				
						}
					}
					if(!parametroEncontrado){
						chaves.add(valores[1]);
					}
				}
				if(valores[0].equals("valor")){
					if(!parametroEncontrado){
						valor.add(valores[1]);
					}
				}		
			}
		parametroInvalido=false;
	}else{
            
                for(int j=0;j<chaves.size();j++){	
			if(chave.equals(chaves.get(j))){                            
                                parametroEncontrado=true;                            
			}
		}
                valores = body.split("=");
                if(!parametroEncontrado && !valores[1].isEmpty()){
                        valor.add(valores[1]);
                        chaves.add(chave);
                        parametroInvalido=false;
		}  else{
                    parametroInvalido=true;
                }       
                
	}
		

	}
	public static String checaRequisicao(String req){
		if(req.contains("GET ")){
        	return "GET";
        }else if(req.contains("POST ")){
        	return "POST";
        }else if (req.contains("PUT ")){
        	return "PUT";
        }else if (req.contains("DELETE ")){
        	return "DELETE";
        }else{
        	return "INVALID";
        }
	}
	@Override
	//Metodo que roda a thread
	public void run() {
		try {
			trataRequisicao();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
        
        
	private void trataRequisicao() throws IOException {
                //Trata a requisicao
		boolean tamanhoExcedido=false;
		String req="";
                InputStreamReader isr =  new InputStreamReader(socket.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                String line = reader.readLine();            
                String html="";
                String httpResponse = "";
                req = checaRequisicao(line);
                
                String[] chave = line.split(" /");
                chave = chave[1].split(" ");
                
                String body ="";
                System.out.println(req);
                int length=0;
                while (!line.isEmpty()) {                    
                    line = reader.readLine();
                    if(line.equals("")){
                    	break;
                    }
                    //Pega o tamanho do body
					if (line.startsWith("Content-Length: ")) { 
						int index = line.indexOf(':') + 1;
						String len = line.substring(index).trim();
						length = Integer.parseInt(len);
					}
					System.out.println(line);
                }
               StringBuilder bodyapende = new StringBuilder();
	           if (length<1000){    
                    if (length > 0) {
                            int read;
                            while ((read = reader.read()) != -1) {
                                    bodyapende.append((char) read);
                                if (bodyapende.length() == length)
                                    break;
                            }
                        }
                            tamanhoExcedido=false;
                    }else{
                            tamanhoExcedido=true;
                    }
	               
               body = bodyapende.toString();
               

                if(req=="GET"){        
                	html = funcaoGet(chave[0]);
                	System.out.println(html);
                	if(parametroEncontrado)
                	httpResponse = "HTTP/1.1 200 OK\r\n\r\n"+html;
                	else
                        httpResponse = "HTTP/1.1 404 Parametro nao encontrado!\r\n\r\n" + html;
                	

                }else if(req=="POST"){
                	funcaoPost(chave[0],body);
                	if(parametroEncontrado)
                		httpResponse = "HTTP/1.1 400 Parametro já existe!\r\n\r\n";
                	else if (parametroInvalido||tamanhoExcedido)
                		httpResponse = "HTTP/1.1 400 Requisicao inválida\r\n\r\n" + html;
                	else
                		httpResponse = "HTTP/1.1 201 OK\r\n\r\n" + html;

                }else if(req=="PUT"){
                	funcaoPut(chave[0],body);
                	if(parametroEncontrado)
                    	httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + html;
                	else if (parametroInvalido||tamanhoExcedido)
                		httpResponse = "HTTP/1.1 404 Requisicao inválida\r\n\r\n" + html;
                	else
                		httpResponse = "HTTP/1.1 404 Parametro não encontrado\r\n\r\n";

                }else if(req=="DELETE"){
                	funcaoDelete(chave[0]);
                	if(parametroEncontrado)
                    	httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + html;
                	else
                	httpResponse = "HTTP/1.1 404 Parametro não encontrado\r\n\r\n";

                }else{
                    httpResponse = "HTTP/1.1 400 BAD REQUEST\r\n\r\n" + html;                	
                }
                	
                                
                socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
                socket.getOutputStream().flush();
                socket.getOutputStream().close();
                req="";
                parametroEncontrado=false;
            		
	}

}
