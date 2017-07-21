/*global define */
define(["jquery"], 
function($) {
    'use strict';

    return function() {
        this.main = {
                gauche : null,
                droite : null,
                graine : null
        };
        
        this.cursor = null;
        
        /**
         * Permet de selectionner un objet de l'inventaire
         */
        this.pickItem = function(itemId, inventory) {
            var itemEntry = inventory.get(itemId);
            var main = "gauche";
            
            if (itemEntry.item.isGraine) main = "graine";
            else {
                if (!this.main.gauche) main = "gauche";
                else if (!this.main.droite) main = "droite";
                else if (this.cursor) main = this.cursor;
            }
            
            this.setMain(main, itemId, itemEntry);
        };
        
        /**
         * Permet de mettre un objet dans une main
         */
        this.setMain = function(main, itemId, itemEntry) {
            this.main[main] = itemId;
            $(".interface-outils ul .outil#"+main+" .item").attr("class", "item "+itemId);
            if (itemEntry.nbr > 0) {
                $(".interface-outils ul .outil#"+main+" .nbr").html(itemEntry.nbr);
                $(".interface-outils ul .outil#"+main+" .nbr").show();
            } else {
                $(".interface-outils ul .outil#"+main+" .nbr").hide();
            }
        };
        
        /**
         * Change la position du curseur
         */
        this.setCursor = function(main) {
            $(".interface-outils ul .outil").each(function(index, element) {
                $(element).removeClass("choose");
            });
            if (main) {
                this.cursor = main;
                $(".interface-outils ul .outil#"+main).addClass("choose");
            }
        };

        /**
         * Raffraichie le nombre d'item en main
         */
        this.refresh = function(inventory) {
            var graine = inventory.get(this.main.graine);
            if (graine.nbr > 0) {
                this.setMain("graine", this.main.graine, graine);
            }else {
                this.setMain("graine", "", graine);
                if (this.cursor == "graine") this.setCursor(null);
            }
        };
        
        /**
         * Renvoit l'id de l'objet utilisé
         */
        this.getCurrent = function() {
            var main = this.cursor;
            return this.main[main];
        };
        
    };
});