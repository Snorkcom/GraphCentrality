class createVivaGraph {


    
    createGraphFromJSON() {
        
        var service = new getJSONGraphService;        
        var data = service.getJSONGraph().split("|"); 
   
                
          
          var vertices = data[0];
          console.log(vertices);
          console.log("___________________________________________________________");
          var edges = data[1];
          console.log(edges);
      
       // var element = document.getElementById("biography"); // находим на странице элемент с id = biography
       // element.innerHTML = res; // вставка туда строки res
    }

   

}




