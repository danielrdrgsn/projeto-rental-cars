package entities.usuario;

public enum TipoUsuario {

    ADMIN("Administrador/Operador"),
    PF("Pessoa Física"),
    PJ("Pessoa Jurídica");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
