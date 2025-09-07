CREATE TABLE senhas (
    sen_id SERIAL PRIMARY KEY,
    sen_senha VARCHAR(255)
);

CREATE TABLE clientes (
    cli_id SERIAL PRIMARY KEY,
    cli_nome VARCHAR(255),
    cli_data_nascimento DATE,
    cli_genero VARCHAR(255),
    cli_cpf VARCHAR(255),
    cli_email VARCHAR(255),
    cli_sen_id INT,
    cli_cadastro_ativo BOOLEAN,
    FOREIGN KEY (cli_sen_id) REFERENCES senhas(sen_id)
);

CREATE TABLE bandeiras (
    ban_id SERIAL PRIMARY KEY,
    ban_bandeira VARCHAR(255)
);

CREATE TABLE cartoes_credito (
    ctc_id SERIAL PRIMARY KEY,
    ctc_ban_id INT,
    ctc_nome_titular VARCHAR(255),
    ctc_numero VARCHAR(255),
    ctc_codigo_seguranca VARCHAR(255),
    ctc_preferencial BOOLEAN,
    ctc_cli_id INT,
    FOREIGN KEY (ctc_ban_id) REFERENCES bandeiras(ban_id),
    FOREIGN KEY (ctc_cli_id) REFERENCES clientes(cli_id)
);

CREATE TABLE tipos_logradouro (
    tpl_id SERIAL PRIMARY KEY,
    tpl_tipo VARCHAR(255)
);

CREATE TABLE tipos_residencia (
    tpr_id SERIAL PRIMARY KEY,
    tpr_tipo VARCHAR(255)
);

CREATE TABLE enderecos (
    end_id SERIAL PRIMARY KEY,
    end_nome_identificador VARCHAR(255),
    end_pais VARCHAR(255),
    end_estado VARCHAR(255),
    end_cidade VARCHAR(255),
    end_tpl_id INT,
    end_logradouro VARCHAR(255),
    end_tpr_id INT,
    end_numero VARCHAR(255),
    end_bairro VARCHAR(255),
    end_cep VARCHAR(255),
    end_observacao VARCHAR(255),
    end_cobranca BOOLEAN,
    end_entrega BOOLEAN,
    end_cli_id INT,
    FOREIGN KEY (end_tpl_id) REFERENCES tipos_logradouro(tpl_id),
    FOREIGN KEY (end_tpr_id) REFERENCES tipos_residencia(tpr_id),
    FOREIGN KEY (end_cli_id) REFERENCES clientes(cli_id)
);

CREATE TABLE tipos_telefone (
    tpt_id SERIAL PRIMARY KEY,
    tpt_tipo VARCHAR(255)
);

CREATE TABLE telefones (
    tel_id SERIAL PRIMARY KEY,
    tel_ddd VARCHAR(255),
    tel_tpt_id INT,
    tel_numero VARCHAR(255),
    tel_cli_id INT,
    FOREIGN KEY (tel_tpt_id) REFERENCES tipos_telefone(tpt_id),
    FOREIGN KEY (tel_cli_id) REFERENCES clientes(cli_id)
);

