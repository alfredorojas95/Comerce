<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <p>categorias</p>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoCategoria}">
                <tr>
                    <td>${i.catId}</td>
                    <td>${i.catNombre}</td>
                    <td>
                        <a href="editarFormCategoria?id=${i.catId}">Editar</a>
                        <a href="eliminarCategoria?id=${i.catId}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        
    </table>
</div>
