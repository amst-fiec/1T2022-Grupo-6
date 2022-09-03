import 'package:flutter/material.dart';
import 'package:amst_2ep_grupo3/pages/bienvenida.dart';

class Nav extends StatefulWidget {
  @override
  _NavState createState() => _NavState();

}


class _NavState extends State<Nav> {

  int _currentIndex = 1;
  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text('AMST 2EP Grupo3'),
      ),
      body: _llamarPagina(_currentIndex),
    );
  }



  Widget _llamarPagina(int paginaActual) {
    switch (paginaActual) {
      default:
        return HomePage();

    }
  }
}