insert into produtos (prd_id, prd_pub_id, prd_preco)
values (nextval('produtos_seq'),
        (select pub_id from publicacoes where pub_isbn = '8594318782'),
        1559);

insert into produtos (prd_id, prd_pub_id, prd_preco)
values (nextval('produtos_seq'),
        (select pub_id from publicacoes where pub_isbn = '6555871784'),
        4335);

insert into produtos (prd_id, prd_pub_id, prd_preco)
values (nextval('produtos_seq'),
        (select pub_id from publicacoes where pub_isbn = '8538058975'),
        1416);

insert into produtos (prd_id, prd_pub_id, prd_preco)
values (nextval('produtos_seq'),
        (select pub_id from publicacoes where pub_isbn = '8579624169'),
        3310);

insert into produtos (prd_id, prd_pub_id, prd_preco)
values (nextval('produtos_seq'),
        (select pub_id from publicacoes where pub_isbn = '6555320354'),
        2650);
