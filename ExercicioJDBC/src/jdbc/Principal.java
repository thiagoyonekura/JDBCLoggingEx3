package jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;




public class Principal {

public static void main(String[] args) throws IOException {
		
	  BancoDados bd = new BancoDados();
      bd.conectar("jdbc:mysql://localhost:3306/jdbc", "root", "");

      Scanner leitor = new Scanner(System.in);
      Log meuLogger = new Log("Log.txt");
      meuLogger.logger.setLevel(Level.FINEST);
      

      System.out.println("Selecione uma opção:");
      meuLogger.logger.info("Você deve selecionar apenas uma opção!");
      System.out.println("1 - Inserir pessoa");
      System.out.println("2 - Consultar pessoas");
      System.out.println("3 - Alterar pessoa");
      System.out.println("4 - Deletar pessoa");
      System.out.println("5 - Desconectar do BD");
      int opcao = leitor.nextInt();
      
      switch (opcao) {
          case 1:
        	  System.out.println("Quantas pessoas você quer inserir?");
              int quantidade = leitor.nextInt();
              for (int i = 0; i < quantidade; i++) {
                  System.out.println("Digite o nome da pessoa " + (i+1) + ":");
                  String nomePessoa = leitor.next();
                  System.out.println("Digite o email da pessoa " + (i+1) + ":");
                  String emailPessoa = leitor.next();
                  String queryInsercaoPessoa = "INSERT INTO pessoa (nome, email) VALUES ('" + nomePessoa + "','" + emailPessoa + "')";
                  bd.inserirAlterarExcluir(queryInsercaoPessoa);
                  
              }
              break;
          case 2:
              bd.consultar("SELECT * FROM pessoa");              
              break;
          case 3:
              System.out.println("Digite o ID da pessoa que deseja alterar:");
              int idAlterar = leitor.nextInt();
              System.out.println("Digite o novo nome da pessoa:");
              String novoNome = leitor.next();
              System.out.println("Digite o novo email da pessoa:");
              String novoEmail = leitor.next();
              String queryAlteracao = "UPDATE pessoa SET nome = '" + novoNome + "', email = '" + novoEmail + "' WHERE id = " + idAlterar;
              bd.inserirAlterarExcluir(queryAlteracao);
              meuLogger.logger.info("Usuário com id " + idAlterar + " foi alterado na base de dados!");
              break;
          case 4:
        	  System.out.println("Digite o ID da pessoa que deseja deletar:");
              int idDeletar = leitor.nextInt();
              String queryDelecao = "DELETE FROM pessoa WHERE id = " + idDeletar;
              bd.inserirAlterarExcluir(queryDelecao);
              meuLogger.logger.info("Usuário com id " + idDeletar + " foi excluído da base de dados!");
              break;
          case 5:
        	  bd.desconectar();
        	  break;
          default:
              System.out.println("Opção inválida!");
              meuLogger.logger.severe("\n Opção digitada no console é inválida!");
              break;
      }

     
  }

}
