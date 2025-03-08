var pageNumber = 0;

// efeito infinite scroll
$(window).scroll(function() {
	
	var scrollTop = $(this).scrollTop();
	var conteudo = $(document).height() - $(window).height();
	
	console.log('scrollTop: ', scrollTop, ' | ', 'conteudo', conteudo);
	
	if(scrollTop >= conteudo) {
		pageNumber++;
		setTimeout(function() {
			loadScrollBar(pageNumber);
		}, 200);
	}
	
});

function loadScrollBar(pageNumber) {
	
	$.ajax({
		method: "GET",
		url: "/promocao/list/ajax",
		data: {
			page: pageNumber
		},
		success: function(response) {
			console.log("response > ", response);
		}
	})
	
}