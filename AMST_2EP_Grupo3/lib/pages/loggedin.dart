import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:amst_2ep_grupo3/provider/google_sign_in.dart';
import 'package:google_sign_in/google_sign_in.dart';

class LoggedIn extends StatelessWidget{
  final user = FirebaseAuth.instance.currentUser!;

  @override
  Widget build(BuildContext context){
    return Scaffold(
      body: Container(
        alignment: Alignment.center,
        color: Colors.blueGrey.shade900,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text(
            'Perfil',
          style: TextStyle(fontSize: 24),
        ),
        CircleAvatar(
          radius: 40,
          backgroundImage: NetworkImage(user.photoURL!),
        ),
        SizedBox(height: 8),
            Text(
              'Name: ' + user.displayName!,
              style: TextStyle(color: Colors.blue, fontSize: 16),
            ),
            SizedBox(height: 8),
            Text(
              'Email: ' + user.email!,
              style: TextStyle(color: Colors.blue, fontSize: 16),
            ),
            SizedBox(height: 8),
          ],
        ),
      ),
    );
  }

}