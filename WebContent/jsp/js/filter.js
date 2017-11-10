function filtro(texto, id, celula) {
	var valor = texto.value.toLowerCase();
	var table = document.getElementById(id);
	var res;
	
	for (var i = 1; i < table.rows.length; i++) {
		res = table.rows[i].cells[celula].innerHTML.replace(/<[^>]+>/g, "");
		
		if (res.toLowerCase().indexOf(valor) >= 0) {
			table.rows[i].style.display = '';
		} else {
			table.rows[i].style.display = 'none';
		}
	}
}