/*global define */
define(["jquery", "app/utils/mapEntry"], 
function($, MapEntry) {
	'use strict';

	return function() {
	    this.map = [];
	    
	    this.get = function(name) {
	    	for (var i = 0; i < this.inventory.length; i++) {
	    		var itemFound = this.inventory[i];
	    		if (itemFound.key == itemSearched) return {index: i, item: itemFound.value};
	    	}
	    	return null;
	    };
	    
	    /**
	     * Si l'objet existe dans linventaire, en rajoute un
	     * Sinon, l'ajoute dans l'inventaire.
	     */
	    this.put = function(item) {
	    	if (item) {
		    	var itemFound = this.get(item).item;
		    	if (itemFound) {
		    		itemFound.add(1);
		    	}else {
		    		this.map.push(new MapEntry(item.name, item));
		    	}
	    	}
	    };

	    /**
	     * Retire un exemplaire de l'objet de l'inventaire
	     * Si il n'y en a plus, le supprime de l'inventaire.
	     */
	    this.remove = function(itemName) {
    		var entry = this.get(itemName);
    		if (entry.item) {
    			entry.item.remove(1);
    			if (entry.item.nbr <= 0) {
        			this.map.splice(entry.id, 1);
        		}
    		}
	    };
	};
});