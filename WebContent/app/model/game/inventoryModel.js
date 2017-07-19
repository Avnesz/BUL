/*global define */
define(["jquery", "app/model/game/inventoryEntry", "app/data/items"], 
function($, InventoryEntry, ItemsData) {
	'use strict';

	return function() {
	    this.map = [];
	    
	    this.get = function(itemId) {
	    	return this.map[itemId];
	    };
	    
	    /**
	     * Si l'objet existe dans linventaire, en rajoute un
	     * Sinon, l'ajoute dans l'inventaire.
	     */
	    this.put = function(itemId) {
	        var item = ItemsData.get(itemId);
	    	if (item) {
		    	var itemFound = this.map[item.name];
		    	if (itemFound) {
		    		itemFound.add(1);
		    	}else {
		    	    this.map[itemId] = new InventoryEntry(item);
		    	}
	    	}
	    };

	    /**
	     * Retire un exemplaire de l'objet de l'inventaire
	     * Si il n'y en a plus, le supprime de l'inventaire.
	     */
	    this.remove = function(itemId) {
    		var entry = this.map[itemId];
    		if (entry) {
    			entry.remove(1);
    			if (entry.nbr <= 0) {
        			this.map.splice(this.map.indexOf(itemId), 1);
        		}
    		}
	    };
	};
});