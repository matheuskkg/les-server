insert into livros_base (lvb_id, lvb_titulo, lvb_sinopse, lvb_ano)
values (nextval('livros_base_seq'),
        'A Metamorfose',
        'O caixeiro-viajante Gregor acorda metamorfoseado em um enorme inseto e percebe que tudo mudou e não só em sua vida, mas no mundo. Ele, então, acompanha as reações de sua família ao perceberem o estranho ser em que ele se tornou. E, enquanto luta para se manter vivo, reflete sobre o comportamento de seus pais, de sua irmã e sobre a sua nova vida.',
        1915);
insert into autores_livros (aul_lvb_id, aul_aut_id)
values (currval('livros_base_seq'), (select aut_id from autores where aut_nome = 'Franz Kafka'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'), (select ctl_id from categorias_livros where ctl_nome = 'Clássico'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'), (select ctl_id from categorias_livros where ctl_nome = 'Ficção'));

insert into livros_base (lvb_id, lvb_titulo, lvb_sinopse, lvb_ano)
values (nextval('livros_base_seq'),
        'Tudo é rio',
        'Com uma narrativa madura, precisa e ao mesmo tempo delicada e poética, o romance narra a história do casal Dalva e Venâncio, que tem a vida transformada após uma perda trágica, resultado do ciúme doentio do marido, e de Lucy, a prostituta mais depravada e cobiçada da cidade, que entra no caminho deles, formando um triângulo amoroso. Na orelha do livro, Martha Medeiros escreve: “Tudo é rio é uma obra-prima, e não há exagero no que afirmo. É daqueles livros que, ao ser terminado, dá vontade de começar de novo, no mesmo instante, desta vez para se demorar em cada linha, saborear cada frase, deixar-se abraçar pela poesia da prosa. Na primeira leitura, essa entrega mais lenta é quase impossível, pois a correnteza dos acontecimentos nos leva até a última página sem nos dar chance para respirar. É preciso manter-se à tona ou a gente se afoga.” A metáfora do rio se revela por meio da narrativa que flui – ora intensa, ora mais branda – de forma ininterrupta, mas também por meio do suor, da saliva, do sangue, das lágrimas, do sêmen, e Carla faz isso sem ser apelativa, sem sentimentalismo barato, com a habilidade que só os melhores escritores possuem.',
        2014);
insert into autores_livros (aul_lvb_id, aul_aut_id)
values (currval('livros_base_seq'), (select aut_id from autores where aut_nome = 'Carla Madeira'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'), (select ctl_id from categorias_livros where ctl_nome = 'Ficção'));

insert into livros_base (lvb_id, lvb_titulo, lvb_sinopse, lvb_ano)
values (nextval('livros_base_seq'),
        'Perigoso!',
        'Bob é uma toupeira que adora etiquetar as coisas. Um dia, ele encontra uma coisa muito estranha. Uma coisa escamosa. Uma coisa escamosa com dentes pontudos. Ahhh! Cuidado, Bob!',
        2014);
insert into autores_livros (aul_lvb_id, aul_aut_id)
values (currval('livros_base_seq'), (select aut_id from autores where aut_nome = 'Tim Warnes'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'), (select ctl_id from categorias_livros where ctl_nome = 'Infantil'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'), (select ctl_id from categorias_livros where ctl_nome = 'Emoções e Sentimentos'));

insert into livros_base (lvb_id, lvb_titulo, lvb_sinopse, lvb_ano)
values (nextval('livros_base_seq'),
        'Ainda estou aqui',
        'Eunice Paiva é uma mulher de muitas vidas. Casada com o deputado Rubens Paiva, esteve ao seu lado quando foi cassado e exilado, em 1964. Mãe de cinco filhos, passou a criá-los sozinha quando, em 1971, o marido foi preso por agentes da ditadura, a seguir torturado e morto. Em meio à dor, ela se reinventou. Voltou a estudar, tornou-se advogada, defensora dos direitos indígenas. Nunca chorou na frente das câmeras. Ao falar de Eunice, e de sua última luta, desta vez contra o Alzheimer, Marcelo Rubens Paiva fala também da memória, da infância e do filho. E mergulha num momento obscuro da história recente brasileira para contar ― e tentar entender ― o que de fato ocorreu com Rubens Paiva, seu pai, naquele janeiro de 1971.',
        2015);
insert into autores_livros (aul_lvb_id, aul_aut_id)
values (currval('livros_base_seq'), (select aut_id from autores where aut_nome = 'Marcelo Rubens Paiva'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'),
        (select ctl_id from categorias_livros where ctl_nome = 'Biografias e Histórias Reais'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'),
        (select ctl_id from categorias_livros where ctl_nome = 'Líderes e Pessoas Notáveis'));

insert into livros_base (lvb_id, lvb_titulo, lvb_sinopse, lvb_ano)
values (nextval('livros_base_seq'),
        'A hora da estrela',
        'Pouco antes de morrer, em 1977, Clarice Lispector decide se afastar da inflexão intimista que caracteriza sua escrita para desafiar a realidade. O resultado desse salto na extroversão é A hora da estrela, o livro mais surpreendente que escreveu. Se desde Perto do coração selvagem, seu romance de estreia, Clarice estava de corpo inteiro, todo o tempo, no centro de seus relatos, agora a cena é ocupada por personagens que em nada se parecem com ela. A nordestina Macabéa, a protagonista de A hora da estrela, é uma mulher miserável, que mal tem consciência de existir. Depois de perder seu único elo com o mundo, uma velha tia, ela viaja para o Rio, onde aluga um quarto, se emprega como datilógrafa e gasta suas horas ouvindo a Rádio Relógio. Apaixona-se, então, por Olímpico de Jesus, um metalúrgico nordestino, que logo a trai com uma colega de trabalho. Desesperada, Macabéa consulta uma cartomante, que lhe prevê um futuro luminoso, bem diferente do que a espera. Clarice cria até um falso autor para seu livro, o narrador Rodrigo S.M., mas nem assim consegue se esconder. O desejo de desaparecimento, que a morte real logo depois consolidaria, se frustra. Entre a realidade e o delírio, buscando social enquanto sua alma a engolfava, Clarice escreveu um livro singular. A hora da estrela é um romance sobre o desamparo a que, apesar do consolo da linguagem, todos estamos entregues.',
        1977);
insert into autores_livros (aul_lvb_id, aul_aut_id)
values (currval('livros_base_seq'), (select aut_id from autores where aut_nome = 'Clarice Lispector'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'), (select ctl_id from categorias_livros where ctl_nome = 'Clássico'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'), (select ctl_id from categorias_livros where ctl_nome = 'Ficção'));
insert into categorias_livros_base (ctb_lvb_id, ctb_ctl_id)
values (currval('livros_base_seq'), (select ctl_id from categorias_livros where ctl_nome = 'Romance'));