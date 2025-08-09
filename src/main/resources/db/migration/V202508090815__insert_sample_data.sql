insert into locations (id, code, name, address, city, country, zip_code, geolocation)
values
(1, 'UBX1', 'Ubicación 1', 'Dirección 1', 'Ciudad 1', 'EEUU', '00000', '35.49432, -93.86948'),
(2, 'UBX2', 'Ubicación 2', 'Dirección 2', 'Ciudad 2', 'EEUU', '00000', '46.892651, -118.595826'),
(3, 'UBX3', 'Ubicación 3', 'Dirección 3', 'Ciudad 3', 'EEUU', '00000', '40.754779, -78.651790'),
(4, 'UBX4', 'Ubicación 4', 'Dirección 4', 'Ciudad 4', 'EEUU', '00000', '28.999902, -82.070894'),
(5, 'UBX5', 'Ubicación 5', 'Dirección 5', 'Ciudad 5', 'EEUU', '00000', '34.031395, -116.196725');

insert into states (id, name)
values
(1, 'Recibido'),
(2, 'En preparación'),
(3, 'Asignado a transportista'),
(4, 'En tránsito'),
(5, 'En espera por entrega'),
(6, 'Entregado');
