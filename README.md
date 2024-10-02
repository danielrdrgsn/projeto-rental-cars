# Documentação do Projeto Rental Cars

## Introdução

Este é um projeto de locação de veículos desenvolvido como parte do programa Santander Coders.
O sistema simula uma locadora de veículos


## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para o desenvolvimento do sistema.
- **Estruturas de Dados**: Listas, Classes e Objetos.
- **Console Colors**: Para exibir mensagens coloridas no console.

## Estrutura do Projeto

A estrutura do projeto é organizada conforme o diagrama de classes a seguir:

```mermaid
classDiagram
    Usuario <|-- Administrador
    Usuario <|-- Cliente
    Cliente <|-- PessoaFisica
    Cliente <|-- PessoaJuridica
    
    class Usuario {
        <<abstract>>
        +Integer id
        +String nome
        +String email
        +TipoUsuario tipo
    }
    class Administrador{
      +Integer numeroRegistro
    }
    class Cliente{
        <<abstract>>
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
    
    class Veiculo {
        <<abstract>>
        +String placa
        +String modelo
        +int anoFabricacao
        +String cor
        +TipovEICULO tipo
        +Boolean disponivel
        +Integer numeroAgencia
    }
    
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
    A((Início)) --> B(Menu Principal)
    B --> C{Usuário}
    C -- Adm --> D(Menu Adm)
    C -- Cliente --> E(Menu Cliente)
    D -- CRUD --> F(Clientes)
    D -- CRUD --> G(Agências)
    D -- CRUD --> H(Veículos)
    E --> I(Alugar Veículo)
    E --> J(Devolver Veículo)
    E --> K(Gerar Comprovante)
    E --> L(Ver Histórico)
    E --> M(Mostrar extrato de aluguéis)

```

## Como Utilizar

### Clonando o repositório

1. **Clone do repositório**:
    ```bash
   git clone https://github.com/danielrdrgsn/projeto-rental-cars
   ```
2. **Navegue até o diretório do projeto**
   ```bash 
    cd projeto-rental-cars/src

### Executando o programa

```bash
  Java Main.java
```


## Participantes

- [Daniel Rodrigues](https://github.com/danielrdrgsn)
- [Eloise Helena](https://github.com/EloiseHelena)
- [Thiago Vinícius](https://github.com/Thiagoqdev)
- [Samuel Quaresma](https://github.com/squoliver83)
- [Vinícius Augusto](https://github.com/vinicius-augusto1)