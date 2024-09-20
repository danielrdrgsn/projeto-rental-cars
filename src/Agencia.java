public abstract class Agencia {

    //private int ID;
    private String nome;
    private String endereco;
    private String telefone;
    private List<Veiculo> veiculos = new ArrayList<>();

    public Agencia(String nome, String telefone, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public Veiculo buscarVeiculoPorId(String id) {
        for (Veiculo v : veiculos) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    public List<Veiculo> buscarVeiculoPorMarca(String marca) {
        List<Veiculo> encontrados = new ArrayList<>();
        for (Veiculo v : veiculos) {
            if (v.getMarca().toLowerCase().contains(marca.toLowerCase())) {
                encontrados.add(v);
            }
        }
        return encontrados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
}