create database FilmesBanco;
use FilmesBanco;



create table catalogo_filmes(
idFilme int not null auto_increment unique primary key,
tituloFilme varchar (50) not null unique,
generoFilme varchar (50) not null, 
plataforma varchar (50) not null,
faixaEtaria int not null
);

create table tb_Usuario(
id_usuario int primary key unique not null auto_increment,
nome_usuario varchar (50) not null,
status_usuario varchar (25) not null,
nota int not null,
Login varchar(50) not null unique,
Senha varchar(50) not null
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

