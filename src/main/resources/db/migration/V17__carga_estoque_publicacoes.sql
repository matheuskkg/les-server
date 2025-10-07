insert into estoque_publicacoes (esp_id, esp_pub_id, esp_quantidade)
values (nextval('estoque_publicacoes_seq'),
        (select pub_id from publicacoes where pub_isbn = '8594318782'),
        10);

insert into estoque_publicacoes (esp_id, esp_pub_id, esp_quantidade)
values (nextval('estoque_publicacoes_seq'),
        (select pub_id from publicacoes where pub_isbn = '6555871784'),
        2);

insert into estoque_publicacoes (esp_id, esp_pub_id, esp_quantidade)
values (nextval('estoque_publicacoes_seq'),
        (select pub_id from publicacoes where pub_isbn = '8538058975'),
        6);

insert into estoque_publicacoes (esp_id, esp_pub_id, esp_quantidade)
values (nextval('estoque_publicacoes_seq'),
        (select pub_id from publicacoes where pub_isbn = '8579624169'),
        0);

insert into estoque_publicacoes (esp_id, esp_pub_id, esp_quantidade)
values (nextval('estoque_publicacoes_seq'),
        (select pub_id from publicacoes where pub_isbn = '6555320354'),
        2);