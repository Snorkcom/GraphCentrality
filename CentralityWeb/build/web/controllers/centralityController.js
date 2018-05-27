class centralityController{
    
    getData(x) {
        var service = new getJSONRanksService();
         return service.getJSONRanks(x);        
    }
    
    
    calculateCentrality(number){
        
         var data = JSON.parse(this.getData(number));
         
         console.log(data[1]);
         
        $('#1').attr('fill', 'blue');
        
        
        var algorithm = number;
        
        
        
    }
    
}