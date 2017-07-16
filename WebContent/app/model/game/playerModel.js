/*global define */
define(["jquery", "app/model/game/inventoryModel"], 
function($, Inventory) {
	'use strict';

	return function() {
	    this.terrain = null;
	    this.main = {
	    		droite : null,
	    		gauche : null,
	    		graine : null,
	    		current : null
	    };
	    this.inventaire = new Inventory();
	    
	    this.refresh = function(data) {
	    	this.terrain = data.terrain;
	    };
	    
	    this.addToInventory = function(itemName) {
	    	var item = ItemsData.get(itemName);
	    	if (item) this.inventory.put(item);
	    };
	    /**
	     * Choisit l'item et le place dans une main
	     */
	    this.pickItem = function(itemName) {
	    	var item = this.inventory.get(itemName);
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
	    		this.main.current.use(this.terrain, x, y);
	    	}
	    };
	};
});