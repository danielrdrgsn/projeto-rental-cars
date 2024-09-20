import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Aluguel {

    private int ID;
    private Usuario usuario;
    private Carro carro;
    private Moto moto;
    private Caminhao caminhao;
    private Agencia agencia;
    private LocalDateTime dateTime;
    private int hora;
    private double total;
    private String status;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Aluguel() {
        dateTime = LocalDateTime.now();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public Caminhao getCaminhao() {
        return caminhao;
    }

    public void setCaminhao(Caminhao caminhao) {
        this.caminhao = caminhao;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public String getDateTime() {
        return formatter.format(dateTime);
    }

    public void setDateTime(String dateTimeString) {
        this.dateTime = LocalDateTime.parse(dateTimeString, formatter);
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void alugarVeiculoParaPessoaFisica(Cliente cliente, List<Veiculo> veiculos, List<Agencia> agencias, int horas){

        boolean isAlugado = false;

        for(int i=0; i<veiculos.size(); i++){
            Veiculo veiculo = veiculos.get(i);

            if(veiculo.isDisponivel()){
                this.usuario = usuario;

                if(veiculo instanceof Carro){
                    this.carro = (Carro) veiculo;
                }else{
                    this.carro = null;
                }

                if(veiculo instanceof Moto){
                    this.moto = (Moto) veiculo;
                }else{
                    this.moto = null;
                }

                if(veiculo instanceof Caminhao){
                    this.caminhao = (Caminhao) veiculo;
                }else{
                    this.caminhao = null;
                }

                // MELHORAR ISSO MAIS TARDE
                if(!agencias.isEmpty()){
                    this.agencia = agencias.get(0);
                }

                this.hora = horas;

                this.total = calcularTotal(horas, veiculo.getValor());

                this.status = "Veículo alugado!";
                veiculo.setDisponivel(false);

                this.dateTime = LocalDateTime.now();

                isAlugado = true;
                break;


            }

        }

        if(!isAlugado){
            System.out.println("Não há veículos disponíveis!");

    }

}

    private double calcularTotal(int horas, double valor) {
        double result = valor*horas;
        return result;
    }


    public void alugarVeiculoParaPessoaJuridica(Cliente cliente, List<Veiculo> veiculos, List<Agencia> agencias, int horas){
        boolean isAlugado = false;

        for(int i=0; i < veiculos.size(); i++){
            Veiculo veiculo = veiculos.get(i);

            double descontoPessoaJuridica = 0.9;

            if(veiculo.isDisponivel()){
                this.usuario = usuario;

                if(veiculo instanceof Carro){
                    this.carro = (Carro) veiculo;
                }else{
                    this.carro = null;
                }

                if(veiculo instanceof Moto){
                    this.moto = (Moto) veiculo;
                }else{
                    this.moto = null;
                }

                if(veiculo instanceof Caminhao){
                    this.caminhao = (Caminhao) veiculo;
                }else{
                    this.caminhao = null;
                }

                if(!agencias.isEmpty()){
                    this.agencia = agencias.get(0);
                }

                this.hora = horas;
                this.total = calcularTotal(horas, veiculo.getValor()) * descontoPessoaJuridica;
                this.status = "Alugado PJ";
                veiculo.setDisponivel(false);
                this.dateTime = LocalDateTime.now();
                isAlugado = true;
                break;

            }
        }

        if(!isAlugado){
            System.out.println("Não há veículos disponíveis!");
        }
    }
}
