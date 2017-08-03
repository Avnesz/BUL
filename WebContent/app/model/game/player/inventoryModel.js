/*global define */
define(["jquery", "app/data/items"], 
function($, ItemsData) {
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
		    		itemFound.nbr += nbr;
		    	}else {
		    	    this.map[itemId] = item;
		    	    this.map[itemId].nbr = nbr;
		    	}
	    	}
	    };

	    /**
	     * Retire un exemplaire de l'objet de l'inventaire
	     * Si il n'y en a plus, le supprime de l'inventaire.
	     */
	    this.remove = function(itemId) {
    		var item = this.get(itemId);
    		if (item) {
    			item.nbr --;
    			if (item.nbr <= 0) {
        			this.map.splice(this.map.indexOf(itemId), 1);
        		}
    			return item.nbr;
    		}
    		return 0;
	    };
	    
	    this.use = function(itemId, x, y, player) {
	        var item = this.get(itemId);
	        if (item) {
	            var used = item.use(player, x, y);
	            if (used && item.consommable) {
                    this.remove(itemId);
                }
            }
	    };
	};
});