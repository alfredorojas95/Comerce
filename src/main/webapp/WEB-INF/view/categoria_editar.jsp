<form action="actualizarCategoria" method="post">
    
    <fieldset>
        <legend>Editar Categoria</legend>
        <input type="hidden" name="id_cat" value="${tecCategoria.catId}"><br><br>
        Nombre:<br>
        <input type="text" name="nombre_categoria" value="${tecCategoria.catNombre}"><br><br>
        
        <input type="submit" value="Cancelar">
        <input type="submit" value="Guardar">
              
    </fieldset>
    
    
</form>