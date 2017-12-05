$('#cpf').mask('000.000.000-00');

function validaSenha(input){
	var senha = document.getElementById('senha').value;
	var confirmarSenha = input.value;
	var checaLetraNumero = new RegExp(/^(?=.*[a-zA-Z])(?=.*\d)[A-Za-z\d][A-Za-z\d!@#$%^&*()_+]{5,9}$/);

	if (confirmarSenha != senha)
		input.setCustomValidity('Senhas não conferem!');
	else if (confirmarSenha.length < 6 || confirmarSenha.length > 10)
		input.setCustomValidity('A senha deverá possuir no mínimo 6 caracteres e máximo 10.');
	else if (checaLetraNumero.test(confirmarSenha) == false || checaLetraNumero.test(senha) == false)
		input.setCustomValidity('Coloque ao menos uma letra e um número!');
	else
		input.setCustomValidity('');
}