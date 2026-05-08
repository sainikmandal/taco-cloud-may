create table if not exists Ingredient (
    id   varchar(4)  not null primary key,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists Taco (
    id         identity,
    name       varchar(50) not null,
    created_at timestamp   not null
);

create table if not exists Taco_Ingredients (
    taco_id       bigint not null,
    ingredient_id varchar(4) not null
);

alter table Taco_Ingredients
    add foreign key (taco_id) references Taco(id);

alter table Taco_Ingredients
    add foreign key (ingredient_id) references Ingredient(id);

create table if not exists Taco_Order (
    id                identity,
    delivery_name     varchar(100) not null,
    delivery_street   varchar(255) not null,
    delivery_city     varchar(100) not null,
    delivery_state    varchar(100) not null,
    delivery_zip      varchar(10)  not null,
    cc_number         varchar(16)  not null,
    cc_expiration     varchar(5)   not null,
    cc_cvv            varchar(3)   not null,
    placed_at         timestamp    not null
);

create table if not exists Taco_Order_Tacos (
    taco_order_id bigint not null,
    taco_id       bigint not null
);

alter table Taco_Order_Tacos
    add foreign key (taco_order_id) references Taco_Order(id);

alter table Taco_Order_Tacos
    add foreign key (taco_id) references Taco(id);
