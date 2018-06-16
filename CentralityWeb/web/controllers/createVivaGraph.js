class createVivaGraph {

    getData() {

        var service = new getJSONGraphService;
        return service.getJSONGraph();
    }

    createGraphFromJSON() {

        var data = this.getData();

        var graph = Viva.Graph.graph();
        var graphics = Viva.Graph.View.svgGraphics();
        var nodeSize = 24; // Размер узла в пикселях
        
        // Функция выделения ребер при наведении
        var highlightRelatedNodes = function (nodeId, isOn) {
            // just enumerate all realted nodes and update link color:
            graph.forEachLinkedNode(nodeId, function (node, link) {
                var linkUI = graphics.getLinkUI(link.id);
                if (linkUI) {
                    // linkUI is a UI object created by graphics below
                    linkUI.attr('stroke', isOn ? 'red' : 'gray');
                }
            });
        };

        // Вершины
        var x = data[0];
        var z = JSON.parse(x);

        $.each(z, function (index, value) {
            graph.addNode(value[0], value[0]+" "+value[1]);
        });

        // Ребра
        x = data[1];
        z = JSON.parse(x);

        $.each(z, function (index, value) {
            graph.addLink(value[0], value[1]);
        });

        // Слой для графа
        var layout = Viva.Graph.Layout.forceDirected(graph, {
            springLength: 100,
            springCoeff: 0.001, //0.0008
            dragCoeff: 0.01,
            gravity: -1.2,
        });


        var renderer = Viva.Graph.View.renderer(graph, {
            layout: layout,
            graphics: graphics,
            container: this.createContainer()
        });


        graphics.node(function (node) {
            var ui = Viva.Graph.svg('g');
            var svgText = Viva.Graph.svg('text').attr('y', '-13px').attr('x',
                    '-' + (nodeSize) + 'px');//.text(node.data); - всегда отображать названия
            var img = Viva.Graph.svg('rect')
                    .attr('width', nodeSize / 2)
                    .attr('height', nodeSize / 2)
                    .attr('fill', '#2f75a8')
                    .attr('id', node.id);
            
           
            // Событие при наведении на узел
             var nodeColor;
            $(ui).hover(function () { // mouse over 
                nodeColor = img.getAttributeNS(null, 'fill');
                highlightRelatedNodes(node.id, true);
                svgText.text(node.data);
                img.attr('fill', "#04e300");  
            }, function () { // mouse out
                highlightRelatedNodes(node.id, false);
                svgText.text("");
                img.attr('fill', nodeColor);                
            });

            ui.append(img);
            ui.append(svgText);
            return ui;
        })
                .placeNode(function (nodeUI, pos) {
                    nodeUI.attr('transform', 'translate(' + (pos.x - nodeSize / 4)
                            + ',' + (pos.y - nodeSize / 4) + ')');
                });


        renderer.run();
    }

    //Создание контейнера для svg
    createContainer() {
        var div = document.getElementById('graph');
        var container = document.createElement('div');
        container.className = 'graph-container';
        div.appendChild(container);
        return container;
    }

}




