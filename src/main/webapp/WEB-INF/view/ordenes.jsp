<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <p>Productos</p>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Categoria</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Fecha modificación</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoOrdenes}">
                <tr>
                    <td>${i.ordId}</td>
                    <td>${i.ordFcreacion}</td>
                    <td>${i.cat.ordFumConfirmacion}</td>
                    <td>${i.ordFumConfirmacion}</td>
                    <td>${i.ordPrecioTotal}</td>
                    <td>${i.cli.cliNombre}</td>
                    
                    <td>
                        <a href="editarFormOrden?id=${i.ordId}">Editar</a>
                        <a href="eliminarFormOrden?id=${i.ordId}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        
    </table>
</div>
