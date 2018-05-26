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

        var renderer = Viva.Graph.View.renderer(graph, {
            graphics: graphics
        });


        graphics.node(function (node) {
            var ui = Viva.Graph.svg('g');
            var svgText = Viva.Graph.svg('text').attr('y', '-4px').attr('x',
                    '-' + (nodeSize) + 'px').text(node.data);
            var img = Viva.Graph.svg('rect')
                    .attr('width', nodeSize / 2)
                    .attr('height', nodeSize / 2)
                    .attr('fill', 'red');

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

    // var element = document.getElementById("biography"); // находим на странице элемент с id = biography
    // element.innerHTML = res; // вставка туда строки res


}




