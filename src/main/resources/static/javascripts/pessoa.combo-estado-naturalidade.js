var Brewer = Brewer || {};

Brewer.ComboEstadonascimento = (function() {
	
	function ComboEstadonascimento() {
		this.combo = $('#estadonascimento');
		this.emitter = $({});
		this.on = this.emitter.on.bind(this.emitter);
	}
	
	ComboEstadonascimento.prototype.iniciar = function() {
		this.combo.on('change', onEstadonascimentoAlterado.bind(this));
	}
	
	function onEstadonascimentoAlterado() {
		this.emitter.trigger('alterado', this.combo.val());
	}
	
	return ComboEstadonascimento;
	
}());

Brewer.ComboNaturalidade = (function() {
	
	function ComboNaturalidade(comboEstadonascimento) {
		this.comboEstadonascimento = comboEstadonascimento;
		this.combo = $('#naturalidade');
		this.imgLoading = $('.js-img-loading-nat');
		this.inputHiddenNaturalidadeSelecionada = $('#inputHiddenNaturalidadeSelecionada');
	}
	
	ComboNaturalidade.prototype.iniciar = function() {
		reset.call(this);
		this.comboEstadonascimento.on('alterado', onEstadonascimentoAlterado.bind(this));
		var codigoEstadonascimento = this.comboEstadonascimento.combo.val();
		inicializarNaturalidades.call(this, codigoEstadonascimento);
	}
	
	function onEstadonascimentoAlterado(evento, codigoEstadonascimento) {
		this.inputHiddenNaturalidadeSelecionada.val('');
		inicializarNaturalidades.call(this, codigoEstadonascimento);
	}
	
	function inicializarNaturalidades(codigoEstadonascimento) {
		if (codigoEstadonascimento) {
			var resposta = $.ajax({
				url: this.combo.data('url'),
				method: 'GET',
				contentType: 'application/json',
				data: { 'estado': codigoEstadonascimento }, 
				beforeSend: iniciarRequisicao.bind(this),
				complete: finalizarRequisicao.bind(this)
			});
			resposta.done(onBuscarNaturalidadesFinalizado.bind(this));
		} else {
			reset.call(this);
		}
	}
	
	function onBuscarNaturalidadesFinalizado(cidades) {
		var options = [];
		cidades.forEach(function(cidade) {
			options.push('<option value="' + cidade.codigo + '">' + cidade.nome + '</option>');
		});
		
		this.combo.html(options.join(''));
		this.combo.removeAttr('disabled');
		
		var codigoNaturalidadeSelecionada = this.inputHiddenNaturalidadeSelecionada.val();
		if (codigoNaturalidadeSelecionada) {
			this.combo.val(codigoNaturalidadeSelecionada);
		}
	}
	
	function reset() {
		this.combo.html('<option value="">Selecione a cidade</option>');
		this.combo.val('');
		this.combo.attr('disabled', 'disabled');
	}
	
	function iniciarRequisicao() {
		reset.call(this);
		this.imgLoading.show();
	}
	
	function finalizarRequisicao() {
		this.imgLoading.hide();
	}
	
	return ComboNaturalidade;
	
}());

$(function() {
	
	var comboEstadonascimento = new Brewer.ComboEstadonascimento();
	comboEstadonascimento.iniciar();
	
	var comboNaturalidade = new Brewer.ComboNaturalidade(comboEstadonascimento);
	comboNaturalidade.iniciar();
	
});

