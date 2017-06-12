<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <p>categorias</p>
    
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Teléfono</th>
                <th>Dirección</th>
                <th>Comuna</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="i" items="${listadoUsuario}">
                <tr>
                    <td>${i.cliId}</td>
                    <td>${i.cliNombre}</td>
                    <td>${i.cliApellido}</td>
                    <td>${i.cliTelefono}</td>
                    <td>${i.cliDireccion}</td>
                    <td>${i.cliComuna}</td>
                    <td>
                        <a href="editarUsuario?id=${i.cliId}">Editar</a>
                        <a href="eliminarUsuario?id=${i.cliId}">Eliminar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
        
    </table>
</div>
