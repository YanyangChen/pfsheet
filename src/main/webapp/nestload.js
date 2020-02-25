
function Validation(row, col, comment, color) {
    this.row = row;
    this.col = col;
    this.value = '';
    this.comment = comment;
    this.color = color;
    this.vldtfunc = function vldtfunc(){};
}

function ValWithWidget(validations, wname) {
    this.validations = validations;
    this.wname = wname;
    this.table=''
    
}

function secondRowRenderer(instance, td, row, col, prop, value, cellProperties) {
    Handsontable.renderers.TextRenderer.apply(this, arguments);
    td.style.fontWeight = 'bold';
    td.style.color = 'green';
    td.style.background = '#CEC';
    td.style.verticalAlign = 'middle';
    td.style.textAlign = 'center';
    td.style.unselectable = 'on';
    
  }

function setCommentContentByRowCol(row, col, value, vldtlines){
    /* if(value == '999'){
    	
    	if (col == 5) {return "comment in column 5"}
    	if (col == 6) {return "comment in column 6"}
    }
    else { */
    	for (vldt of vldtlines) {
        	// can only specify a row or a column or a cell
    		  if( (row == vldt.row && vldt.col == '')
        	   || (col == vldt.col && vldt.row == '')
        	   || (row == vldt.row && col == vldt.col))
           		  {
           		    //assign value to validation object and do validations
           		    vldt.value = value;
           		   bolvldt = vldt.vldtfunc();
         		    if (vldt.vldtfunc()){
							return vldt.comment;
             		    }
         		   else {return ""}
         		   //vldt.value='';
           		  }
    		  
    		}
        
        //return ""
        
    /*  }  */
}
function validationColorRenderer(instance, td, row, col, prop, value, cellProperties) {
	Handsontable.renderers.TextRenderer.apply(this, arguments);
	/* if (value == 999) {
		
        td.style.backgroundColor = 'red'; 

    	        

     } 
	else
		{td.style.backgroundColor = '';} */
	for (vldt of vldtlines) {
    	// can only specify a row or a column or a cell
		  if( (row == vldt.row && vldt.col == '')
    	   || (col == vldt.col && vldt.row == '')
    	   || (row == vldt.row && col == vldt.col))
       		  {
       		    //assign value to validation object and do validations
       		    vldt.value = value;
       		   bolvldt = vldt.vldtfunc();
     		    if (vldt.vldtfunc()){
     		    	td.style.backgroundColor = vldt.color;
         		    }
     		   else {return td.style.backgroundColor = '';}
     		   //vldt.value='';
       		  }
		  
		}
	
	}

function addHook2AfterCHangeValition(tb_instance, validations_array, nestedrows, valwid){
	Handsontable.hooks.add(
     		"afterChange", function (changes, source) {
    				if (source == 'edit' || source == "CopyPaste.paste") { 
    		        	if (changes != null){
//    		        		console.log("table widget name " + valwid.wname);
	       		     		for(x=0; x<changes.length; x++) {
		       		    		var responseComments = setCommentContentByRowCol(changes[x][0], changes[x][1], changes[x][3], validations_array);
		       		    	
		       		    		 
		       		    		
		       		    		
		       		    		//the follow method will force rendering whole table after it assign value to every comment
		       		    		 this.getCellMeta(changes[x][0], changes[x][1]).comment = {'value':responseComments};
		       		    		 
		       		    		 //the follow method will force rendering whole table everytime it assign value to comment
		       		    		 /* this.getPlugin('comments').setCommentAtCell(changes[x][0], changes[x][1], comment);  */
		       		    	}
    		      		}
		      			
     		       } 
		       
    		         }, tb_instance

		         );
	
	tb_instance.updateSettings({
			cells: function (row, col) {

				

				
				/////////////////////////////////
				/* console.log("in cell function") */
			      var cellProperties = {};
			      var data = this.instance.getData();
			      
			      vldtlines = validations_array;
			      if (row < nestedrows || data[row] && data[row][col] === 'readOnly') {
			        cellProperties.readOnly = true; // make cell read-only if it is first row or the text reads 'readOnly'
			        
			      }
			      if (row < nestedrows) {
			          cellProperties.renderer = secondRowRenderer; // uses function directly
			       
			         
			      }
			      
			      else {
			    	    var cellProperties = {};
			        cellProperties.renderer = validationColorRenderer; // uses lookup map
			        return cellProperties;
			      }
			  
			      return cellProperties;
			    }
	})
	
}

function updateSetting2AfterSelectionBorder(tb_instance, nestedrows){
	
	tb_instance.updateSettings({


		   
	        afterSelection: 
	        	function (row, col, row2, col2, preventScrolling, selectionLayerLevel) {
	        		
//	        		console.log(widgetname);
//	        		console.log("row, col, row2, col2 : " + row +" "+ col+" "+  row2+" "+  col2);
	        		
				
	            	
	        	preventScrolling.value = false;
	    			var borders = document.querySelectorAll('.handsontable-inner .wtBorder.current'); 
	    			if (typeof  borders[0].style !== 'undefined') {  
	    			 if (row < nestedrows ){
	    				 
	    				  
	    			  for(y=0; y<document.querySelectorAll('.handsontable-inner').length; y++)
	    				  {
	    		      for(x=0; x<5 * (y+1); x++){
	    		    	   
			    	  //hide selection border
	        		  borders[x].style.backgroundColor="";
	    		      borders[x].style.display="none"; 
	    		      //hide corner square
	    		      borders[4 + 5*y].style.borderWidth="0px";
	   		    	  borders[4 + 5*y].style.borderRightStyle="";
	  		    	
	    		       }
	    	}}
	    			
	    		        if (row >= nestedrows  ){
//	    		        	 console.log("greater than 2") 
	    		        	 for(y=0; y<document.querySelectorAll('.handsontable-inner').length; y++)
	    				  {
	    		        	 for(x=0; x<5 * (y+1); x++){
	    		        		//show selection border
	        		        	  borders[x].style.backgroundColor="rgb(75, 137, 255)";
	    		        		 
	        		        	  }
	    		        	//show hided corner square
	    		           borders[4 + 5*y].style.borderWidth="1px" 
	    		           borders[4 + 5*y].style.borderRightWidth="1px" 
	    		   		  
	    		          }  
	    		        }
	        		 }      
	        	}


	    	 
		
	     		}); 
	
}

function nestload(valwids) {
	

		   

$(document).ready(
         function() {
      for(valwid of valwids){
//        var wname= Object.keys(PrimeFaces.widgets); 
//    	var $hot = PF(wname).ht; 
//    	vldtlines = valwid.validations;
    	wname = valwid.wname;
    	//valwid.table= PF(wname).ht; 
//    	console.log(valwid.table);
//    	console.log(valwid.wname);
    	var nestedrows = PF(wname).cfg.nestedrows;

    	


        
 

 function negativeValueRenderer(instance, td, row, col, prop, value, cellProperties) {
    		Handsontable.renderers.TextRenderer.apply(this, arguments);

    		
    		}


      
      	
    	

    	Handsontable.renderers.registerRenderer('negativeValueRenderer', negativeValueRenderer);
    	
    	
    	  

//    	 console.log("add hook")
    	 addHook2AfterCHangeValition(PF(valwid.wname).ht, valwid.validations, PF(valwid.wname).cfg.nestedrows, valwid);
    	 updateSetting2AfterSelectionBorder(PF(valwid.wname).ht, PF(valwid.wname).cfg.nestedrows);
//    	 console.log("clearing variables of " + valwid.wname);
//    	 valwid='';
      }
      valwids=[];   
    	});
/*valwids=[];*/
}