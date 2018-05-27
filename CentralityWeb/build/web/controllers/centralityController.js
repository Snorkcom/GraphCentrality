class centralityController{
    
    getData(x) {
        var service = new getJSONRanksService();
         return service.getJSONRanks(x);        
    }
    
    
    calculateCentrality(number){
        
        $('rect').attr('width', 24 / 2)
                    .attr('height', 24 / 2)
                    .attr('fill', 'red')
                    .attr('stroke', "None");
                    
        
         var data = JSON.parse(this.getData(number));         
         console.log(data[1]);
         
         var x;
         for(let i = 0; i<3;i++)
         {
             x =data[i]; 
             alert(x[1]);
             $('#'+x[1]).attr('fill', 'blue')
                        .attr('width', 24)
                        .attr('height', 24)
                        .attr('stroke', "yellowgreen")
                        .attr('stroke-width', '0.3%');                        
         }
         
        
        
        
        var algorithm = number;
        
        
        
    }
    
}