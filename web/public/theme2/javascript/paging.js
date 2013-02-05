function Pager(tableName, itemsPerPage) {
    this.tableName = tableName;
    this.itemsPerPage = itemsPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.inited = false;
    
    this.showRecords = function(from, to) {        
        var rows = document.getElementById(tableName).rows;
        for (var i = 0; i < rows.length; i++) {
            if (i < from || i > to)  
                rows[i].style.display = 'none';
            else
                rows[i].style.display = '';
        }
    }
    
    this.showPage = function(pageNumber) {
    	if (! this.inited) {
    		alert("not inited");
    		return;
    	}
    	


        var oldPageAnchor = document.getElementById('pg'+this.currentPage);
        oldPageAnchor.className = 'pg-normal';
        
        this.currentPage = pageNumber;
        var newPageAnchor = document.getElementById('pg'+this.currentPage);
        newPageAnchor.className = 'pg-selected';
        
        var from = (pageNumber - 1) * itemsPerPage ;
        var to = from + itemsPerPage - 1;
        this.showRecords(from, to);        
    	var pgPrev = document.getElementById('pgPrev');
    	var pgNext = document.getElementById('pgNext');
    	if (pageNumber == this.pages) 	 
    		 pgNext.style.display = 'none';
    	else  	
    		pgNext.style.display = 'inline-block';
    	if (pageNumber == 1) 
    		pgPrev.style.display = 'none';
    	else  
    		pgPrev.style.display = 'inline-block';
    }   
    
    this.prev = function() {
        if (this.currentPage > 1)
            this.showPage(this.currentPage - 1);
    }
    
    this.next = function() {    	
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        }
    }                        
    
    this.init = function() {
        var rows = document.getElementById(tableName).rows;        
        var records = (rows.length);
        this.pages = Math.ceil(records / itemsPerPage);        
        this.inited = true;
    }

    this.showPageNav = function(pagerName, positionId) {
    	if (! this.inited) {
    		alert("not inited");
    		return;
    	}
    	var element = document.getElementById(positionId);
    	var pagerHtml ="";
    	if (this.pages > 1)
        {
    	   var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal" id="pgPrev"> &#171 Prev </span> | ';
           for (var page = 1; page <= this.pages; page++) 
               pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> | ';
           pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal" id="pgNext"> Next &#187;</span>';            
        }
        element.innerHTML = pagerHtml;
        
        

    }
}

