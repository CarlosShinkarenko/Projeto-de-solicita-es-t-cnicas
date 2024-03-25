create table permissao(

	idpermissao serial not null,
	nomepermissao varchar(50),
	
	constraint pk_pms_idpermissao primary key (idpermissao),
	constraint un_pms_comepermissao unique (nomepermissao)
	
);

create table usuario(

	idusuario serial not null,
	nome varchar(120) not null,
	email varchar(120) not null,
	senha text not null,
	account_non_expired boolean not null,
	account_non_locked boolean not null,
	credential_non_expired boolean not null,
	enabled boolean not null,
	
	constraint pk_usr_idusuario primary key (idusuario),
	constraint un_usr_email unique (email)

);

create table permissao_usuario(
	idusuario integer not null,
	idpermissao integer not null,
	
	constraint pk_pmu_idpermissaousuario primary key (idpermissao, idusuario),
	constraint fk_pmu_idusuario foreign key (idusuario) references usuario (idusuario),
	constraint fk_pmu_idpermissao foreign key (idpermissao) references permissao (idpermissao)
);

create table chamados(

	idchamado serial not null,
	idusuario integer not null,
	assunto varchar(100) not null,
	tipo varchar(20) not null, 
	descricao text,
	data_chamado date not null,
	observacoes text,
	estado varchar(20),
	comentarios text,

	constraint pk_chm_idchamado primary key (idchamado),
	constraint fk_chm_idusuario foreign key (idusuario) references usuario (idusuario)
	
);

alter table chamados alter column data_chamado set default current_date;

create table arquivos (

	idarquivo serial not null,
	idchamado integer not null,
	nomearquivo varchar(120) not null,
	tipoarquivo varchar (50) not null,
	urlarquivo varchar(120) not null,
	tamanho integer not null,
	
	constraint pk_arq_idarquivo primary key (idarquivo),
	constraint fk_arq_idchamado foreign key (idchamado) references chamados (idchamado),
	constraint un_arq_urlarquivo unique (urlarquivo)
	
);

insert into permissao (nomepermissao) values ('administrador');
insert into permissao (nomepermissao) values ('usuario');
insert into permissao (nomepermissao) values ('tecnico');

select * from permissao;

insert into 
	usuario (nome, email, senha, account_non_expired, account_non_locked, credential_non_expired, enabled) 
values 
	('Carlos', 'carlos@email.com', 'e6d579e74cc528dadd598c6ef412510b56491a031c7240f0be5b20e28b0a1cc562fca5e23393853b', true, true, true, true); --Senha: admin123
insert into 
	usuario (nome, email, senha, account_non_expired, account_non_locked, credential_non_expired, enabled) 
values 
	('Jose', 'jose@email.com', '6e7de92471be63ec1d67f7393bc40e12b06f9861c2b87dd0be2d1ef386c6760b7a735303083a1df5', true, true, true, true); --Senha: user123
insert into 
	usuario (nome, email, senha, account_non_expired, account_non_locked, credential_non_expired, enabled) 
values 
	('Thiago', 'thiago@email.com', '428cc136f17387aceaebd827fe338307ce65f49759ce79935b03b8b8f7c654ea25c844d7257f9304', true, true, true, true); -- Senha: tec123
insert into 
	usuario (nome, email, senha, account_non_expired, account_non_locked, credential_non_expired, enabled) 
values 
	('Joaquim', 'joaquim@email.com', 'fad950815c69f7e69ab4344536c22a5e47e28128b452e292dbdcbcc3c193ab788145616c9c5380ac', true, true, true, true); --Senha: user234
insert into 
	usuario (nome, email, senha, account_non_expired, account_non_locked, credential_non_expired, enabled) 
values 
	('Jacinto', 'jacinto@email.com', '4f8ec4a6f6a11d553b1393e0a78a97704e612c047f6af6d6a9a14d57bdef8ae3e3db67a9ea2b734b', true, true, true, true); --Senha: user345

select * from usuario;

insert into permissao_usuario ( idusuario, idpermissao ) values (1,1);
insert into permissao_usuario ( idusuario, idpermissao ) values (2,2);
insert into permissao_usuario ( idusuario, idpermissao ) values (3,3);
insert into permissao_usuario ( idusuario, idpermissao ) values (4,2);
insert into permissao_usuario ( idusuario, idpermissao ) values (5,2);

select * from permissao_usuario;

insert into 
	chamados (idusuario, assunto, tipo, descricao, data_chamado ,estado) 
values 
	(2, 'Lentidão na internet', 'Assistência', 'Desde o dia 05/02 a internet está lenta.', '2024-02-06', 'Resolvido');
	
insert into 
	chamados (idusuario, assunto, tipo, descricao, data_chamado, observacoes, estado) 
values 
	(4, 'Segunda via de boleto', 'Financeiro', 'O meu banco não consegue ler o boleto do mês de março.', '2024-03-01', 'No mês de fevereiro tive o mesmo problema', 'Em andamento' );

insert into 
	chamados (idusuario, assunto, tipo, descricao, data_chamado, estado) 
values 
	(5, 'Rompimento da fibra do modem', 'Visita técnica', 'Ontem a fibra do meu modem rompeu', '2024-03-15', 'Aberto' );

select * from usuario;
