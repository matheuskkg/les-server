insert into controle_estoque_produtos (esp_id, esp_prd_id, esp_quantidade)
values (nextval('controle_estoque_produtos_seq'),
        (select pub_id from produtos p join publicacoes pb on pb.pub_id = p.prd_pub_id where pb.pub_isbn = '8594318782'),
        10);

insert into controle_estoque_produtos (esp_id, esp_prd_id, esp_quantidade)
values (nextval('controle_estoque_produtos_seq'),
        (select pub_id from produtos p join publicacoes pb on pb.pub_id = p.prd_pub_id where pb.pub_isbn = '6555871784'),
        2);

insert into controle_estoque_produtos (esp_id, esp_prd_id, esp_quantidade)
values (nextval('controle_estoque_produtos_seq'),
        (select pub_id from produtos p join publicacoes pb on pb.pub_id = p.prd_pub_id where pb.pub_isbn = '8538058975'),
        6);

insert into controle_estoque_produtos (esp_id, esp_prd_id, esp_quantidade)
values (nextval('controle_estoque_produtos_seq'),
        (select pub_id from produtos p join publicacoes pb on pb.pub_id = p.prd_pub_id where pb.pub_isbn = '8579624169'),
        0);

insert into controle_estoque_produtos (esp_id, esp_prd_id, esp_quantidade)
values (nextval('controle_estoque_produtos_seq'),
        (select pub_id from produtos p join publicacoes pb on pb.pub_id = p.prd_pub_id where pb.pub_isbn = '6555320354'),
        2);