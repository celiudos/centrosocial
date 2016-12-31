var Iaff = Iaff || {};

Iaff.PessoaCadastroRapido = (function() {
	
	function PessoaCadastroRapido() {
		this.modal = $('#cadastroRapidoPessoa');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-pessoa-salvar-btn');
		this.form = this.modal.find('form');
		this.url = this.form.attr('action');
		this.inputNomePessoa = $('#nomePessoaCadastro');
		this.inputSexoPessoa = $('#sexoCadastro');
		this.inputDataNascimentoPessoa = $('#dataNascimentoCadastro');
		this.inputTelefonePessoa = $('#telefoneCadastro');
		this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-pessoa');
		
		this.containerTabelaPesquisa = $('#containerTabelaCadastroRapidoPessoa');
		this.htmlTabelaPesquisa = $('#tabela-cadastro-rapido-pessoa').html();
		this.template = Handlebars.compile(this.htmlTabelaPesquisa);
	}
	
	PessoaCadastroRapido.prototype.iniciar = function() {
		this.form.on('submit', function(event) { event.preventDefault() });
		this.modal.on('shown.bs.modal', onModalShow.bind(this));
		this.modal.on('hide.bs.modal', onModalClose.bind(this))
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
	}
	
	function onModalShow() {
		this.inputNomePessoa.focus();
	}
	
	function onModalClose() {
		this.inputNomePessoa.val('');
		this.inputSexoPessoa.val('M');
		this.inputDataNascimentoPessoa.val('');
		this.inputTelefonePessoa.val('');		

		this.containerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
		this.containerTabelaPesquisa.html("");
	}
	
	function onBotaoSalvarClick() {
		var nomePessoa = this.inputNomePessoa.val().trim();
		var sexoPessoa = this.inputSexoPessoa.val().trim();
		var dataNascimentoPessoa = this.inputDataNascimentoPessoa.val().trim();
		var telefonePessoa = this.inputTelefonePessoa.val().trim();
		
		$.ajax({
			url: this.url,
			method: 'POST',
			contentType: 'application/json',

			data: JSON.stringify({ 
				nome: nomePessoa,
				sexo: sexoPessoa,
				dataNascimento: dataNascimentoPessoa,
				nomePai: '',
				nomeMae: '',
				estadoCivil: 0,
				escolaridade: 0,
				profissao: '',
				tipoDocumento: 0,
				telefone: telefonePessoa,
				email: ''
				}),
			
			error: onErroSalvandoPessoa.bind(this),
			success: onPessoaSalva.bind(this)
		});
	}
	
	function onErroSalvandoPessoa(obj) {
		var mensagemErro = obj.responseText;
		this.containerMensagemErro.removeClass('hidden');
		this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onPessoaSalva(pessoa) {
		// this.modal.modal('hide');
		
		pessoa = [pessoa];
		
		this.containerMensagemErro.addClass('hidden');
		
		var html = this.template(pessoa);
		this.containerTabelaPesquisa.html(html);
		
		var tabelaPessoaCadastroRapido = new Iaff.TabelaPessoaCadastroRapido(this.modal);
		tabelaPessoaCadastroRapido.iniciar();
	}
	
	return PessoaCadastroRapido;
	
}());


Iaff.TabelaPessoaCadastroRapido = (function() {
	
	function TabelaPessoaCadastroRapido(modal) {
		this.modalPessoa = modal;
		this.pessoa = $('.js-pessoa-cadastro-rapido');
	}
	
	TabelaPessoaCadastroRapido.prototype.iniciar = function() {
		this.pessoa.on('click', onPessoaSelecionado.bind(this));
	}
	
	function onPessoaSelecionado(evento) {
		this.modalPessoa.modal('hide');
		
		var pessoaSelecionada = $(evento.currentTarget);
		$('#nomePessoa').val(pessoaSelecionada.data('nome'));
		$('#codigoPessoa').val(pessoaSelecionada.data('codigo'));
	}
	
	return TabelaPessoaCadastroRapido;
	
}());

$(function() {
	var pessoaCadastroRapido = new Iaff.PessoaCadastroRapido();
	pessoaCadastroRapido.iniciar();
});
