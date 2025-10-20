# MyNetFlix




Este projeto é um sistema desenvolvido em **Java** com integração ao banco de dados **MySQL**, com o objetivo de gerenciar filmes. O sistema permite ao usuário fazer cadastro, login (como usuário ou adm), adicionar filmes, ver catálogo, remover filmes, listar,  filmes assistidos e etc. 
---




## 🚀 Funcionalidades




- Cadastro de **usuários** com as opções de id, nome completo, data de nascimento, senha e confirmação de senha.
- Login, com o cadastro salvo no banco de dados.
- Registro de filmes, com opções de faixa etária, gênero e plataforma.
- Visualização do catálogo para mudar status do filme.
- Classificação de filmes com opções de status e sinopse.




---




## 🛠️ Tecnologias Utilizadas




- **Java version 21.0.2**  
- **Swing** para interface gráfica  
- **MySQL** para persistência de dados  
- **DAO/DTO Pattern** para organização do código  








---




## 📂 Estrutura do Projeto
  src/




├── dao/ # Classes de acesso ao banco de dados




├── dto/ # Objetos de transferência de dados




├── img/ # Imagens dos ícones

└──  view/ # Interfaces gráficas (Swing)








---




## 📅 Histórico de Atualizações




- **v1.0.0** – (16/10/2025)  
  - Primeira versão estável.  
  - Correção de erros.


- **v0.3.0** – (1-15/10/2025)  
  - Adição de métodos.  
  - Correção de erros.  




- **v0.2.0** – (29/09/2025)  
  - Criação base das páginas e métodos.  




- **v0.1.0** – (22/09/2025)  
  - Criação inicial do projeto (DAO, DTO e VIEW).




---




## 👨‍💻 Desenvolvedores




Este projeto foi idealizado e desenvolvido por:  
##  Rafaela Lima
## Mateus Diolinda




---




## Informações sobre o Banco de Dados usado




— User: root; Senha: root;
Driver de uso: com.mysql.jdbc.Driver; Url de uso: jdbc:mysql://localhost:3306/FilmesBanco;
Tipo de banco: SGDB;












## 📜 Licença




Este projeto está licenciado sob a licença **MIT** – veja o arquivo [LICENSE](LICENSE) para mais detalhes.




---




**Script banco**


 CREATE DATABASE FilmesBanco; 
 USE FilmesBanco;

 CREATE TABLE catalogo_Filmes
 ( idFilme INT PRIMARY KEY AUTO_INCREMENT,
 tituloFilme VARCHAR(50) NOT NULL UNIQUE, 
 generoFilme VARCHAR(50) NOT NULL, 
 plataforma VARCHAR(50) NOT NULL, 
 faixaEtaria INT NOT NULL, 
 sinopse VARCHAR(300) NOT NULL, nota INT );

CREATE TABLE tb_usuario( id_usuario INT PRIMARY KEY AUTO_INCREMENT,
 nome_usuario VARCHAR(50) NOT NULL,
 Login VARCHAR(50) NOT NULL UNIQUE, 
 Senha VARCHAR(50) NOT NULL );

select * from tb_usuario;

CREATE TABLE usuario_filme1 ( id_usuario INT NOT NULL, 
id_filme INT NOT NULL,
 status_visualizacao VARCHAR(25) NOT NULL,
nota_usuario INT, PRIMARY KEY (id_usuario, id_filme),
FOREIGN KEY (id_usuario) REFERENCES tb_Usuario(id_usuario), FOREIGN KEY (id_filme) REFERENCES Catalogo_Filmes(idFilme) );

create table cadastro_usuarios( 
idCadastro int primary key not null, 
nome varchar(50) not null unique,
 senha varchar(10) not null );

SELECT * FROM login WHERE login = 'Admin' AND senha = '1234';

INSERT INTO Catalogo_Filmes(tituloFilme, generoFilme, plataforma, faixaEtaria, sinopse, nota) VALUES 
("Interestelahr", "Ficção Científica", "Disney+", 12, "Exploradores viajam por um buraco de minhoca para salvar a humanidade.", 9), 
("Matrixk", "Ação", "HBO Max", 16, "Um hacker descobre que o mundo é uma simulação.", 10),
 ("O Poderosjo Chefão", "Drama", "Netflix", 18, "Saga da família mafiosa Corleone.", 10);







