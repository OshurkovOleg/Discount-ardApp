insert into client  (
    id,
    card_balance,
    card_number
)
values (231, 600, 951753654);

insert into client_check  (
    id,
    number,
    price,
    client_id
)
values (32, 65, 234, 231);

insert into check_position(
    id,
    price,
    check_id
)
values (43, 145, 32);


insert into client  (
    id,
    card_balance,
    card_number
)
values (165, 777, 23499921);

insert into client_check  (
    id,
    number,
    price,
    client_id
)
values (54, 100, 403, 165);

insert into check_position(
    id,
    price,
    check_id
)
values (204, 43, 54);