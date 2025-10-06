**Script banco**

create database FilmesBanco;
use FilmesBanco;



CREATE TABLE Catalogo_Filmes(
    idFilme INT PRIMARY KEY AUTO_INCREMENT,
    tituloFilme VARCHAR(50) NOT NULL UNIQUE,
    generoFilme VARCHAR(50) NOT NULL,
    plataforma VARCHAR(50) NOT NULL,
    faixaEtaria INT NOT NULL,
    sinopse VARCHAR(300) NOT NULL,
    nota INT NOT NULL
);

CREATE TABLE tb_usuario(
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome_usuario VARCHAR(50) NOT NULL,
    Login VARCHAR(50) NOT NULL UNIQUE,
    Senha VARCHAR(50) NOT NULL
);

select * from catalogo_filmes;
describe catalogo_filmes;

select * from tb_Usuario;
describe tb_Usuario;

create table cadastro_usuarios(
idCadastro int primary key not null,
nome varchar(50) not null unique,
dataNascimento varchar(25) not null,
senha varchar(10) not null,
confirSenha varchar (10) not null
);

select * from cadastro_usuario;
describe cadastro_usuario;

CREATE TABLE usuario_filme1 (
    id_usuario INT NOT NULL,
    id_filme INT NOT NULL,
    status_visualizacao VARCHAR(25) NOT NULL,  
    nota_usuario INT,
    PRIMARY KEY (id_usuario, id_filme),         
    FOREIGN KEY (id_usuario) REFERENCES tb_Usuario(id_usuario),
    FOREIGN KEY (id_filme) REFERENCES Catalogo_Filmes(idFilme)
);


CREATE TABLE login(
    id INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(50) NOT NULL
);

