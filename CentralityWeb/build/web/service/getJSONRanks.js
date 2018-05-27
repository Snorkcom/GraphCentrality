class getJSONGraphService {

    
    getJSONGraph() {

        var request = new XMLHttpRequest();
        request.open('GET', '/CentralityWeb/getJsonGraph', false); 
        request.send(); 

        
        if (request.status != 200)
        {
            alert(request.status + ': ' + request.statusText);
            return null;
        } else
        {
            var response = request.responseText.split("|");
  
            return response; 
        }
    }
}



