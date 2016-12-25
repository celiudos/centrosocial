Brewer = Brewer || {};

Brewer.PesquisaRapidaPessoa = (function() {
	
	function PesquisaRapidaPessoa() {
		this.pesquisaRapidaPessoasModal = $('#pesquisaRapidaPessoas');
		this.nomeInput = $('#nomePessoaModal');
		this.pesquisaRapidaBtn = $('.js-pesquisa-rapida-pessoas-btn'); 
		this.containerTabelaPesquisa = $('#containerTabelaPesquisaRapidaPessoas');
		this.htmlTabelaPesquisa = $('#tabela-pesquisa-rapida-pessoa').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
		this.mensagemErro = $('.js-mensagem-erro');
	}
	
	PesquisaRapidaPessoa.prototype.iniciar = function() {
		this.pesquisaRapidaBtn.on('click', onPesquisaRapidaClicado.bind(this));
		this.pesquisaRapidaPessoasModal.on('shown.bs.modal', onModalShow.bind(this));

	}
	
	function onModalShow() {
		this.nomeInput.focus();
	}
	
	function onPesquisaRapidaClicado(event) {
		event.preventDefault();
		
		$.ajax({
			url: this.pesquisaRapidaPessoasModal.find('form').attr('action'),
			method: 'GET',
			contentType: 'application/json',
			data: {
				nome: this.nomeInput.val()
			}, 
			success: onPesquisaConcluida.bind(this),
			error: onErroPesquisa.bind(this)
		});
	}
	
	function onPesquisaConcluida(resultado) {
		this.mensagemErro.addClass('hidden');
		
		var html = this.template(resultado);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaPessoaPesquisaRapida = new Brewer.TabelaPessoaPesquisaRapida(this.pesquisaRapidaPessoasModal);
		tabelaPessoaPesquisaRapida.iniciar();
	} 
	
	function onErroPesquisa() {
		this.mensagemErro.removeClass('hidden');
	}
	
	return PesquisaRapidaPessoa;
	
}());

Brewer.TabelaPessoaPesquisaRapida = (function() {
	
	function TabelaPessoaPesquisaRapida(modal) {
		this.modalPessoa = modal;
		this.pessoa = $('.js-pessoa-pesquisa-rapida');
	}
	
	TabelaPessoaPesquisaRapida.prototype.iniciar = function() {
		this.pessoa.on('click', onPessoaSelecionado.bind(this));
	}
	
	function onPessoaSelecionado(evento) {
		this.modalPessoa.modal('hide');
		
		var pessoaSelecionada = $(evento.currentTarget);
		$('#nomePessoa').val(pessoaSelecionada.data('nome'));
		$('#codigoPessoa').val(pessoaSelecionada.data('codigo'));
	}
	
	return TabelaPessoaPesquisaRapida;
	
}());

$(function() {
	var pesquisaRapidaPessoa = new Brewer.PesquisaRapidaPessoa();
	pesquisaRapidaPessoa.iniciar();
});