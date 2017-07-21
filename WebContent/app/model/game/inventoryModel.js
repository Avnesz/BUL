/*global define */
define(["jquery", "app/model/game/inventoryEntry", "app/data/items"], 
function($, InventoryEntry, ItemsData) {
	'use strict';

	return function() {
	    this.map = [];
	    
	    /**
	     * Renvoi une entree d'inventaire
	     */
	    this.get = function(itemId) {
	    	return this.map[itemId];
	    };
	    
	    /**
	     * Si l'objet existe dans linventaire, en rajoute un
	     * Sinon, l'ajoute dans l'inventaire.
	     */
	    this.put = function(itemId, nbr) {
	        if (!nbr) nbr = 1;
	        
	        var item = ItemsData.get(itemId);
	    	if (item) {
		    	var itemFound = this.get(itemId);
		    	if (itemFound) {
		    		itemFound.add(nbr);
		    	}else {
		    	    this.map[itemId] = new InventoryEntry(item, nbr);
		    	}
	    	}
	    };

	    /**
	     * Retire un exemplaire de l'objet de l'inventaire
	     * Si il n'y en a plus, le supprime de l'inventaire.
	     */
	    this.remove = function(itemId) {
    		var entry = this.get(itemId);
    		console.log("remove 1 : ", entry);
    		if (entry) {
    			entry.remove(1);
    			if (entry.nbr <= 0) {
        			this.map.splice(this.map.indexOf(itemId), 1);
        		}
    			return entry.nbr;
    		}
    		return 0;
	    };
	    
	    this.use = function(itemId, x, y, player) {
	        var item = this.get(itemId).item;
	        if (item) {
	            item.use(player, x, y);
	            if (item.consommable) {
                    this.remove(itemId)
                }
            }
	    };
	};
});