CREATE TABLE senhas
(
    sen_id    SERIAL PRIMARY KEY,
    sen_senha VARCHAR(255)
);

CREATE TABLE clientes
(
    cli_id              SERIAL PRIMARY KEY,
    cli_nome            VARCHAR(255),
    cli_data_nascimento DATE,
    cli_genero          VARCHAR(255),
    cli_cpf             VARCHAR(255),
    cli_email           VARCHAR(255),
    cli_sen_id          INT,
    cli_cadastro_ativo  BOOLEAN,
    FOREIGN KEY (cli_sen_id) REFERENCES senhas (sen_id)
);

CREATE TABLE bandeiras
(
    ban_id       SERIAL PRIMARY KEY,
    ban_bandeira VARCHAR(255)
);

CREATE TABLE tipos_cupom
(
    tpc_id   SERIAL PRIMARY KEY,
    tpc_tipo VARCHAR(255)
);

CREATE TABLE formas_pagamento
(
    fpg_id SERIAL PRIMARY KEY
);

CREATE TABLE cartoes_credito
(
    ctc_id               INT PRIMARY KEY,
    ctc_ban_id           INT,
    ctc_nome_titular     VARCHAR(255),
    ctc_numero           VARCHAR(255),
    ctc_codigo_seguranca VARCHAR(255),
    ctc_preferencial     BOOLEAN,
    ctc_cli_id           INT,
    FOREIGN KEY (ctc_id) REFERENCES formas_pagamento (fpg_id) ON DELETE CASCADE,
    FOREIGN KEY (ctc_ban_id) REFERENCES bandeiras (ban_id),
    FOREIGN KEY (ctc_cli_id) REFERENCES clientes (cli_id)
);

CREATE TABLE cupons
(
    cps_id     INT PRIMARY KEY,
    cps_codigo VARCHAR(255),
    cps_valor  INT,
    cps_cli_id INT,
    cps_tpc_id INT,
    FOREIGN KEY (cps_id) REFERENCES formas_pagamento (fpg_id) ON DELETE CASCADE,
    FOREIGN KEY (cps_cli_id) REFERENCES clientes (cli_id),
    FOREIGN KEY (cps_tpc_id) REFERENCES tipos_cupom (tpc_id)
);

CREATE TABLE pagamentos
(
    pag_id          SERIAL PRIMARY KEY,
    pag_fpg_id      INT,
    pag_porcentagem DOUBLE PRECISION,
    FOREIGN KEY (pag_fpg_id) REFERENCES formas_pagamento (fpg_id)
);

CREATE TABLE tipos_logradouro
(
    tpl_id   SERIAL PRIMARY KEY,
    tpl_tipo VARCHAR(255)
);

CREATE TABLE tipos_residencia
(
    tpr_id   SERIAL PRIMARY KEY,
    tpr_tipo VARCHAR(255)
);

CREATE TABLE enderecos
(
    end_id                 SERIAL PRIMARY KEY,
    end_nome_identificador VARCHAR(255),
    end_pais               VARCHAR(255),
    end_estado             VARCHAR(255),
    end_cidade             VARCHAR(255),
    end_tpl_id             INT,
    end_logradouro         VARCHAR(255),
    end_tpr_id             INT,
    end_numero             VARCHAR(255),
    end_bairro             VARCHAR(255),
    end_cep                VARCHAR(255),
    end_observacao         VARCHAR(255),
    end_cobranca           BOOLEAN,
    end_entrega            BOOLEAN,
    end_cli_id             INT,
    FOREIGN KEY (end_tpl_id) REFERENCES tipos_logradouro (tpl_id),
    FOREIGN KEY (end_tpr_id) REFERENCES tipos_residencia (tpr_id),
    FOREIGN KEY (end_cli_id) REFERENCES clientes (cli_id)
);

CREATE TABLE tipos_telefone
(
    tpt_id   SERIAL PRIMARY KEY,
    tpt_tipo VARCHAR(255)
);

CREATE TABLE telefones
(
    tel_id     SERIAL PRIMARY KEY,
    tel_ddd    VARCHAR(255),
    tel_tpt_id INT,
    tel_numero VARCHAR(255),
    tel_cli_id INT,
    FOREIGN KEY (tel_tpt_id) REFERENCES tipos_telefone (tpt_id),
    FOREIGN KEY (tel_cli_id) REFERENCES clientes (cli_id)
);

CREATE TABLE autores
(
    aut_id   SERIAL PRIMARY KEY,
    aut_nome VARCHAR(255)
);

CREATE TABLE categorias_livros
(
    ctl_id   SERIAL PRIMARY KEY,
    ctl_nome VARCHAR(255)
);

CREATE TABLE editoras
(
    edi_id   SERIAL PRIMARY KEY,
    edi_nome VARCHAR(255)
);

CREATE TABLE livros_base
(
    lvb_id      SERIAL PRIMARY KEY,
    lvb_titulo  VARCHAR(255),
    lvb_sinopse VARCHAR(1000),
    lvb_ano     INT
);

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

CREATE TABLE estoque_publicacoes
(
    esp_id         SERIAL PRIMARY KEY,
    esp_pub_id     INT,
    esp_quantidade INT,
    FOREIGN KEY (esp_pub_id) REFERENCES publicacoes (pub_id)
);

CREATE TABLE carrinhos
(
    car_id     SERIAL PRIMARY KEY,
    car_cli_id INT,
    FOREIGN KEY (car_cli_id) REFERENCES clientes (cli_id)
);

CREATE TABLE produtos
(
    prd_id     SERIAL PRIMARY KEY,
    prd_pub_id INT,
    prd_preco  INT,
    FOREIGN KEY (prd_pub_id) REFERENCES publicacoes (pub_id)
);

CREATE TABLE itens
(
    its_id         SERIAL PRIMARY KEY,
    its_prd_id     INT,
    its_car_id     INT,
    its_quantidade INT,
    FOREIGN KEY (its_prd_id) REFERENCES produtos (prd_id),
    FOREIGN KEY (its_car_id) REFERENCES carrinhos (car_id)
);