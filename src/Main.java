import entities.Locadora;
import entities.agencia.Agencia;
import entities.agencia.Endereco;
import entities.usuario.Administrador;
import entities.usuario.PessoaFisica;
import entities.usuario.PessoaJuridica;
import entities.usuario.Usuario;
import entities.veiculo.Caminhao;
import entities.veiculo.Carro;
import entities.veiculo.Moto;
import entities.veiculo.Veiculo;
import repositories.LocadoraBD;
import utils.ConsoleColors;
import utils.MenusUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Locadora locadora = new Locadora("* RENT-A-CAR *");
        LocadoraBD.carregarDadosLocadora();

        System.out.println(locadora.getNome());

        MenusUtils.mostrarMenuPrincipal();


//        LocadoraBD.salvarDadosLocadora(locadora);
//
//        Endereco endereco1 = new Endereco("Rua das Flores", 123, "Casa 1", "São Paulo", "SP", "01000-000");
//        Endereco endereco2 = new Endereco("Avenida Senador Feijó", 326, "N/A", "Santos", "SP", "02000-000");
//
//        Agencia ag1 = new Agencia(1, "Localiza Flores", endereco1);
//        Agencia ag2 = new Agencia(2, "Localiza Santos", endereco2);
//
//        Veiculo v1 = new Carro("123ABC", "Ford Ka", 2001, "Prata", true, 1);
//        Veiculo v2 = new Moto("456DEF", "Honda Bis", 2021, "Marrom", true, 1);
//        Veiculo v3 = new Caminhao("789GHI", "Scania", 2018, "Azul", true, 1);
//        Veiculo v4 = new Carro("012JKL", "Nissan Livina", 2012, "Vermelho", true, 2);
//
//        Locadora.getVeiculos().add(v1);
//        Locadora.getVeiculos().add(v2);
//        Locadora.getVeiculos().add(v3);
//        Locadora.getVeiculos().add(v4);
//
//        Locadora.getAgencias().add(ag1);
//        Locadora.getAgencias().add(ag2);
//
//        Usuario u1 = new Administrador("Joao", "joao@gmail.com", 123456);
//        Usuario u2 = new PessoaFisica("Maria", "maria@gmail.com", 1, "123456789");
//        Usuario u3 = new PessoaJuridica("Pedro", "pedro@gmail.com", 2, "987654321");
//
//        Locadora.getUsuarios().add(u1);
//        Locadora.getUsuarios().add(u2);
//        Locadora.getUsuarios().add(u3);
//
//        Collections.sort(Locadora.getUsuarios());
//        for (Usuario u : Locadora.getUsuarios()) {
//            System.out.println(u);
//        }
//
//        LocadoraBD.salvarDadosLocadora(locadora);
//
//        LocadoraBD.carregarUsuarios();
//        LocadoraBD.carregarAgencias();
//        LocadoraBD.carregarVeiculos();
//        Locadora.getUsuarios().forEach(System.out::println);
//        Locadora.getAgencias().forEach(System.out::println);
//        Locadora.getVeiculos().forEach(System.out::println);

//        System.out.println("Meu carro:");
//        System.out.println(Locadora.getVeiculos().get(0).mostrarVeiculo());
//
//        System.out.println("Usuário:");
//        System.out.println(Locadora.getUsuarios().get(1).mostrarUsuario());
//
//        System.out.println("Dados da agência:");
//        System.out.println(Locadora.getAgencias().get(0).mostrarAgencia());
    }

}