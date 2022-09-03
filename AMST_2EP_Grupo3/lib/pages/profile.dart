import 'package:amst_2ep_grupo3/pages/paginaprincipal.dart';
import 'package:amst_2ep_grupo3/shared/nav.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';



class Profile extends StatelessWidget{
  @override
  Widget build(BuildContext context) => Scaffold(
    body: StreamBuilder(
      stream: FirebaseAuth.instance.authStateChanges(),
      builder: (context, snapshot){
        if (snapshot.connectionState == ConnectionState.waiting) {
          return Center(child: CircularProgressIndicator());
        } else if (snapshot.hasData){
          return PagPrincipal();
        }
        else if (snapshot.hasError){
          return Center(child: Text('Algo salio mal!'));
        } else {
          return Nav();
          //return SignUpWidget();
        }
      },
    ),
  );
}
