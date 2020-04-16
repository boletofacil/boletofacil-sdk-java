## SDK Java para integração com o Boleto Fácil

Este SDK (Software Development Kit) para o Boleto Fácil tem como objetivo abstrair, para desenvolvedores de aplicações Java, os detalhes de comunicação com a [API do Boleto Fácil](https://www.boletobancario.com/boletofacil/integration/integration.html), tanto com o servidor de [produção](https://www.boletobancario.com/boletofacil/) como com o servidor de testes ([sandbox](https://sandbox.boletobancario.com/boletofacil/)), de modo que o desenvolvedor possa se concentrar na lógica de negócio de sua aplicação.

## Requisitos

* Java 1.7 ou superior

## Integração

#### Gradle
```gradle
repositories {
	mavenCentral()
}
dependencies {
	compile 'com.boletobancario:boletofacilsdk:1.0.0'
}
```

#### Maven
```xml
<dependency>
    <groupId>com.boletobancario</groupId>
    <artifactId>boletofacilsdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Limitações

O único item da API do Boleto Fácil que essa SDK não contempla é a [notificação de pagamentos](https://www.boletobancario.com/boletofacil/integration/integration.html#notificacao) para aplicações Web, através da URL de notificação. Nesse caso, tanto a lógica de captura das requisições POST enviadas pelo Boleto Fácil com os dados dos pagamentos como a lógica da baixa das cobranças pagas ficam a cargo do sistema integrado com o Boleto Fácil.

## Guia de uso

Para usar o SDK do Boleto Fácil é necessário definir dois itens:

1. O ambiente: produção (`PRODUCTION`) ou testes (`SANDBOX`)
2. O token do favorecido, o qual deve ser definido na área de **integração** do ambiente escolhido ([aqui](https://www.boletobancario.com/boletofacil/integration/integration.html#token) para produção ou [aqui](https://sandbox.boletobancario.com/boletofacil/integration/integration.html#token) para sandbox)

Exemplo:
```java
// Cria uma instância do SDK que irá enviar requisições ao ambiente de testes do Boleto Fácil (Sandbox)
BoletoFacil boletoFacil = new BoletoFacil(BoletoFacilEnvironment.SANDBOX, "XYZ12345"); // XYZ12345 é o token
```

### Gerando uma cobrança

`Charge` é a classe que representa uma cobrança do Boleto Fácil e que contém os atributos relacionados a ela, que 
são exatamente os atributos disponibilizados pela API do Boleto Fácil e podem ser conferidos [aqui](https://www.boletobancario.com/boletofacil/integration/integration.html#cobrancas). 

Dentre os atributos da cobrança estão os dados do pagador, que são definidos na classe `Payer`.

```java
Payer payer = new Payer();
payer.setName("Pagador teste - SDK Java");
payer.setCpfCnpj("11122233300");

Charge charge = new Charge();
charge.setDescription("Cobrança teste gerada pelo SDK Java");
charge.setAmount(BigDecimal.valueOf(123.45));
charge.setPayer(payer);

ChargeResponse response = boletoFacil.issueCharge(charge);
if (response.isSuccess()) {
	for (Charge c : response.getData().getCharges()) {
		System.out.println(c);
	}
}
```

A classe `ChargeResponse` indica se a requisição foi bem sucedida ou não (da mesma forma que todas as classes que herdam da superclasse `Response` no SDK) e, além disso, contém a lista de cobranças que foram geradas pela requisição, em uma lista de objetos do tipo `Charge`.


### Consulta de saldo

Por padrão, as requisições feitas pelo SDK desserializam o retorno em **JSON** para popular os objetos com as informações das requisições, mas o SDK também provê a possibilidade de alterar a formatação do retorno da API para **XML**, conforme pode ser visto no exemplo abaixo:

```java
FetchBalanceResponse response = boletoFacil.fetchBalance(ResponseType.XML);
if (response.isSuccess()) {
	System.out.println(response.getData());
}
```


### Solicitação de transferência

Mesmo que se deseje solicitar uma transferência com o saldo total, é necessário passar um parâmetro da classe `Transfer`, sem o atributo `amount` definido, no caso.

```java
Transfer transfer = new Transfer();
TransferResponse response = boletoFacil.requestTransfer(transfer);
if (response.isSuccess()) {
	System.out.println(response);
}
```

Como a resposta de solicitação transferência contém apenas se a requisição foi bem sucedida ou não, não se aplica o método `getData()` para ela.


### Consulta de pagamentos e cobranças

Para esta requisição, é usado um objeto da classe `ListChargesDates` para definir as datas usadas no filtro da consulta. No exemplo abaixo, são usadas apenas as datas de vencimento das cobranças desejadas.

```java
Calendar endDate = Calendar.getInstance();
endDate.add(Calendar.DAY_OF_WEEK, 5);
ListChargesDates dates = new ListChargesDates();
dates.setBeginDueDate(Calendar.getInstance());
dates.setEndDueDate(endDate);

ListChargesResponse response = boletoFacil.listCharges(dates);
if (response.isSuccess()) {
	for (Charge c : response.getData().getCharges()) {
		System.out.println(c);
	}
}
```


### Criação de favorecido (API Avançada)

A API avançada também está disponível no SDK. Segue abaixo um exemplo de criação de favorecido, com os principais atributos (e objetos) relacionados.

```java
Person person = new Person();
person.setName("Favorecido do SDK Java");
person.setCpfCnpj("11122233300");

BankAccount account = new BankAccount();
account.setBankAccountType(BankAccountType.CHECKING);
account.setBankNumber("237");
account.setAgencyNumber("123");
account.setAccountNumber("4567");
account.setAccountComplementNumber(0);

Address address = new Address();
address.setStreet("Rua Teste");
address.setNumber("123");
address.setCity("4106902"); // Deve ser passado o Código de município do IBGE, assim como na API
address.setState("PR");
address.setPostcode("12345000");

Payee payee = new Payee();
payee.setName("Favorecido do SDK Java");
payee.setCpfCnpj("11122233300");
payee.setEmail("email@teste.com");
payee.setPassword("senha");
payee.setBirthDate(Calendar.getInstance()); // Não funciona: o Boleto Fácil rejeita favorecidos menores de idade
payee.setPhone("(99) 91234-4321");
payee.setLinesOfBusiness("Linha de negócio");
payee.setAccountHolder(person);
payee.setBankAccount(account);
payee.setCategory(Category.OTHER);
payee.setAddress(address);
payee.setBusinessAreaId(1000);

PayeeResponse response = boletoFacil.createPayee(payee);
if (response.isSuccess()) {
	System.out.println(response.getData());
}
```

A tabela com os códigos de município do IBGE pode ser consultada [aqui](http://www.ibge.gov.br/home/geociencias/areaterritorial/area.shtm).


## Aplicação cliente de exemplo

Juntamente com o projeto do SDK há um outro projeto de um cliente de exemplo (aplicação console) que contém exemplos de todas as chamadas disponibilizadas pelo SDK.


## Suporte

Em caso de dúvidas, problemas ou sugestões, não hesite em contatar nossa [equipe de implantação](mailto:implantacao@juno.com.br).

