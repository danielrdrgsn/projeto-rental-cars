public class Cliente extends Usuario {

    public Cliente() {
        super();
    }

    @Override
    public void showMenu() {
        System.out.println("\nMENU - CLIENTE");
        System.out.println("\n1. Listar veículos");
        System.out.println("2. Alugar veículo");
        System.out.println("3. Meus aluguéis");
        System.out.println("4. Devolução de veículo");
        System.out.println("5. Sair");
    }
}