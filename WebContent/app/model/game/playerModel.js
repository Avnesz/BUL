/*global define */
define(["jquery", 
        "app/model/game/inventoryModel",
        "app/model/game/terrainModel"], 
function($, Inventory, Terrain) {
	'use strict';

	return function() {
	    this.terrain = new Terrain();
	    
	    this.main = {
	    		droite : null,
	    		gauche : null,
	    		graine : null,
	    		current : null
	    };
	    this.inventory = new Inventory();
	    
	    this.addToInventory = function(itemId) {
	    	this.inventory.put(itemId);
	    };
	    /**
	     * Choisit l'item et le place dans une main
	     */
	    this.pickItem = function(itemId) {
	    	var item = this.inventory.get(itemId);
	    	// Si c'est une graine, on le place dans la poche a graine
	    	if (item.isGraine) {
	    		this.main.graine = item;
	    	}else {
	    		//Si l'objet courant est dans la main gauche ou droite on le remplace
	    		if (this.main.current == this.main.gauche) {
	    			this.main.gauche = item;
	    			this.main.current = this.main.gauche;
	    		}else if (this.main.current == this.main.droite) {
	    			this.main.droite = item;
	    			this.main.current = this.main.droite;
	    		}else {
	    			// Si la main gauche est vide on place l'objet ici sinon dans la main droite
	    			if (!this.main.gauche) {
		    			this.main.gauche = item;
		    		}else {
		    			this.main.droite = item;
		    		}
	    		}
	    	}
	    };
	    this.chooseCurrent = function(main) {
	    	this.main.current = this.main[main];
	    	$(".interface-outils ul .outil").each(function(element) {
	    		$(element).removeClass("choose");
	    	});
	    	$(".interface-outils ul .outil#"+main).addClass("choose");
	    };
	    this.useCurrentTool = function(x, y) {
	        if (this.main.current) {
	            this.main.current.use(this, x, y);
	    	}
	    };
	};
});