<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="guardarProductoOrden" method="post">
    
    <fieldset>
        <legend>Producto orden</legend>
        ID Producto:<br>
        <select name="categoria">
            <c:forEach var="i" items="${listadoCategoria}">
                <option value="${i.catId}" name="producto_producto_orden">${i.catNombre}</option>
            </c:forEach>           
        </select><br>
        
        ID Orden:<br>
        <select name="categoria">
            <c:forEach var="i" items="${listadoCategoria}">
                <option value="${i.catId}" name="orden_producto_orden">${i.catNombre}</option>
            </c:forEach>           
        </select><br>
        
        Cantidad:<br>
        <input type="number" name="cantidad_producto_orden" value=""><br><br>
        
        Subtotal:<br>
        <input type="number" name="subtotal_producto_orden" value=""><br><br>
                
        <input type="submit" value="Cancelar">
        <input type="submit" value="Crear">
              
    </fieldset>
    
    
</form>
