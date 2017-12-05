$(document).ready(function(){
	$('#list-minhas-avaliacoes').DataTable({
		responsive: true,
		order: [
			[
				0, 'desc'
			]
		],
		columnDefs: [
		       { type: 'date-euro', targets: 0 }
		],
		language: {
			"emptyTable":     "Nenhum registro encontrado",
			"info":           "Mostrando de _START_ até _END_ de _TOTAL_ registros",
			"infoEmpty":      "Mostrando 0 até 0 de 0 registros",
			"infoFiltered":   "(Filtrados de _MAX_ registros)",
			"thousands":      ",",
			"lengthMenu":     "_MENU_ resultados por página",
			"loadingRecords": "Carregando...",
			"processing":     "Processando...",
			"search":         "Pesquisar:",
			"zeroRecords":    "Nenhum registro encontrado",
			"paginate": {
			    "first":      "Primeiro",
			    "last":       "Último",
			    "next":       "Próximo",
			    "previous":   "Anterior"
			},
			"aria": {
			    "sortAscending":  ": Ordenar colunas de forma ascendente",
			    "sortDescending": ": Ordenar colunas de forma descendente"
			}
		}
	});

	$('#list-minhas-avaliacoes tbody tr').on('click','td',function(e){
		if(!$(this).index() == 0)
			window.location = $(this).closest('tr').data('href');
	});
});