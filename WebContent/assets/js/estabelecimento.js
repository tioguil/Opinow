$(document).ready(function(){
	var categoriaCadeirante = false, categoriaPiso = false, categoriaBraile = false, categoriaSanitario = false;
	var valor, descricao;

	$('.novo-estabelecimento a').click(function(){
		$('#nome_estabelecimento').attr('readonly', false).val('');
		$.each($('#result-list tbody').find('span'), function(i,r){
			$('#'+$(r).attr('data-field')+'_estabelecimento').attr('readonly', false).val('');
		});
		$('#value-search').val('');
		$('.estabelecimento-empty').hide();
		$('.novo-estabelecimento').fadeOut();
		$('#result-list-title').fadeOut();
		$('#result-list').fadeOut();
		$('#result').empty();
		$('.nova-avaliacao').fadeIn(600);
	});
	
	$('#value-search').on('input', function(){
		$('#result').empty();
		if($('#value-search').val() != ''){
			foursquareApi.search($('#value-search').val());
			$('.novo-estabelecimento').fadeIn(100);
			$('#result-list-title').fadeIn(100);
			$('#result-list').fadeIn(100);
		} else {
			$('.novo-estabelecimento').fadeOut();
			$('#result-list-title').fadeOut();
			$('#result-list').fadeOut();
			$('#result').empty();
		}
	});

	var foursquareApi = {
		getJson: function(url,callback){
			$.getJSON(url, function(data){
				callback(data);
			});
		},
		search: function(query){
			var location = '-23.533773,-46.625290'; //SP
			var limit = 5;

			var version = '20171101';
			var client_id = '2AU5MPIY2M5Q35XEERQNB41WYBDNHSMXRSFWZ0I1EMF4SKAA';
			var client_secret = 'RTHSUJITCJLUE1VRIJ531JKIAIAH4NAAFRXVCAWIZICNSBJR';

			var url = 'https://api.foursquare.com/v2/venues/search?ll='+location+'&query='+query+'&limit='+limit+'&v='+version+'&client_id='+client_id+'&client_secret='+client_secret;
			this.getJson(url, function(data){
				$.each(data.response.venues, function(i, value){
					var innterListItem = '<strong>Estabelecimento</strong>: <span data-field="estabelecimento" data-value="'+value.name+'">'+value.name+'</span><br>';
					if(value.categories[0].name !== undefined)
						innterListItem += '<strong>Categoria</strong>: <span data-field="categoria" data-value="'+value.categories[0].name+'">'+value.categories[0].name+'</span><br>';
					else
						innterListItem += '<strong>Categoria</strong>: <span data-field="categoria" data-value="none">Sem categoria</span><br>';
					if(value.location.address !== undefined)
						innterListItem += '<strong>Endereço</strong>: <span data-field="endereco" data-value="'+value.location.address+'">'+value.location.address+'</span><br>';
					else
						innterListItem += '<strong>Endereço</strong>: <span data-field="endereco" data-value="none">Não foi encontrado o endereço cadastrado</span><br>';
					if(value.location.city !== undefined)
						innterListItem += '<strong>Cidade</strong>: <span data-field="cidade" data-value="'+value.location.city+'">'+value.location.city+'</span><br>';
					else
						innterListItem += '<strong>Cidade</strong>: <span data-field="cidade" data-value="São Paulo">São Paulo</span><br>';
					
					$('#result-list').addClass('border-result-list');
					$('#result').append('<tr><td>'+innterListItem+'</td></tr>');
				});
			});
		}
	};

	$('.btn-clear-estabelecimento').click(function(){
		var confirmLimpar = confirm('Deseja realmente limpar todos os campos?');

		if(confirmLimpar){
			$('#value-search').val('');
			$('.novo-estabelecimento').hide();
			$('#result-list-title').hide();
			$('#result-list').hide();
			$('#result').empty();
			$('.nova-avaliacao').hide();
			$('.estabelecimento-empty').show();
			$('#comentario_estabelecimento').val('');
			$('.estrelas').remove();
			$('#categoriaAval').val('');
			categoriaCadeirante = false;
			categoriaPiso = false;
			categoriaBraile = false;
			categoriaSanitario = false;

			$('#nome_estabelecimento').attr('readonly', true).val('');
			$('#endereco_estabelecimento').attr('readonly', true).val('');
			$('#cidade_estabelecimento').attr('readonly', true).val('');
			$('#categoria_estabelecimento').attr('readonly', true).val('');
		}
	});

	$('#result-list tbody').on('click','tr',function(){
		$('input[name=nome_estabelecimento]').val('');
		$('input[name=endereco_estabelecimento]').val('');
		$('input[name=cidade_estabelecimento]').val('');
		$('input[name=categoria_estabelecimento]').val('');

		$.each($(this).find('span'), function(i,r){
			$('#nome_estabelecimento').attr('readonly', true);
			$('#'+$(r).attr('data-field')+'_estabelecimento').attr('readonly', true);
			if($(r).attr('data-value') != 'none'){
				switch($(r).attr('data-field')){
					case 'estabelecimento':
						$('input[name=nome_estabelecimento]').val($(r).attr('data-value'));
						break;
					case 'endereco':
						$('input[name=endereco_estabelecimento]').val($(r).attr('data-value'));
						break;
					case 'cidade':
						$('input[name=cidade_estabelecimento]').val($(r).attr('data-value'));
						break;
					case 'categoria':
						$('input[name=categoria_estabelecimento]').val($(r).attr('data-value'));
						break;	
				}
			} else {
				$('#'+$(r).attr('data-field')+'_estabelecimento').attr('readonly', false);
			}
		});
		
		$('.estabelecimento-empty').hide();
		$('.nova-avaliacao').fadeIn(600);
	});

	$('#categoriaAval').change(function(e){
		e.preventDefault();

		valor = $('#categoriaAval').val(); // Captura value
		descricao = $("#categoriaAval option:selected").text(); // Captura descricao

		if(valor != "Selecione a categoria de avalia&#231;&#227;o"){
			if(valor == "categoriaCadeirante" && !categoriaCadeirante){
				categoriaCadeirante = true;
				adicionar(1);
			}else if(valor == "categoriaPiso" && !categoriaPiso){
				categoriaPiso = true;
				adicionar(2);
			}else if(valor == "categoriaBraile" && !categoriaBraile){
				categoriaBraile = true;
				adicionar(3);
			}else if(valor == "categoriaSanitario" && !categoriaSanitario){
				categoriaSanitario = true;
				adicionar(4);
			}
		}
	});

	function adicionar(categoria){
		var categoriaSelecionada;

		if(categoria == 1)
			categoriaSelecionada = 'cat1';
		else if(categoria == 2)
			categoriaSelecionada = 'cat2';
		else if(categoria == 3)
			categoriaSelecionada = 'cat3';
		else
			categoriaSelecionada = 'cat4';

		$('#listas').append(
			'<div class="estrelas">\
				<label for="comment">Nota para ' + descricao +':</label>\
				<input type="radio" id="'+categoriaSelecionada+'-empty" name="'+valor+'" value="" checked="checked">\
				<label for="'+categoriaSelecionada+'-1"><i class="fa"></i></label>\
				<input type="radio" id="'+categoriaSelecionada+'-1" name="'+valor+'" value="1">\
				<label for="'+categoriaSelecionada+'-2"><i class="fa"></i></label>\
				<input type="radio" id="'+categoriaSelecionada+'-2" name="'+valor+'" value="2">\
				<label for="'+categoriaSelecionada+'-3"><i class="fa"></i></label>\
				<input type="radio" id="'+categoriaSelecionada+'-3" name="'+valor+'" value="3">\
				<label for="'+categoriaSelecionada+'-4"><i class="fa"></i></label>\
				<input type="radio" id="'+categoriaSelecionada+'-4" name="'+valor+'" value="4">\
				<label for="'+categoriaSelecionada+'-5"><i class="fa"></i></label>\
				<input type="radio" id="'+categoriaSelecionada+'-5" name="'+valor+'" value="5">\
				<i class="fa fa-remove" style="font-size:20px;color:red" id="removCategoria" data-categoria="'+categoriaSelecionada+'"></i>\
			</div>');
	}

	$('#listas').on("click","#removCategoria", function(e){
		e.preventDefault();

		if($(this).attr("data-categoria") == 'cat1'){
			$(this).parent('div').remove();
			categoriaCadeirante = false;
		}else if($(this).attr("data-categoria") == 'cat2'){
			$(this).parent('div').remove();
			categoriaPiso = false;
		}else if($(this).attr("data-categoria") == 'cat3'){
			$(this).parent('div').remove();
			categoriaBraile = false;
		}else if($(this).attr("data-categoria") == 'cat4'){
			$(this).parent('div').remove();
			categoriaSanitario = false;
		}
		if($('.estrelas').val() === undefined)
			$('#categoriaAval').val('');
	});

	$('form').submit(function(e){
		var notaCategoriaCadeirante = $('input[name=categoriaCadeirante]:checked', '.estrelas').val();
		var notaCategoriaPiso = $('input[name=categoriaPiso]:checked', '.estrelas').val();
		var notaCategoriaBraile = $('input[name=categoriaBraile]:checked', '.estrelas').val();
		var notaCategoriaSanitario = $('input[name=categoriaSanitario]:checked', '.estrelas').val();

		if (notaCategoriaCadeirante == "" || notaCategoriaPiso == "" || notaCategoriaBraile == "" || notaCategoriaSanitario == "") {
			alert('Escolha uma nota!');
			e.preventDefault();
		}
	});
});