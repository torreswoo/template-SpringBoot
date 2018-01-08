$(document).ready(function() {

  // config JMX chart factory
  var
    keepHistorySec = 180,
    pollInterval = 1000,
    columnsCount = 3;

  var factory = new JmxChartsFactory(keepHistorySec, pollInterval, columnsCount);

  // Memory
  // - http://localhost:8080/jolokia/read/java.lang:type=Memory
  factory.create([
    {
      name : 'java.lang:type=Memory',
      attribute : 'HeapMemoryUsage',
      path : 'committed'
    }, {
      name : 'java.lang:type=Memory',
      attribute : 'HeapMemoryUsage',
      path : 'used'
    }, {
      name : 'java.lang:type=Memory',
      attribute : 'HeapMemoryUsage',
      path : 'init'
    }
  ]);

  // OperatingSystem
  // - http://localhost:8080/jolokia/read/java.lang:type=OperatingSystem
  factory.create([
    {
      name : 'java.lang:type=OperatingSystem',
      attribute : 'SystemLoadAverage'
    }
  ]);

  // Threading
  // - http://localhost:8080/jolokia/read/java.lang:type=Threading
  factory.create([
    {
      name : 'java.lang:type=Threading',
      attribute : 'PeakThreadCount'
    }, {
      name : 'java.lang:type=Threading',
      attribute : 'ThreadCount'
    }, {
      name : 'java.lang:type=Threading',
      attribute : 'DaemonThreadCount'
    }
  ]);

  // factory.create([{
  //   name: 'Tomcat:name="http-nio-8080",type=ThreadPool',
  //   attribute: 'currentThreadsBusy'
  //   }, {
  //   name: 'Tomcat:name=executor,type=Executor',
  //   attribute: 'queueSize'
  // }]);
  // factory.create([
  // {
  // name:
  // 'com.blogspot.nurkiewicz.download.tokenbucket:name=perRequestTokenBucket,type=PerRequestTokenBucket',
  // attribute: 'OngoingRequests'
  // },
  // {
  // name:
  // 'com.blogspot.nurkiewicz.download:name=downloadServletHandler,type=DownloadServletHandler',
  // attribute: 'AwaitingChunks'
  // }
  // ]);
});

/* JmxChartsFactory
 * set mbeans -> create()
 * set parameter
 *
 */
function JmxChartsFactory(keepHistorySec, pollInterval, columnsCount) {

  /*
   * set init parameter
   *  - columnsCount : the number of the chart in the one row (default:3)
   *  - keepHistorySec : how long keep the chart data (dafault:600s)
   *  - pollInterval : poll & update interval (dafault:1000ms)
   */
  columnsCount = columnsCount || 3;
  pollInterval = pollInterval || 1000;
  var keepPoints = (keepHistorySec || 600) / (pollInterval / 1000);

  /*
   * set mbeans
   * - mbeans -> series, monitoredMbeans
   */
  var jolokia = new Jolokia("/jolokia");
  var series = [];
  var monitoredMbeans = [];
  var chartsCount = 0;
  this.create = function(mbeans) {
    mbeans = $.makeArray(mbeans);
    series = series.concat(createChart(mbeans).series);
    monitoredMbeans = monitoredMbeans.concat(mbeans);
  };


  /*
   * set Portlets Container
   */
  setupPortletsContainer(columnsCount);

  /*
   * set interval - poll & update chart
   */
  setInterval(function() {
    pollAndUpdateCharts();
  }, pollInterval);


  /* ======================================================================
   * methods
   * ====================================================================== */
  function pollAndUpdateCharts() {
    var requests = prepareBatchRequest();
    var responses = jolokia.request(requests);
    // console.log(' -> requests : ', requests);
    // console.log(' -> response : ', responses);
    updateCharts(responses);
  }

  function createNewPortlet(name) {
    return $('#portlet-template')
      .clone(true)
      .appendTo($('.column')[chartsCount++ % columnsCount])
      .removeAttr('id')
      .find('.title')
      .text(
        (name.length > 50 ? '...' : '')
        + name.substring(name.length - 50, name.length))
      .end().find('.portlet-content')[0];
  }

  function setupPortletsContainer() {
    var column = $('.column');
    for (var i = 1; i < columnsCount; ++i) {
      column.clone().appendTo(column.parent());
    }
    $(".column").sortable({
      connectWith : ".column"
    });

    $(".portlet-header .ui-icon").click(
      function() {
        $(this).toggleClass("ui-icon-minusthick").toggleClass(
          "ui-icon-plusthick");
        $(this).parents(".portlet:first").find(".portlet-content")
          .toggle();
      });
    $(".column").disableSelection();
  }

  function prepareBatchRequest() {
    return $.map(monitoredMbeans, function(mbean) {
      return {
        type : "read",
        mbean : mbean.name,
        attribute : mbean.attribute,
        path : mbean.path
      };
    });
  }

  function updateCharts(responses) {
    var curChart = 0;
    $.each(responses,
      function() {
        var point = {  // data
          x : this.timestamp * 1000,
          y : parseFloat(this.value)
        };
        var curSeries = series[curChart++];
        curSeries.addPoint(point, true, curSeries.data.length >= keepPoints);
      });
  }

  function createChart(mbeans) {
    console.log(mbeans)
    return new Highcharts.Chart({
      chart : {
        renderTo : createNewPortlet(mbeans[0].name),
        animation : false,
        defaultSeriesType : 'areaspline',
        shadow : false
      },
      title : {
        text : null
      },
      xAxis : {
        type : 'datetime'
      },
      yAxis : {
        title : {
          text : mbeans[0].attribute
        }
      },
      legend : {
        enabled : true,
        borderWidth : 0
      },
      credits : {
        enabled : false
      },
      exporting : {
        enabled : false
      },
      plotOptions : {
        area : {
          marker : {
            enabled : false
          }
        }
      },
      series : $.map(mbeans, function(mbean) {
        return {
          data : [],  // data NULL
          name : mbean.path || mbean.attribute
        }
      })
    })
  }
}