# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Contexto (
  id                        varchar(255) not null,
  nome                      varchar(255),
  grupo                     varchar(255),
  sensor_id                 varchar(255),
  constraint pk_Contexto primary key (id))
;

create table Entidade (
  id                        varchar(255) not null,
  contexto_id               varchar(255),
  nome                      varchar(255),
  constraint pk_Entidade primary key (id))
;

create table Medidas (
  valores                   varchar(255) not null,
  sensor_id                 varchar(255),
  constraint pk_Medidas primary key (valores))
;

create table pessoa (
  n_cid                     integer identity(1,1) not null,
  nome                      varchar(255),
  utilizador                varchar(255),
  pass                      varchar(255),
  contacto                  integer,
  entidade_id               varchar(255),
  area_id                   varchar(255),
  cargo_id                  varchar(255),
  constraint pk_pessoa primary key (n_cid))
;

create table Sensor (
  id                        varchar(255) not null,
  constraint pk_Sensor primary key (id))
;

create table gen_user (
  utilizador                varchar(255) not null,
  nome                      varchar(255),
  pass                      varchar(255),
  passconf                  varchar(255),
  constraint pk_gen_user primary key (utilizador))
;




# --- !Downs




IF OBJECT_ID('Contexto', 'U') IS NOT NULL drop table Contexto;

IF OBJECT_ID('Entidade', 'U') IS NOT NULL drop table Entidade;

IF OBJECT_ID('Medidas', 'U') IS NOT NULL drop table Medidas;

IF OBJECT_ID('pessoa', 'U') IS NOT NULL drop table pessoa;

IF OBJECT_ID('Sensor', 'U') IS NOT NULL drop table Sensor;

IF OBJECT_ID('gen_user', 'U') IS NOT NULL drop table gen_user;

