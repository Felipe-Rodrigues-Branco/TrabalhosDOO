# **Sistema de Estacionamento \**

## **Descrição**

Sistema completo de gerenciamento de estacionamento desenvolvido em Java com interface gráfica Swing e banco de dados SQLite. O sistema permite cadastrar, listar, buscar, editar e remover veículos, além de calcular pagamentos baseados no tipo de veículo e tempo de permanência.

## **Estrutura do Projeto**

src/  
├── main/  
│   └── Main.java  
├── model/  
│   ├── Veiculo.java (classe abstrata)  
│   ├── Carro.java  
│   ├── Moto.java  
│   └── Caminhao.java  
├── controller/  
│   └── EstacionamentoController.java  
├── view/  
│   ├── TelaInicial.java  
│   ├── TelaCadastro.java  
│   ├── TelaListagem.java  
│   └── TelaBusca.java  
└── util/  
    └── DatabaseConnection.java

## **Funcionalidades**

###  **Gestão de Veículos**

* **Cadastro**: Adicionar novos veículos (Carro, Moto, Caminhão)  
* **Listagem**: Visualizar todos os veículos em tabela  
* **Busca**: Localizar veículo específico por placa  
* **Edição**: Modificar dados do veículo  
* **Remoção**: Excluir veículo do sistema

### **Cálculo de Pagamento**

* **Carro**: R$ 5,00 por hora  
* **Moto**: R$ 3,00 por hora  
* **Caminhão**: R$ 10,00 por hora

###  **Interface Gráfica**

* **3+ Telas funcionais** com navegação intuitiva  
* **Componentes Swing**: JFrame, JPanel, JTable, JButton, etc.  
* **Design responsivo** com cores e estilos consistentes  
* **Feedback visual** para todas as operações

###  **Banco de Dados**

* **SQLite integrado** (não requer instalação)  
* **Operações CRUD** completas  
* **Controle de transações** e tratamento de exceções  
* **Persistência automática** dos dados

## **Requisitos do Sistema**

* **Java 8+** (JDK/JRE)  
* **NetBeans IDE** (recomendado)  
* **SQLite JDBC Driver** (incluído automaticamente no Java moderno)

## **Manual de Uso**

### **Tela inicial.**

* **Cadastrar Veículo**: Abre formulário de cadastro  
* **Listar Veículos**: Mostra tabela com todos os veículos  
* **Buscar/Editar Veículo**: Permite localizar e modificar veículos  
* **Sair**: Encerra a aplicação

### **Cadastro de Veículo**

1. Preencha a **placa** (obrigatório, único)  
2. Digite o **modelo** do veículo  
3. Selecione o **tipo** (Carro/Moto/Caminhão)  
4. Clique em **Cadastrar**  
5. Use **Limpar** para resetar o formulário  
6. **Voltar** retorna à tela inicial

### **Listagem de Veículos**

* **Tabela** mostra: Placa, Modelo, Tipo, Data/Hora de entrada  
* **Atualizar**: Recarrega dados da tabela  
* **Remover Selecionado**: Exclui veículo selecionado  
* **Calcular Pagamento**: Calcula valor baseado nas horas  
* **Voltar**: Retorna à tela inicial

### **Busca e Edição**

1. Digite a **placa** no campo de busca  
2. Clique em **Buscar**  
3. Se encontrado, dados aparecem nos campos  
4. **Editar**: Habilita modificação dos campos  
5. **Salvar**: Confirma as alterações  
6. **Remover**: Exclui o veículo atual

## **Tratamento de Erros**

O sistema inclui tratamento completo de exceções:

### **Validações de Entrada**

* Campos obrigatórios não podem estar vazios  
* Placa deve ser única no sistema  
* Horas para pagamento devem ser numéricas e positivas

### **Mensagens de Feedback**

* ✅ **Sucesso**: Confirmações de operações bem-sucedidas  
* ⚠️ **Avisos**: Campos não preenchidos, seleções necessárias  
* ❌ **Erros**: Problemas de banco de dados, validações falhadas

### **Exceções de Banco**

* Conexão com banco de dados  
* Violações de constraint (placa duplicada)  
* Operações de CRUD mal-sucedidas

## **Banco de Dados**

### **Estrutura da Tabela `veiculos`**

CREATE TABLE veiculos (  
    placa TEXT PRIMARY KEY,  
    modelo TEXT NOT NULL,  
    tipo TEXT NOT NULL,  
    data\_entrada TEXT,  
    hora\_entrada TEXT  
);

### **Localização**

* Arquivo: `estacionamento.db` (criado automaticamente)  
* Localização: Diretório raiz do projeto  
* Formato: SQLite (arquivo único, portável)

## **Características Técnicas**

### **Padrão de Arquitetura**

* **MVC**: Model-View-Controller bem definido  
* **Separação de responsabilidades** entre camadas  
* **Reutilização de código** através de herança e polimorfismo

### **Programação Orientada a Objetos**

* **Herança**: Classe abstrata `Veiculo` com especializações  
* **Polimorfismo**: Métodos abstratos implementados nas subclasses  
* **Encapsulamento**: Atributos protegidos com métodos de acesso

### **Banco de Dados**

* **JDBC**: Conexão nativa com SQLite  
* **PreparedStatements**: Proteção contra SQL Injection  
* **Transações**: Controle adequado de operações

### **Interface Gráfica**

* **Layout Managers**: GridBagLayout, BorderLayout, FlowLayout  
* **Eventos**: ActionListeners para interatividade  
* **Componentes**: JTable, JComboBox, JTextField, JButton

