-- dele table if exists --
drop table if exists rental;
drop table if exists customer;
drop table if exists document_type;
drop table if exists rental_status;


create table if not exists document_type(
	id SERIAL primary key,
	code VARCHAR not null UNIQUE,
	name VARCHAR not null UNIQUE,
	description VARCHAR not null
);


INSERT INTO document_type (code, name, description)
VALUES
('CC', 'CEDULA_CIUDADANIA', 'Documento nacional de identidad'),
('CE', 'CEDULA_EXTRANJERIA', 'Documento de identificación para extranjeros'),
('PP', 'PASSPORT', 'Pasaporte'),
('NIT', 'NIT', 'Número de identificación tributaria');

create table if not exists customer(
	id SERIAL primary key,
	name VARCHAR not null,
	last_name VARCHAR not null,
	email VARCHAR not null,
	phone VARCHAR not null,
	document_type_id INTEGER not null,
	document_number VARCHAR not null UNIQUE,
	created_at TIMESTAMP default CURRENT_TIMESTAMP,
	updated_at TIMESTAMP default CURRENT_TIMESTAMP,

	constraint fk_document_type
		foreign key (document_type_id)
		references document_type(id)
);


create table if not exists rental_status(
	id SERIAL primary key,
	code VARCHAR not null UNIQUE,
	name VARCHAR not null UNIQUE,
	description VARCHAR not null
);

INSERT INTO rental_status (code, name, description)
VALUES ('ACT', 'ACTIVE', 'Rental active'),
('CAN', 'CANCELLED', 'Rental cancelled'),
('COM', 'COMPLETED', 'Rental completed');


create table if not exists rental(
	id SERIAL primary key,
	customer_id INTEGER not null,
	vehicle_id VARCHAR not null,
	vehicle_model VARCHAR not null,
	vehicle_plate VARCHAR not null,
	rental_status_id INTEGER not null,
	start_date TIMESTAMP NOT NULL,
	end_date TIMESTAMP NOT NULL,
	daily_price NUMERIC(10,2) NOT null,
	total_price NUMERIC(10,2),
	created_at TIMESTAMP default CURRENT_TIMESTAMP,
	updated_at TIMESTAMP default CURRENT_TIMESTAMP,


	constraint fk_customer
		foreign key (customer_id)
		references customer(id),
	constraint fk_rental_status
		foreign key (rental_status_id)
		references rental_status(id)
);


INSERT INTO customer (
	name,
    last_name,
    email,
    phone,
    document_type_id,
    document_number
)
VALUES (
    'Juan',
    'Perez',
    'juan@test.com',
    '3001112233',
    1,
    '100200300'
);
