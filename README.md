# MyNetFlix



create database FilmesBanco;
use FilmesBanco;

create table Catalogo_Filmes(
id_Filme int primary key unique not null auto_increment,
tituloFilme varchar (50) not null unique,
generoFilme varchar (50) not null,
plataforma varchar (50) not null,
faixaEtaria int not null

);


create table tb_Usuario(
id_usuario int primary key unique not null auto_increment,
nome_usuario varchar(50) not null,
status_usuario varchar(25) not null,
nota_usuario int not null,
Login varchar(50) not null unique,
Senha varchar(50) not null
);

describe Catalogo_Filmes;







