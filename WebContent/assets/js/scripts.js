$(document).ready(function(){
	$(function(){
		$('[data-toggle="tooltip"]').tooltip();
	});
	var listaEstabelecimento = new List('lista-estabelecimento', {
		valueNames: ['nome-estabelecimento', 'categoria-estabelecimento', 'endereco-estabelecimento'],
		page: 5,
		pagination: true,
		innerWindow: 1,
		left: 0,
		right: 0
	});
});