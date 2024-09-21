public class Admin extends Usuario1 {

    public Admin() {
        super();
    }

    @Override
    public void showMenu() {
        System.out.println("\nMENU - ADMINISTRADOR");
        System.out.println("\n1. Agências"); // ELOISE
        System.out.println("2. Veículos"); // THIAGO
        System.out.println("3. Aluguéis"); // VINICIUS
        System.out.println("4. Clientes"); // SAMUEL
        System.out.println("5. Sair");
    }
}