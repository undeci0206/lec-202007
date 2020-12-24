/**
 * 
 */
$.fn.getLprodAndBuyer=function(param){
	let prod_buyerTag = param.prod_buyerTag;
	let prod_lgu = param.prod_lgu;
	let prod_buyer = param.prod_buyer;
	
	let prod_lguTag = this;
	this.on("change", function(){
		let buyer_lgu = this.value;
		if(buyer_lgu){
			prod_buyerTag.find("option:gt(0)").hide();
			prod_buyerTag.find("option."+buyer_lgu).show();
		}else{
			prod_buyerTag.find("option").show();
		}
		prod_buyerTag.val("");
		
	});
	$.ajax({
		url: $.getContextPath() + "/prod/getOthers.do",
		dataType:"json",
		success:function(resp){
			let buyerOpts = [];
			let lprodOpts = [];
			$(resp.buyerList).each(function(idx, buyer){
				buyerOpts.push(
					$("<option>").text(buyer.buyer_name)
								.attr("value", buyer.buyer_id)
								.addClass(buyer.buyer_lgu)
								.prop("selected", prod_buyer==buyer.buyer_id)
				);
			});
			$(resp.lprodList).each(function(idx, lprod){
				lprodOpts.push(
					$("<option>").text(lprod.lprod_nm)
								.attr("value", lprod.lprod_gu)
								.prop("selected", prod_lgu==lprod.lprod_gu)
				);
			});
			prod_lguTag.append(lprodOpts);
			prod_buyerTag.append(buyerOpts);
		}
	});
	return this;
}