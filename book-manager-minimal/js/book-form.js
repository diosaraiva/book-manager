const bookForm = (function($){
	const BOOK_ISBN = $("#book_isbn");
    const BOOK_TITLE = $("#book_title");
    const BOOK_PUBLICACAO = $("#book_publicacao");
	const BOOK_PRECO = $("#book_preco");
    const BOOK_SINOPSE = $("#book_sinopse");
	const BOOK_AUTOR = $("#book_autor");
	const BOOK_EDITORA = $("#book_editora");
	const BOOK_CRITICA = $("#book_critica");
    const BOOK_UPDATE_BUTTON = $("#updateButton");

    function clear() {
        setData();
        BOOK_TITLE.focus();
    }

    function hasErrors() {
        return BOOK_TITLE.val() === null || BOOK_TITLE.val() === '';
    }

    function getData() {
        return {
			isbn: BOOK_ISBN.val(),
            title: BOOK_TITLE.val(),
            publicacao: BOOK_PUBLICACAO.val(),
			preco: BOOK_PRECO.val(),
            sinopse: BOOK_SINOPSE.val(),
			autor: BOOK_AUTOR.val(),
			editora: BOOK_EDITORA.val(),
			critica: BOOK_CRITICA.val(),
        };
    }

    function setData(isbn='', title='', publicacao='', preco='', sinopse='', autor='', editora='', critica='') {
        BOOK_ISBN.val(isbn)
		BOOK_TITLE.val(title);
        BOOK_PUBLICACAO.val(publicacao);
		BOOK_PRECO.val(preco);
        BOOK_SINOPSE.val(sinopse);
		BOOK_AUTOR.val(autor);
		BOOK_EDITORA.val(editora);
		BOOK_CRITICA.val(critica);
    }

    function setSubmitButtonText(str) {
        BOOK_UPDATE_BUTTON.text(str);
    }

    function getSubmitButtonText() {
        return BOOK_UPDATE_BUTTON.text();
    }

    return {
        clear: clear,
        hasErrors: hasErrors,
        getData: getData,
        setData: setData,
        setSubmitButtonText: setSubmitButtonText,
        getSubmitButtonText: getSubmitButtonText,
    };
})(jQuery);