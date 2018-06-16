class getJSONRanksService {

    
    getJSONRanks(x) {

        var request = new XMLHttpRequest();
        request.open('GET', '/CentralityWeb/getRanks?id='+x, false); 
        request.send(); 

        
        if (request.status != 200)
        {
            alert(request.status + ': ' + request.statusText);
            return null;
        } else
        {
            var response = request.responseText;
  
            return response; 
        }
    }
}



