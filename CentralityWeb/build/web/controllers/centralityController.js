class centralityController {

   
    // Получает данные с сервиса getJSONRanksService
    getData(x) {
        var service = new getJSONRanksService();
        return service.getJSONRanks(x);
    }

    // Привести вершины к начальному состоянию
    refreshVertices(size) {
        $('rect').attr('width', size / 2)
                .attr('height', size / 2)
                .attr('fill', '#2f75a8') // стандартный цвет - красный
                .attr('stroke', "None"); // без обводки
    }

    // Отобразить ранги текстом
    getRanksToString(algr) {        
        var data = JSON.parse(this.getData(algr));
        var res = "", x;
        for (let i = 0; i < data.length; i++) {
            x = data[i];
            res += "<p> Ранг: " + x[0] + " ID: " + x[1] + " Значение: " + x[2] + "</p>";
        }
        var element = document.getElementById("nameOfAlg");        
        element.innerHTML = this.nameAlg(algr);        
        element = document.getElementById("ranks");
        element.innerHTML = res;
    }

    // Отоюражение вершин по рангам на графе
    calculateCentrality(alg, n) {
        var maxNodes = $("g").length;
        var show;
        if (n > maxNodes) {
            show = maxNodes;
        } else {
            show = n;
        }


        var nodeSize = 24; // стандартный размер вершин при создании
        this.refreshVertices(nodeSize);

        var data = JSON.parse(this.getData(alg)); // получает ранги с json строки     

        var x;
        for (let i = 0; i < show; i++)
        {
            x = data[i]; //255 / show * (i + 1)
            $('#' + x[1]).attr('fill', this.rgbTohex(96, 255 / show * (i + 1), 210))
                    .attr('width', this.clamp(parseInt(nodeSize, 10) + 4 - i, nodeSize))
                    .attr('height', this.clamp(parseInt(nodeSize, 10) + 4 - i, nodeSize))
                    .attr('opacity', 0.93)
                    .attr('stroke', "black")
                    .attr('stroke-width', '0.3%');
        }
        
    }

    // Цвет RGB в HEX
    rgbTohex(red, green, blue) {
        var rgb = blue | (green << 8) | (red << 16);
        return '#' + (0x1000000 + rgb).toString(16).slice(1);
    }
    // ограничения числа
    clamp(val, min) {
        var x = val < min ? min : val;
        return x;
    }

    nameAlg(alg) {
        switch (alg) {
            case 1:
                return "Betweenness Centrality";
                break;
            case 2:
                return "Closeness Centrality";
                break;
            case 3:
                return "Degree Centrality";
                break;
            case 4:
                return "Eigenvector Centrality";
                break;
            case 5:
                return "PageRank";
                break;
            case 6:
                return "HITS";
                
            default:
                return "null";
                break;
        }       
    }

}