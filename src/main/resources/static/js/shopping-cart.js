$(function() {
	console.log("in shopping cart");
    $("#placeOrder-btn").click(function(e) {
    	console.log("in shopping cart..");
        $.ajax({
            type: "POST",
            url: "/shoppingCart/placeOrder",
            contentType: "application/json; charset=utf-8",
            success: function() {
            }
        });
    });
    
    
	console.log("Add Product catalog");
    $(".add-to-cart-btn").click(function(e) {
        //e.preventDefault();
    	console.log("Clicked");
        var self = $(this);
        console.log( self.attr("productId"));
        $.ajax({
            url: "/shoppingCart/addProduct/",
            type: "POST",
            data: self.attr("productId"),
            contentType: "application/json; charset=utf-8",
            success: function() {
                //do nothing
            	console.log("Loading...");
            }
        });
    }); 
    
})