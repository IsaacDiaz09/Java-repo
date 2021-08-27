/* Reto 4 Isaac Diaz G15 */
use db_reto4;
/* Consulta 1*/ 
SELECT nombre,inventario FROM productos WHERE precio >= 9000 ORDER BY nombre;
/* Consulta 2*/ 
SELECT avg(precio) as promedio FROM productos;
/* Consulta 3*/ 
SELECT nombre,precio FROM productos WHERE nombre like 'A%' or nombre like 'P%' ORDER BY nombre;
/* Consulta 4*/ 
SELECT count(nombre) as total FROM productos WHERE (precio BETWEEN 3000 and 10000);
/* Consulta 5*/ 
SELECT sum(precio*inventario) as total_inventario FROM productos;