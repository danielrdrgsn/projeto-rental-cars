public class AgenciaAddNew extends Agencia {

    public AgenciaAddNew() {
        super();
    }

    @Override
    public void showMenu() {
        System.out.println("\nMENU - AGÊNCIA");
        System.out.println("\n1. Cadastrar agência");
        System.out.println("2. Alterar agência");
        System.out.println("3. Deletar agência");
        System.out.println("4. Listar agências");
        System.out.println("5. Sair");
    }
}
