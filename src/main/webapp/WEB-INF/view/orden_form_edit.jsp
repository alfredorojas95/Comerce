<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="guardarOrden" method="post">
    
    <fieldset>
        <legend>Nueva Orden</legend>
        Fecha Creación:<br>
        <input type="date" name="fecha_creacion_orden" value="${orden.ordFcreacion}"><br><br>
        
        Cliente:<br>
        <select name="cliente">
            <c:forEach var="i" items="${listadoUsuario}">
                <option value="${orden.cli.cliNombre}" name="cliente_orden">${i.cliNombre}</option>
            </c:forEach>
            
        </select><br>
        
        Num confirmacion:<br>
        <input type="number" name="num_confirmacion_orden" value="${orden.ordFumConfirmacion}"><br><br>
        
        Precio total:<br>
        <input type="number" name="precio_orden" value="${orden.ordPrecioTotal}"><br><br>
        
        <input type="submit" value="Cancelar">
        <input type="submit" value="Crear">
              
    </fieldset>
    
    
</form>
