-- dele table if exists --
DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS vehicle_status;


-- CREATINE TABLA STATUS OF VEHICLE --
create table if not exists vehicle_status (
	id serial primary key,
	code varchar not null unique,
	name varchar not null unique,
	description varchar
);

-- insert iniciales vehicle_status--
INSERT INTO vehicle_status (code, name, description) VALUES
('AVA', 'AVAILABLE', 'Vehículo listo para alquiler'),
('NAV', 'NOT_AVAILABLE', 'Vehículo no disponible');

-- CREATINE TABLA VEHICLE --
create table if not exists vehicle(
    id serial primary key,
    brand varchar not null,
    model varchar not null,
    color varchar not null,
    plate varchar not null unique,
    daily_price numeric(10,2) not null,
    status_id int not null,
    active boolean not null default true,
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP,

    constraint fk_vehicle_status
        foreign key (status_id)
        references vehicle_status(id)
);


