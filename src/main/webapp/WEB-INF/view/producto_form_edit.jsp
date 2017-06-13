<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="actualizarFormProducto" method="post">
    
    <fieldset>
        <legend>Nuevo Producto</legend>
        <input type="hidden" name="prod_id" value="${tecProducto.prodId}">
        Nombre:<br>
        <input type="text" name="nombre_producto" value="${tecProducto.proNombre}"><br><br>
        
        <select name="categoria">
            <c:forEach var="i" items="${listadoCategoria}">
                <option value="${i.catId}" name="categoria_producto">${i.catNombre}</option>
            </c:forEach>
            
        </select><br>
        
        Descripción<br>
        <input type="text" name="descripcion_producto" value="${tecProducto.proDescripcion}"><br><br>
        
        Precio:<br>
        <input type="number" name="precio_producto" value="${tecProducto.prPrecio}"><br><br>
        
        Fecha última modificación<br>
        <input type="date" name="fecha_mod_producto" value="${tecProducto.proUltimaUctualizacion}"><br><br>
        
        <input type="submit" value="Cancelar">
        <input type="submit" value="Crear">
              
    </fieldset>
    
    
</form>
