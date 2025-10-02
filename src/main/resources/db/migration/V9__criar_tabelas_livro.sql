CREATE TABLE autores
(
    aut_id   SERIAL PRIMARY KEY,
    aut_nome VARCHAR(255)
);

CREATE SEQUENCE IF NOT EXISTS public.autores_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY autores.aut_id;

CREATE TABLE categorias_livros
(
    ctl_id   SERIAL PRIMARY KEY,
    ctl_nome VARCHAR(255)
);

CREATE SEQUENCE IF NOT EXISTS public.categorias_livros_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY categorias_livros.ctl_id;

CREATE TABLE editoras
(
    edi_id   SERIAL PRIMARY KEY,
    edi_nome VARCHAR(255)
);

CREATE SEQUENCE IF NOT EXISTS public.editoras_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY editoras.edi_id;

CREATE TABLE livros_base
(
    lvb_id      SERIAL PRIMARY KEY,
    lvb_titulo  VARCHAR(255),
    lvb_sinopse VARCHAR(1000),
    lvb_ano     INT
);

CREATE SEQUENCE IF NOT EXISTS public.livros_base_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY livros_base.lvb_id;

CREATE TABLE autores_livros
(
    aul_lvb_id INT,
    aul_aut_id INT,
    PRIMARY KEY (aul_lvb_id, aul_aut_id),
    FOREIGN KEY (aul_lvb_id) REFERENCES livros_base (lvb_id),
    FOREIGN KEY (aul_aut_id) REFERENCES autores (aut_id)
);

CREATE TABLE categorias_livros_base
(
    ctb_lvb_id INT,
    ctb_ctl_id INT,
    PRIMARY KEY (ctb_lvb_id, ctb_ctl_id),
    FOREIGN KEY (ctb_lvb_id) REFERENCES livros_base (lvb_id),
    FOREIGN KEY (ctb_ctl_id) REFERENCES categorias_livros (ctl_id)
);

CREATE TABLE publicacoes
(
    pub_id             SERIAL PRIMARY KEY,
    pub_lvb_id         INT,
    pub_isbn           VARCHAR(255),
    pub_codigo_barras  VARCHAR(255),
    pub_numero_paginas INT,
    pub_altura         DOUBLE PRECISION,
    pub_largura        DOUBLE PRECISION,
    pub_peso           DOUBLE PRECISION,
    pub_profundidade   DOUBLE PRECISION,
    pub_edicao         VARCHAR(255),
    pub_edi_id         INT,
    FOREIGN KEY (pub_lvb_id) REFERENCES livros_base (lvb_id),
    FOREIGN KEY (pub_edi_id) REFERENCES editoras (edi_id)
);

CREATE SEQUENCE IF NOT EXISTS public.publicacoes_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY publicacoes.pub_id;