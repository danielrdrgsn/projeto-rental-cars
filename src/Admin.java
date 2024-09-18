public class Admin extends Usuario {

    public Admin() {
        super();
    }

    @Override
    public void showMenu() {
        System.out.println("\nMENU - ADMINISTRADOR");
        System.out.println("\n1. Agências");    // Mostrar menu para cadastrar, editar, exluir, listar, buscar e sair
        System.out.println("2. Veículos");      // Mostrar menu para cadastrar, editar, exluir, listar, buscar e sair
        System.out.println("3. Aluguéis");      // Mostrar menu editar, excluir, listar, buscar e sair
        System.out.println("4. Clientes");      // Mostrar menu para cadastrar, editar, exluir, listar, buscar e sair
        System.out.println("5. Sair");          // Sai do sistema
    }
}