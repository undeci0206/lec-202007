/**
 * 
 */
	let prod_buyerTag = $("[name='prod_buyer']");
	let prod_lguTag = $("[name='prod_lgu']").getLprodAndBuyer({
		prod_buyerTag : prod_buyerTag
		, prod_lgu:"${prod.prod_lgu}"
		, prod_buyer:"${prod.prod_buyer}"
	});
	
	$("#prodForm").validate({
		onsubmit:true,
		onfocusout:function(element, event){
			return this.element(element);
		},
		errorPlacement: function(error, element) {
			error.appendTo( $(element).parents("td:first") );
	  	}
	});