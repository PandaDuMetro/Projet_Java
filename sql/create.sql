drop database if exists badminton;
create database badminton;

use badminton;
	
create table joueurs
(
	id integer auto_increment,
    nom varchar(32) not null,
    stamina integer,
    power integer,
    sex bool,
    points integer,
    primary key(id)
);

create table tournois
(
	nom varchar(32) not null,
    gagnant varchar(32),
    primary key(nom)
);

create table matchs 
(
	id integer auto_increment,
    tournoi varchar(32),
    ronde integer,
    joueur1 integer,
    joueur2 integer,
    primary key(id)
);

create table points
(
	id integer not null auto_increment,
	idMatch integer not null,
    numeroPoint integer not null,
    nomGagnant varchar(32),
    primary key(id)
    
);