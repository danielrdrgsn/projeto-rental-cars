## Diagrama de classes - Entidades
```mermaid
classDiagram
    Usuario <|-- Administrador
    Usuario <|-- Cliente
    Cliente <|-- PessoaFisica
    Cliente <|-- PessoaJuridica
    Usuario : +Integer id
    Usuario : +String nome
    Usuario: +String email
    Usuario: +TipoUsuario tipo
    
    class Administrador{
      +Integer numeroRegistro
    }
    class Cliente{
      +Integer idCliente
    }
    class PessoaFisica{
      +String cpf
    }
    class PessoaJuridica{
      +String cnpj
    }
    class TipoUsuario{
        <<enum>>
        ADMIN
        PF
        PJ
    }


    Veiculo <|-- Carro
    Veiculo <|-- Moto
    Veiculo <|-- Caminhao
    Veiculo : +String placa
    Veiculo: +String modelo
    Veiculo: +int anoFabricacao
    Veiculo: +String cor
    Veiculo: +TipovEICULO tipo
    Veiculo: +Boolean disponivel
    Veiculo: +Integer numeroAgencia
    
    class Carro{
      +Integer numeroRegistro
    }
    class Moto{
      +Integer idCliente
    }
    class Caminhao{
      +String cpf
    }
    class TipoVeiculo{
        <<enum>>
        CARRO
        MOTO
        CAMINHAO
    }
```
## Fluxograma - Menus
```mermaid
flowchart TD
    A[Início] --> B(Menu Principal)
    B --> C{Tipo de Usuário}
    C -- Adm --> D(Menu Adm)
    C -- Cliente --> E(Menu Cliente)
    D -- CRUD --> F(Clientes)
    D -- CRUD --> G(Agências)
    D -- CRUD --> H(Veículos)
```
