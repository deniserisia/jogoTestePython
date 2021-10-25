package Painel;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Entidades.Funcionario;


public class Programa {
	
	public static void main(String[] args) {
		
		try {
		
			int id, opcao;
			
			Locale.setDefault(Locale.US); //Separador de decimais
			Scanner entrada = new Scanner(System.in);
			
			List<Funcionario> list = new ArrayList<>(); //Lista de dados
			
			
			do {
				
				System.out.println("\nBem-Vindo ao nosso sistema!\n");
				
				System.out.println("Digite 1 - Para cadastrar funcion�rio;\n"
						+ "Digite 2 - Para editar o sal�rio de um funcion�rio;\n"
						+ "Digite 3 - Para excluir um funcion�rio;\n"
						+ "Digite 4 - Para listar os funcion�rios;\n"
						+ "Digite 5 - Para sair do programa\n");
				opcao = entrada.nextInt();
				
				switch(opcao) {
				
					case 1:
						
						System.out.println("ID: ");
						id = entrada.nextInt();
						
						while(noId(list, id)) {
							System.out.println("Esse ID j� existe, digite novamente:");
							id = entrada.nextInt();
						}
						
						System.out.println("Nome do funcion�rio(a): ");
						entrada.nextLine();
						String nomeDoFuncionario = entrada.nextLine();
						
						System.out.println("Sal�rio: ");
						Double salarioDoFuncionario = entrada.nextDouble();
						
						Funcionario funcionario = new Funcionario(id, nomeDoFuncionario, salarioDoFuncionario);	
						
						if(list.add(funcionario)) {
							System.out.println("Funcion�rio cadastrado com sucesso!\n");
						}else {
							System.out.println("Erro ao realizar cadastro\n");
						}
					
						break;
						
					case 2:
						
						if(list.size() == 0) {
							System.out.println("A lista est� vazia, tente primeiro cadastrar um funcion�rio!\n");
						}else {
							System.out.println("Por favor, entre com o ID do funcion�rio que ter� o seu sal�rio aumentado: ");
							int idSalario = entrada.nextInt();
							
							Integer posicaoDoID = posicaoDoIdNaLista(list, idSalario);
							
							if (posicaoDoID == null) {
								System.out.println("Esse ID n�o existe ou n�o foi cadastrado!\n");
							}else {
								System.out.print("Entre com a porcentagem de aumento no sal�rio: ");
								double percentualDeAumento = entrada.nextDouble();
								list.get(posicaoDoID).aumentarSalario(percentualDeAumento);
							}
							
							System.out.println();
							System.out.println("A sua lista atualizada de funcionarios(as) � essa:");
							System.out.println("A sequ�ncia � ID | Nome do funcion�rio | Sal�rio");
							
							for (Funcionario fun : list) {
								System.out.println(fun);
							}
						}
						
						break;
						
					case 3: 
						
						if(list.size() == 0) {
							System.out.println("Nenhum funcion�rio cadastrado!\n");
						}else{
							
							System.out.println("Por favor, entre com o ID do funcion�rio que voc� deseja excluir: ");
							id = entrada.nextInt();
							
							Integer posicaoDoID = posicaoDoIdNaLista(list, id);
							
							if (posicaoDoID == null) {
								System.out.println("Esse ID n�o existe ou n�o foi cadastrado!\n");
							}else {
							    for(int i = 0; i < list.size(); i++){
							        Funcionario p = list.get(i);
	
							        if(p.getId() == id){
							            if(list.remove(p)) {
							            	System.out.println("Funcion�rio exclu�do com sucesso!\n");
							            }
							            
							            break;
							        }
							    }
								
							}	
						}
					
						break;
					
					case 4:
						
						if(list.size() == 0) {
							System.out.println("Nenhum funcion�rio cadastrado!\n");
						}else{
							Funcionario funcionarios = new Funcionario();
							for(int i = 0; i < list.size(); i++) {
								System.out.println("ID: " + list.get(i).getId() + " | Nome: " + list.get(i).getNomeDoFuncionario() + " | Sal�rio: " + list.get(i).getSalarioDoFuncionario());
							}	
						}
						
						break;
						
					case 5: 
						System.out.println(0);
						break;
						
					default:
						System.out.println("Op��o inv�lida! \n");
				
				}
				
			}while(opcao != 5);
			
			for (Funcionario fun : list) {
				System.out.println(fun);
			}
			
			entrada.close();
		
		// Tratamento de Exce��es GERAL 
		} catch (InputMismatchException e) {
				System.err.println("N�o � permitido letras, informe apenas numeros inteiros" + e.toString());
				
		} catch (NumberFormatException e) {
			System.out.println("N�o � permitido inserir letras, informe apenas n�meros interios" + e.getMessage());
		}
		
	}
	
	// Fun��o para procurar um elemento na lista 
	public static Integer posicaoDoIdNaLista(List<Funcionario> list, int id) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				return i;
			}
		}	
		return null;
	}
	// Fun��o auxiliar para ver se esse ID j� existe na lista
	public static boolean noId(List<Funcionario> list, int id) {
		Funcionario funcionario = list.stream().filter( x -> x.getId() == id).findFirst().orElse(null);
		return funcionario != null;
	}

}