CREATE SEQUENCE IF NOT EXISTS public.bandeiras_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY bandeiras.ban_id;

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

CREATE SEQUENCE IF NOT EXISTS public.formas_pagamento_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY formas_pagamento.fpg_id;

CREATE SEQUENCE IF NOT EXISTS public.autores_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY autores.aut_id;

CREATE SEQUENCE IF NOT EXISTS public.categorias_livros_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY categorias_livros.ctl_id;

CREATE SEQUENCE IF NOT EXISTS public.editoras_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY editoras.edi_id;

CREATE SEQUENCE IF NOT EXISTS public.livros_base_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY livros_base.lvb_id;

CREATE SEQUENCE IF NOT EXISTS public.publicacoes_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY publicacoes.pub_id;

CREATE SEQUENCE IF NOT EXISTS public.produtos_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY produtos.prd_id;

CREATE SEQUENCE IF NOT EXISTS public.pagamentos_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY pagamentos.pag_id;

CREATE SEQUENCE IF NOT EXISTS public.controle_estoque_produtos_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY controle_estoque_produtos.esp_id;

CREATE SEQUENCE IF NOT EXISTS public.itens_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY itens.its_id;

CREATE SEQUENCE IF NOT EXISTS public.carrinhos_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY carrinhos.car_id;

CREATE SEQUENCE IF NOT EXISTS public.pedidos_compras_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY pedidos_compras.pdc_id;

CREATE SEQUENCE IF NOT EXISTS public.itens_pedido_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY itens_pedido.itp_id;

CREATE SEQUENCE IF NOT EXISTS public.pedidos_trocas_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY pedidos_trocas.pdt_id;

CREATE SEQUENCE IF NOT EXISTS public.status_pedidos_seq
    INCREMENT 50
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1
    OWNED BY status_pedidos.stp_id;