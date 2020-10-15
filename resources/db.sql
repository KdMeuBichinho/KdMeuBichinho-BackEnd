CREATE SCHEMA kdmeubichinho;
USE kdmeubichinho;

CREATE TABLE pessoa (
    id_pessoa INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(80) NOT NULL UNIQUE,
    cep VARCHAR(10) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    celular VARCHAR(16) NOT NULL,
    senha VARCHAR(20) NOT NULL
)ENGINE = InnoDB;

CREATE TABLE animal (
    id_animal INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(15) NOT NULL,
    sexo ENUM("Macho", "Fêmea", "Não sei") NOT NULL,
    idade ENUM("Filhote", "Adulto") NOT NULL,
    porte ENUM("Pequeno", "Médio", "Grande") NOT NULL,
    castrado BOOL NOT NULL,
    vacinano BOOL NOT NULL,
    nome VARCHAR(120),
    cep VARCHAR(10) NOT NULL
)ENGINE = InnoDB;

CREATE TABLE anuncio (
    id_anuncio int PRIMARY KEY AUTO_INCREMENT,
    tipo ENUM("Fugiu de Casa", "Encontrei na rua", "Quero doar") NOT NULL,
    status_anuncio BOOL NOT NULL,
    data_criacao DATE,
    fk_id_pessoa INT,
    fk_id_animal INT,
	FOREIGN KEY (fk_id_pessoa) REFERENCES pessoa(id_pessoa),
	FOREIGN KEY (fk_id_animal) REFERENCES animal(id_animal)
)ENGINE = InnoDB;

CREATE TABLE mensagem (
    id_historico INT PRIMARY KEY AUTO_INCREMENT,
    data_mensagem DATE,
    mensagem VARCHAR(255),
    fk_id_pessoa INT,
    fk_id_anuncio INT,
	FOREIGN KEY (fk_id_pessoa) REFERENCES pessoa(id_pessoa),
	FOREIGN KEY (fk_id_anuncio) REFERENCES anuncio(id_anuncio)
)ENGINE = InnoDB;

CREATE TABLE foto (
    id_foto INT PRIMARY KEY AUTO_INCREMENT,
    caminho VARCHAR(255),
    fk_id_animal INT,
	FOREIGN KEY (fk_id_animal) REFERENCES animal(id_animal)
)ENGINE = InnoDB;