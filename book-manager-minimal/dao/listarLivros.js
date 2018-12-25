$(function(){

	var request = 

		$.ajax({
			url: "http://localhost:8080/livros",
			type: "json"
		});

	if ($.fn.jqGrid){
		jQuery("#jqGridLivros").jqGrid({
			data: request,
			datatype: "local",
			height: 250,
			rowNum: 10,
			rowList: [10,20,30],
			colNames:['ISBN','Titulo', 'Publicação', 'Preço','Em Reais (R$)'],
			colModel:[
				{name:'isbn',index:'isbn', width:60, sorttype:"float", search:false},
				{name:'titulo',index:'titulo', width:200, sorttype:"text", search:false},
				{name:'dataPublicacao',index:'dataPublicacao', sorttype:"date", formatter:"date", search:false},
				{name:'preco',index:'preco', width:60, align:"right",sorttype:"float", formatter:"number", search:false},
				{name:'valorPorExtenso',index:'valorPorExtenso', sorttype:"text", search:false},
				],
				pager: "#jqGridPagerLivros",
				viewrecords: true,
				caption: "Tabela de Livros",
				hidegrid:false,
				altRows: true
		});
		jQuery("#jqGridLivros")
		.jqGrid('setSelection', '3');
	}

})