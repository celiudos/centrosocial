var Iaff = Iaff || {};

Iaff.MascaraDocumento = (function() {
	
	function MascaraDocumento() {
		this.selectTipoDocumento = $('.js-select-tipo-documento');
		this.labelNumeroDocumento = $('[for=numeroDocumento]');
		this.inputNumeroDocumento = $('#numeroDocumento');
	}
	
	MascaraDocumento.prototype.iniciar = function() {
		this.selectTipoDocumento.on('change', onTipoDocumentoAlterado.bind(this));
		var tipoDocumentoSelecionado = $('.js-select-tipo-documento :selected');
		if (tipoDocumentoSelecionado) {
			aplicarMascara.call(this, $(tipoDocumentoSelecionado));
		}
	}
	
	function onTipoDocumentoAlterado(evento) {
		var tipoDocumentoSelecionado = $('.js-select-tipo-documento :selected');
		aplicarMascara.call(this, tipoDocumentoSelecionado);
		this.inputNumeroDocumento.val('');
	}
	
	function aplicarMascara(tipoDocumentoSelecionado) {
		this.labelNumeroDocumento.text(tipoDocumentoSelecionado.data('documento'));
		this.inputNumeroDocumento.mask(tipoDocumentoSelecionado.data('mascara'));
		this.inputNumeroDocumento.removeAttr('disabled');
	}
	
	return MascaraDocumento;
	
}());

$(function() {
	var mascaraDocumento = new Iaff.MascaraDocumento();
	mascaraDocumento.iniciar();
});