/*global define */
define(["jquery", 
        "app/model/game/inventoryModel",
        "app/model/game/terrainModel",
        "app/model/game/mainsModel"], 
function($, Inventory, Terrain, Mains) {
	'use strict';

	return function(token) {
	    this.token = null;
	    this.terrain = new Terrain();
	    this.inventory = new Inventory();
	    this.mains = new Mains();
	    
	    this.init = function(token) {
	        this.token = token;
	    };
	    
	    this.addToInventory = function(itemId, nbr) {
	    	this.inventory.put(itemId, nbr);
	    };
	    /**
	     * Choisit l'item et le place dans une main
	     */
	    this.pickItem = function(itemId) {
	    	this.mains.pickItem(itemId, this.inventory);
	    };
	    this.setCursor = function(main) {
	        this.mains.setCursor(main);
	    };
	    this.useCurrentTool = function(x, y) {
	        this.inventory.use(this.mains.getCurrent(), x, y, this);
	        this.mains.refresh(this.inventory);
	    };
	    
	    this.init(token);
	};
});