class createVivaGraph {

    getData() {

        var service = new getJSONGraphService;
        return service.getJSONGraph();
    }

    createGraphFromJSON() {

        var data = this.getData();

        var graph = Viva.Graph.graph();
        var graphics = Viva.Graph.View.svgGraphics(),
                nodeSize = 24;

        // Вершины
        var x = data[0];
        var z = JSON.parse(x);

        $.each(z, function (index, value) {
            graph.addNode(value[0], value[1]);
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
            springCoeff: 0.0008,
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
            var svgText = Viva.Graph.svg('text').attr('y', '-4px').attr('x',
                    '-' + (nodeSize) + 'px').text(node.data);
            var img = Viva.Graph.svg('rect')
                    .attr('width', nodeSize / 2)
                    .attr('height', nodeSize / 2)
                    .attr('fill', 'red')
                    .attr('id', node.id);

            ui.append(img);
            ui.append(svgText);
            return ui;
        })
                .placeNode(function (nodeUI, pos) {
                    nodeUI.attr('transform', 'translate(' + (pos.x - nodeSize / 4)
                            + ',' + (pos.y - nodeSize / 4) + ')');
                });


        renderer.run();
        $('#1').attr('fill', 'blue');
    }

    //Создание контейнера для svg
    createContainer() {
        var container = document.createElement('div');
        container.className = 'graph-container';
        document.body.appendChild(container);
        return container;
    }

}




