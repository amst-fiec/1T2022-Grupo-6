import 'package:amst_2ep_grupo3/pages/bienvenida.dart';
import 'package:amst_2ep_grupo3/pages/loggedin.dart';
import 'package:amst_2ep_grupo3/shared/nav.dart';
import 'package:flutter/material.dart';
import 'package:amst_2ep_grupo3/pages/splash.dart';


class Routes {
  static Route<dynamic> generateRoute(RouteSettings settings){
    final args = settings.arguments;
    switch (settings.name) {
      case '/':
        return MaterialPageRoute(builder: (_) => SplashView());
      case '/login':
        return MaterialPageRoute(builder: (_) => HomePage());
      case '/profile':
        return MaterialPageRoute(builder: (_) => LoggedIn());
      default:
//Aquí se irán agregando las rutas por cada clase dart creada.
    return _errorRoute();
  }
  }
  static Route<dynamic> _errorRoute() {
    return MaterialPageRoute(builder: (_) {
      return Scaffold(
        appBar: AppBar(
          title: const Text("Error"),
          centerTitle: true,
        ),
        body: const Center(
          child: Text("ERROR"),
        ),
      );
    });
  }
}