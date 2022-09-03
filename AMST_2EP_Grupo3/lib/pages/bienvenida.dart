import 'package:amst_2ep_grupo3/provider/google_sign_in.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:provider/provider.dart';


class HomePage extends StatefulWidget{
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage>
    with WidgetsBindingObserver {
  @override
  Widget build(BuildContext context){
    return Container(
      color: Colors.white54,
      padding: EdgeInsets.symmetric(horizontal: 18),
      child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(Icons.home,color:
            Colors.black,size: 190,),
            Text("Bienvenido",
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 60,
                  fontWeight:
                  FontWeight.bold
              ),
            ),
            Text("Bienvenido a la aplicacion multiplataforma",
              style: TextStyle(
                  color: Colors.black,
                  fontWeight: FontWeight.w300
              )
              ,),
            Text("\n "
                "AMST 2EP Grupo 3"
                "\n",
              style: TextStyle(
                  color: Colors.lightBlueAccent,
                  fontSize: 20,
                  fontWeight:
                  FontWeight.bold
              ),
            ),
            ElevatedButton.icon(
              style: ElevatedButton.styleFrom(
                primary: Colors.blue,
                onPrimary: Colors.white,
                minimumSize: Size(double.infinity, 50),
              ),
              icon: FaIcon(FontAwesomeIcons.google, color: Colors.red),
              label: Text('Iniciar Sesion con Google'),
              onPressed: (){
                final provider = Provider.of<GoogleSignInProvider>(context, listen: false);
                provider.googleLogin();
              },
            ),

          ]),
    );
  }
}