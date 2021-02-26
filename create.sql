create table inventory_item (inventory_item_id integer not null auto_increment, amount integer not null, product_product_id integer, primary key (inventory_item_id)) engine=InnoDB
create table product (product_id integer not null auto_increment, description varchar(255), name varchar(255), primary key (product_id)) engine=InnoDB
create table user (user_id integer not null auto_increment, password varchar(255), username varchar(255), primary key (user_id)) engine=InnoDB
alter table inventory_item add constraint FK1vea7e9dxae889gd8i0u6q1a1 foreign key (product_product_id) references product (product_id)
