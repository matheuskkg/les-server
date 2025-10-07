insert into publicacoes (pub_id, pub_lvb_id, pub_isbn, pub_codigo_barras, pub_numero_paginas, pub_altura, pub_largura,
                         pub_peso, pub_profundidade, pub_edicao, pub_edi_id)
values (nextval('publicacoes_seq'),
        (select lvb_id from livros_base where lvb_titulo = 'A Metamorfose'),
        '8594318782',
        '8594318782',
        96,
        22.8,
        15.6,
        0.180,
        1.2,
        'Integral',
        (select edi_id from editoras where edi_nome = 'Principis'));

insert into publicacoes (pub_id, pub_lvb_id, pub_isbn, pub_codigo_barras, pub_numero_paginas, pub_altura, pub_largura,
                         pub_peso, pub_profundidade, pub_edicao, pub_edi_id)
values (nextval('publicacoes_seq'),
        (select lvb_id from livros_base where lvb_titulo = 'Tudo é rio'),
        '6555871784',
        '6555871784',
        210,
        23,
        15.5,
        0.612,
        1.1,
        '10ª',
        (select edi_id from editoras where edi_nome = 'Record'));

insert into publicacoes (pub_id, pub_lvb_id, pub_isbn, pub_codigo_barras, pub_numero_paginas, pub_altura, pub_largura,
                         pub_peso, pub_profundidade, pub_edicao, pub_edi_id)
values (nextval('publicacoes_seq'),
        (select lvb_id from livros_base where lvb_titulo = 'Perigoso!'),
        '8538058975',
        '8538058975',
        32,
        27.6,
        23.8,
        0.180,
        0.4,
        '1ª',
        (select edi_id from editoras where edi_nome = 'Ciranda Cultural'));

insert into publicacoes (pub_id, pub_lvb_id, pub_isbn, pub_codigo_barras, pub_numero_paginas, pub_altura, pub_largura,
                         pub_peso, pub_profundidade, pub_edicao, pub_edi_id)
values (nextval('publicacoes_seq'),
        (select lvb_id from livros_base where lvb_titulo = 'Ainda estou aqui'),
        '8579624169',
        '8579624169',
        296,
        23.2,
        15.0,
        0.500,
        1.8,
        '1ª',
        (select edi_id from editoras where edi_nome = ' Alfaguara'));

insert into publicacoes (pub_id, pub_lvb_id, pub_isbn, pub_codigo_barras, pub_numero_paginas, pub_altura, pub_largura,
                         pub_peso, pub_profundidade, pub_edicao, pub_edi_id)
values (nextval('publicacoes_seq'),
        (select lvb_id from livros_base where lvb_titulo = 'A hora da estrela'),
        '6555320354',
        '6555320354',
        88,
        21,
        14,
        0.800,
        2,
        '1ª',
        (select edi_id from editoras where edi_nome = 'Rocco'));