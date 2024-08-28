const express = require('express');
const proxy = require('express-http-proxy');
const path = require('path');
const bodyParser = require('body-parser');
const requests = require('request');

const euisdkRoutes = require('./euisdk-routes');
const { request } = require('http');

module.exports = (app) => {
  /**
   * Webpack uses express 4.x. express.BodyParser() no longer exists. Use body-parser. Required for developing advanced mock Rest services.
   */
  app.use(bodyParser.json());

  app.get('/metrics', function(req, result){
    
    let data = '{"blank":""}'
    requests('http://localhost:8080/metrics/team/2', {}, (err, res, body) => {
      if (err) { result.send(err) ; return console.log(err);  }
      //console.log(body.url);
      //console.log(body.explanation);
      console.log(res.body);
      data = res.body;
      result.send(data);
    });
    //console.log(request);
    //console.log(data);
    
  });

  app.get('/freemembers', function(req, result){
    
    let data = '{"blank":""}'
    requests('http://localhost:8080/members?team=NOTEAM', {}, (err, res, body) => {
      if (err) { result.send(err) ; return console.log(err);  }
      //console.log(body.url);
      //console.log(body.explanation);
      console.log(res.body);
      data = res.body;
      result.send(data);
    });
    //console.log(request);
    //console.log(data);
    
  });


  /**
   * Initialize E-UI SDK routes
   */
  euisdkRoutes.init(app);

  // ------------------------------------------------------------
  // Custom Routes
  // ------------------------------------------------------------

  // ------------------------------------------------------------
  // Mocking server responses...
  // ------------------------------------------------------------

  /**
   * List all routes that should be proxied here.
   *
   * A route definition must be defined as follows:
   * { path: <path (path can be a regular expression)> }
   *
   * i.e.
   * const routes = [{ path: '/my/first/path' }]
   *
   * See documentation for further details
   *
   * http://presentation-layer.lmera.ericsson.se/euisdkdocs/#docs?chapter=tools&section=proxy
   */
  const routes = [];

  /**
   * Proxy all routes via the server specified in the start script.
   *
   * To start the project with a proxy setup to handle routes from
   * the "routes" array the following command is used:
   *
   * npm start -- --server=<path-to-you-application-server>
   *
   * eg.
   * npm start -- --server=http://localhost:3000
   *
   * Only use a proxy for the paths listed in the routes array.
   */
  process.argv.forEach((arg) => {
    if (arg.startsWith('--server')) {
      const _proxy = arg.substring(arg.indexOf('=') + 1);
      app.use('/', proxy(_proxy, {
        filter: req => routes.some(_route => RegExp(_route.path).test(req.path)),
      }));
    }
  });
};
