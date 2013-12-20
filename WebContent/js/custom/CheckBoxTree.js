define(
	["dijit/Tree",
	 "dojo/_base/window",
	 "dojo/_base/array",
	 "dojo/_base/declare"],
	function(Tree, window, array, declare){
		var _CheckBoxTreeNode = declare([Tree._TreeNode],
		{
		    _checkbox: null,
		    _createCheckbox: function() {
		        this._checkbox = window.doc.createElement('input');
		        this._checkbox.type = 'checkbox';
		        this._checkbox.checked = false;
		        this._checkbox.className = "dijitTreeCheckBox";
		        dojo.place(this._checkbox, this.expandoNode, 'after');
		    },
		    postCreate: function() {
		        this._createCheckbox();
		        this.inherited( arguments );
		    },
		    onNodeChecked: function(/*Tree*/ tree, /*true for checked, false for unchecked*/checked) {
		    	if(this._checkbox){
		    		this._checkbox.checked = checked;
		    	}
		    	tree.model.getChildren(
	        		this.item,
					function(items){
	        			array.forEach(items, function(item){
	        				nodes = tree.getNodesByItem(item);
	        				nodes[0].onNodeChecked(tree, checked);
	        			});
	        		},
					function(err){
						console.error(_this, ": error loading " + this.label + " children: ", err);
					}
        		);
		    }
		});
		return declare([Tree],
		{
		    _onClick: function(/*TreeNode*/ nodeWidget, /*Event*/ e) {
		        var domElement = e.target;
		        if(domElement && domElement.nodeName != 'INPUT') {
		            return this.inherited( arguments );
		        }
		        if(!nodeWidget || !nodeWidget.isTreeNode){
		            return;
		        }
		        //nodeWidget._checkbox.checked ^ true;
		        var tree = this;
		        nodeWidget.onNodeChecked(tree, nodeWidget._checkbox.checked);
		    },
		    _createTreeNode: function( args ) {
		        return new _CheckBoxTreeNode(args);
		    }
		});

	}
)