select pro.identificador,pro.tipo_documento,td.descripcion,pro.nombre,pro.telefono,pro.correo,pro.direccion 
from proveedores pro , tipo_documento td 
where pro.tipo_documento = td.codigo and upper(pro.nombre) like '%S%';

select * from producto;

/*select p.codigo_producto, p.nombre as nombre_producto, udm.codigo_udm 
as nombre_udm, udm.descripcion as descripcion_udm, cast(p.precio_venta as decimal(6,2)), 
p.tiene_iva, cast(p.coste as decimal(6,2)), p.categoria_fk as codigo_categoria, cat.nombre as nombre_categoria,
p.stock
from producto p, unidades_medida udm, categorias cat 
where p.udm = udm.codigo_udm
and p.categoria_fk = cat.codigo_cat
and upper(p.nombre) like "%A%";*/
select * from historial_stock;