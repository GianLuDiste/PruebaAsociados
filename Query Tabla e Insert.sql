CREATE TABLE asociados(
					dni varchar(8) primary key not null,
					nombre varchar(30) not null,
					apellido varchar(30) not null,
					calle varchar(30) not null,
					numero int not null,
					telefono varchar(15) not null,
					ciudad varchar(30) not null);


insert into asociados(dni, nombre, apellido, calle, numero, telefono, ciudad, solicitudes) 
values ('47256279', 'Gian Luca', 'Distefano', 'Valencia', 6150, '2235376208', 'Mar del Plata'),
		('20401272', 'Ines', 'Pepi', 'Valencia', 6150, '2236872329', 'Mechongue'),
		('21861171', 'Cristian', 'Distefano', 'Valencia', 6150, '2236867883', 'Balcarce');
		
drop table asociados;

delete from asociados where dni = '16858124';
show tables like 'asociados';
delete from asociados;
use prueba_asociados;
select * from asociados;
