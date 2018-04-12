var Script = function () {

    //morris chart

    $(function () {
      // data stolen from http://howmanyleft.co.uk/vehicle/jaguar_'e'_type
      var tax_data = [
           {data: '9', "temperatura": 34},
           {data: '8', "temperatura": 35},
           {data: '7', "temperatura": 32},
           {data: '6', "temperatura": 32},
           {data: '5', "temperatura": 31},
           {data: '4', "temperatura": 31},
           {data: '3', "temperatura": 31},
           {data: '2', "temperatura": 32},
           {data: '1', "temperatura": 32}
      ];
      Morris.Line({
        element: 'hero-graph',
        data: [
              {device: '1 sec', temperatura: 13},
              {device: '2 sec', temperatura: 13},
              {device: '3 sec', temperatura: 15},
              {device: '4 sec', temperatura: 10},
              {device: '5 sec', temperatura: 6},
              {device: '6 sec', temperatura: 20}
          ],
        xkey: 'device',
        ykeys: ['temperatura'  ],
        labels: ['temperatura'],
        lineColors:['#4ECDC4','#ed5565']
      });

      Morris.Donut({
        element: 'hero-donut',
        data: [
          {label: 'Jam', value: 25 },
          {label: 'Frosted', value: 40 },
          {label: 'Custard', value: 25 },
          {label: 'Sugar', value: 10 }
        ],
          colors: ['#3498db', '#2980b9', '#34495e'],
        formatter: function (y) { return y + "%" }
      });

      Morris.Area({
        element: 'hero-area',
        data: [
          {period: '2010 Q1', iphone: 2666, ipad: null, itouch: 2647},
          {period: '2010 Q2', iphone: 2778, ipad: 2294, itouch: 2441},
          {period: '2010 Q3', iphone: 4912, ipad: 1969, itouch: 2501},
          {period: '2010 Q4', iphone: 3767, ipad: 3597, itouch: 5689},
          {period: '2011 Q1', iphone: 6810, ipad: 1914, itouch: 2293},
          {period: '2011 Q2', iphone: 5670, ipad: 4293, itouch: 1881},
          {period: '2011 Q3', iphone: 4820, ipad: 3795, itouch: 1588},
          {period: '2011 Q4', iphone: 15073, ipad: 5967, itouch: 5175},
          {period: '2012 Q1', iphone: 10687, ipad: 4460, itouch: 2028},
          {period: '2012 Q2', iphone: 8432, ipad: 5713, itouch: 1791}
        ],

          xkey: 'period',
          ykeys: ['iphone', 'ipad', 'itouch'],
          labels: ['iPhone', 'iPad', 'iPod Touch'],
          hideHover: 'auto',
          lineWidth: 1,
          pointSize: 5,
          lineColors: ['#4a8bc2', '#ff6c60', '#a9d86e'],
          fillOpacity: 0.5,
          smooth: true
      });

      Morris.Bar({
          
        element: 'hero-bar',
        data: [
          {device: '1 sec', geekbench: 13},
          {device: '2 sec', geekbench: 16},
          {device: '3 sec', geekbench: 21},
          {device: '4 sec', geekbench: 30},
          {device: '5 sec', geekbench: 10},
          {device: '6 sec', geekbench: 24}
        ],
        xkey: 'device',
        ykeys: ['geekbench'],
        labels: ['Geekbench'],
        barRatio: 0.4,
        xLabelAngle: 35,
        hideHover: 'auto',
        barColors: ['#ac92ec']
      });

      new Morris.Line({
        element: 'examplefirst',
        xkey: 'sec',
        ykeys: ['value'],
        labels: ['Value'],
        data: [
          {sec: '1', value: 20},
          {sec: '2', value: 10},
          {sec: '3', value: 5},
          {sec: '4', value: 5},
          {sec: '2012', value: 20}
        ]
      });

      $('.code-example').each(function (index, el) {
        eval($(el).text());
      });
    });


    // replace user id with the right one from login
    user = localStorage.getItem("userName");
    $(".content .userID").html(user);
    // replace user image with the right one from login
    img = localStorage.getItem("userImg");
    $(".content .userImage").html(img);
}();




