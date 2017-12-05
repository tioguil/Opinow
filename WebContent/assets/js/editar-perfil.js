$('#alterar-senha').on('show.bs.collapse', function(){
	$('#senha-atual').attr('required', true);
	$('#senha').attr('required', true);
	$('#confirmar-senha').attr('required', true);
});

$('#alterar-senha').on('hide.bs.collapse', function(){
	$('#senha-atual').attr('required', false).val('');
	$('#senha').attr('required', false).val('');
	$('#confirmar-senha').attr('required', false).val('');
});