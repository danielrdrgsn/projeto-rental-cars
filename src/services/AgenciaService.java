package services;

import entities.agencia.Agencia;
import repositories.AgenciaRepository;

import java.util.Scanner;

public class AgenciaService {

    private static final AgenciaRepository agenciaRepository = new AgenciaRepository();


    public static void adicionar(Scanner input) {
        //TODO
    }

    public static void editar(Scanner input) {
        //TODO
    }

    public static void buscar(Scanner input) {
        //TODO
    }

    public static void remover(Scanner input) {
        //TODO
    }

    public static void listar(Scanner input) {
        //TODO
    }

    public static Agencia buscarAgencia(Integer codigoAgencia) {
        return agenciaRepository.buscar(codigoAgencia);
    }
}
