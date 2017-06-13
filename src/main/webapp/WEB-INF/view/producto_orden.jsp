<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <p>Productos</p>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID Producto</th>
                <th>ID Orden</th>
                <th>Top cantidad </th>
                <th>Top subtotal</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoProductoOrden}">
                <tr>
                    <td>${i.prod.prodId}</td>
                    <td>${i.ord.ordId}</td>
                    <td>${i.topCantidad}</td>
                    <td>${i.topSubtotal}</td>

                    
                    <td>
                        <a href="editarProductoOrden?id=${i.ordId}">Editar</a>
                        <a href="eliminarProductoOrden?id=${i.ordId}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        
    </table>
</div>
