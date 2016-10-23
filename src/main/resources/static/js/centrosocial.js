/* simulador javascript
*   http://jsfiddle.net/fVpkm/441/
*/

/* API JQuery
 * http://api.jquery.com/
*/

$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigo = button.data('codigo');
	var descricao = button.data('descricao');
	
	// alert('Variaveis: ' + codigo + ' -> ' + descricao);
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + codigo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o registro <strong>' + descricao + '</strong>?');
});


$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});

	// muda a cor dos botoes collapse no cadastro Pessoa
	$('.js-collapse').on('click', function(event) {
		event.preventDefault();
		var botao = $(event.currentTarget);
		
		if(botao.hasClass('btn-info')){

			$(this).removeClass('btn-info').addClass('btn-success');
		  
		} else {

			$(this).removeClass('btn-success').addClass('btn-info');

		}
	});
	
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botao = $(event.currentTarget);
		var urlStatus = botao.attr('href');
		
		var response = $.ajax({
			url: urlStatus,
			type: 'PUT'
		});
		
		response.done(function(e) {
			var codigo = botao.data('codigo');
			$('[data-role=' + codigo + ']').html('<span class="label label-success">' + e + '</span>');
			botao.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('Erro ao atualizar status');
		});
		
	});
});
