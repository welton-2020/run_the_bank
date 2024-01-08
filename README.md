<h1>Run The Bank</h1>

>Status: Em desenvolvimento ⚠️

##Tecnologias usadas:
<table>
  <tr>
    <td>Java</td>
    <td>Spring Boot</td>
    <td>Spring Data JPA</td>
    <td>Banco de Dados H2</td>
  </tr>
  <tr>
    <td>17.*</td>
    <td>3.*</td>
    <td>3.*</td>
    <td>2.*</td>
  </tr>
</table>

## Índice

+ [Instalação](#instalação)
+ [Como usar](#como-usar)
+ [Endpoints](#endpoints)
+ [Payloads de Exemplo](#payloads-de-exemplo)
+ [Swagger](#swagger)
+ [Contribuição](#contribuição)
+ [Licença](#licença)

## 🚀 Instalação

>Instruções de como instalar e configurar o projeto localmente.

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
# Comandos adicionais de instalação...
```

## 📋 Como usar

>Explicação sobre como usar o projeto após a instalação.

## 🔗 Endpoints

### `/conta/criar` (POST)

Cria uma nova conta.

Payload de exemplo:

```json
{
  "campo1": "valor1",
  "campo2": "valor2"
}
```

### `/conta/buscar-todos` (GET)

>Recupera todas as contas.

### `/conta/find-by-id/{id}` (GET)

>Recupera uma conta pelo ID.

### `/conta/update-for-id/{id}` (PUT)

Atualiza uma conta pelo ID.

Payload de exemplo:

```json
{
  "campo1": "novo-valor1",
  "campo2": "novo-valor2"
}
```

### `/conta/delete-for-id/{id}` (DELETE)

Inativa uma conta pelo ID.

### `/conta/transferencia` (PUT)

Realiza uma transferência entre contas.

Parâmetros:

- `idDetentor` (obrigatório): ID da conta detentora.
- `idDestino`: ID da conta de destino.
- `valor`: Valor da transferência.

## 📑 Payloads de Exemplo

Inclua aqui alguns exemplos de payloads para facilitar os testes.

## 📘 Swagger

A documentação Swagger pode ser acessada [aqui](#).

## 🤝 Contribuição

Instruções para contribuir com o projeto.

1. Faça um fork do projeto
2. Crie uma branch para a sua feature (`git checkout -b feature/nova-feature`)
3. Faça commit das suas alterações (`git commit -am 'Adiciona nova feature'`)
4. Faça push para a branch (`git push origin feature/nova-feature`)
5. Crie um novo Pull Request

## 📄 Licença

Este projeto está licenciado sob a licença XYZ. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
```
