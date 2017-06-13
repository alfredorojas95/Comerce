<form action="actualizarUsuario" method="post">
    
    <fieldset>
        <legend>Editar Usuario</legend>
        <input type="hidden" name="id_cat" value="${user.cliId}">
        Nombre:<br>
        <input type="text" name="nombre_usuario" value="${user.cliNombre}"><br><br>
        
        Apellido<br>
        <input type="text" name="apellido_usuario" value="${user.cliApellido}"><br><br>
        
        Teléfono<br>
        <input type="text" name="telefono_usuario" value="${user.cliTelefono}"><br><br>
        
        Dirección<br>
        <input type="text" name="direccion_usuario" value="${user.cliDireccion}"><br><br>
        
        Comuna<br>
        <input type="text" name="comuna_usuario" value="${user.cliComuna}"><br><br>
        
        <input type="submit" value="Cancelar">
        <input type="submit" value="Editar usuario">
              
    </fieldset>
    
    
</form>