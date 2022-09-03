import 'package:flutter/material.dart';
import 'package:g3amst/models/product.dart';
import 'package:g3amst/ui/views/cart/cart_view.dart';
import 'package:g3amst/ui/views/home/home_view.dart';
import 'package:g3amst/ui/views/product/product_view.dart';
import 'package:g3amst/ui/views/splash.dart';
import 'package:g3amst/ui/views/login/login_view.dart';
import 'package:g3amst/ui/views/register/register_view.dart';

class Routes {
  static Route<dynamic> generateRoute(RouteSettings settings){
    final args = settings.arguments;
    switch (settings.name) {
      case '/':
        return MaterialPageRoute(builder: (_) => SplashView());
      case '/login':
        return MaterialPageRoute(builder: (_) => LoginView());
      case '/register':
        return MaterialPageRoute(builder: (_) => Registerview());
      case '/home':
        return MaterialPageRoute(builder: (_) => HomeView());
      case '/product-view':
        Product _product = args as Product;
        return MaterialPageRoute(builder: (_) => ProductView(_product));
      case '/cart-view':
        return MaterialPageRoute(builder: (_) => CartView());
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