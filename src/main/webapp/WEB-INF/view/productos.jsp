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
            <c:forEach var="i" items="${listadoProducto}">
                <tr>
                    <td>${i.prodId}</td>
                    <td>${i.proNombre}</td>
                    <td>${i.cat}</td>
                    <td>${i.proDescripcion}</td>
                    <td>${i.prPrecio}</td>
                    <td>${i.proUltimaUctualizacion}</td>
                    
                    <td>
                        <a href="editarFormProducto?id=${i.prodId}">Editar</a>
                        <a href="eliminarFormProducto?id=${i.prodId}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        
    </table>
</div>

