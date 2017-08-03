'use strict';
define(["jquery", "app/data/tools"], function($, ToolsData){
	var data = {
		"fleur" : {
			"id" : "fleur",
			"name" : "Fleur",
			"texte" : ["Une jolie fleur a replanter."],
			"use" : function(player, x, y) {
			    return putItem(player, x, y, "fleur");
			},
			"isGraine" : true,
			"consommable" : true
		}
	};
	
	var putItem = function(player, x, y, item) {
		var layers = player.terrain.get(x, y);
	    var putted = false;
		if (layers.sol.id === "herbe" && layers.layer1.id === "") {
	        player.terrain.modify("layer1", x, y, item);
	        putted = true;
	    }
	    return putted;
	};
	
	return {
		/**
		* Permet d'appeler un WS
		**/
		get : function(key, nbr) {
			var item = ToolsData.get(key);
			if (item.name) return item;
		    
		    return $.extend(true, {}, data[key]);
		}
	};
});