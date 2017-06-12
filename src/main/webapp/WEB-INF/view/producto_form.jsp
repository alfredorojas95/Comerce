<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="guardarProducto" method="post">
    
    <fieldset>
        <legend>Nuevo Producto</legend>
        Nombre:<br>
        <input type="text" name="nombre_producto" value=""><br><br>
        
        Categoria:<br>
        <select name="categoria">
            <c:forEach var="i" items="${listadoCategoria}">
                <option value="${i.catId}" name="categoria_producto">${i.catNombre}</option>
            </c:forEach>
            
        </select><br>
        
        Descripción<br>
        <input type="text" name="descripcion_producto" value=""><br><br>
        
        Precio:<br>
        <input type="number" name="precio_producto" value=""><br><br>
        
        Fecha última modificación<br>
        <input type="date" name="fecha_mod_producto" value=""><br><br>
        
        <input type="submit" value="Cancelar">
        <input type="submit" value="Crear">
              
    </fieldset>
    
    
</form>
