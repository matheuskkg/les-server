--create sequence senhas_seq;
--create sequence clientes__seq;
--create sequence bandeiras_seq;
--create sequence cartoes_credito_seq;
--create sequence tipos_logradouro_seq;
--create sequence tipos_residencia_seq;
--create sequence enderecos_seq;
--create sequence tipos_telefone_seq;
--create sequence telefones_seq;
CREATE SEQUENCE IF NOT EXISTS public.bandeiras_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY bandeiras.ban_id;

CREATE SEQUENCE IF NOT EXISTS public.cartoes_credito_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY cartoes_credito.ctc_id;

CREATE SEQUENCE IF NOT EXISTS public.clientes_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY clientes.cli_id;

CREATE SEQUENCE IF NOT EXISTS public.enderecos_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY enderecos.end_id;

CREATE SEQUENCE IF NOT EXISTS public.senhas_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY senhas.sen_id;

CREATE SEQUENCE IF NOT EXISTS public.telefones_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY telefones.tel_id;

CREATE SEQUENCE IF NOT EXISTS public.tipos_logradouro_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY tipos_logradouro.tpl_id;

CREATE SEQUENCE IF NOT EXISTS public.tipos_residencia_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY tipos_residencia.tpr_id;

CREATE SEQUENCE IF NOT EXISTS public.tipos_telefone_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY tipos_telefone.tpt_id;