import 'package:amst_2ep_grupo3/pages/loggedin.dart';
import 'package:amst_2ep_grupo3/pages/profile.dart';
import 'package:amst_2ep_grupo3/pages/menuf.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:provider/provider.dart';


class PagPrincipal extends StatefulWidget{
  final FirebaseAuth firebaseAuth = FirebaseAuth.instance;
  @override
  _PagPrincipalState createState() => _PagPrincipalState();
}

class _PagPrincipalState extends State<PagPrincipal>
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
            Text("AMST - Grupo 3",
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 60,
                  fontWeight:
                  FontWeight.bold
              ),
            ),
            Text("\n "
                "Ivan Mieles"
                "\n"
                "Cristobal Lara"
                "\n",
              style: TextStyle(
                  color: Colors.black,
                  fontSize: 20,
                  fontWeight: FontWeight.w300
              )
              ,),
            ElevatedButton(
              style: ElevatedButton.styleFrom(
                primary: Colors.blue,
                onPrimary: Colors.white,
                minimumSize: Size(double.infinity, 50),
              ),
              child: Text('Menu'),
              onPressed: (){
                PopupMenuButton<int>(
                  itemBuilder: (context) => [
                    //PopupMenuItem1
                    PopupMenuItem(
                      value: 1,
                      child: ListTile(
                        leading: Icon(
                          Icons.face,
                        ),
                        title: Text("Perfil"),
                      ),
                    ),
                    // PopupMenuItem 2
                    PopupMenuItem(
                      value: 2,
                      child: ListTile(
                        leading: Icon(
                          Icons.message,
                        ),
                        title: Text("Visualizar mensajes"),
                      ),
                    ),
                    // PopupMenuItem 3
                    PopupMenuItem(
                      value: 3,
                      child: ListTile(
                        leading: Icon(
                          Icons.exit_to_app,
                        ),
                        title: Text("Cerrar Sesion"),
                      ),
                    ),
                  ],
                  onSelected: (value){
                    if (value == 1){
                      LoggedIn();
                    }else if (value == 2){

                    }else if (value == 3){

                    }
                  },

                );

                /*
                Menuflotante(
                    menuList: const [
                   PopupMenuItem(
                     value: 1,
                      child: ListTile(
                        leading: Icon(
                        Icons.face,
                    ),
                    title: Text("Perfil"),
                  ),
                  ),
                      PopupMenuItem(
                        value: 2,
                child: ListTile(
                leading: Icon(
                Icons.message,
                ),
                title: Text("Visualizar mensajes"),
                ),
                      ),
                      PopupMenuDivider(),
                      PopupMenuItem(
                        value: 3,
                        child: ListTile(
                          leading: Icon(
                            Icons.exit_to_app,
                          ),
                          title: Text("Cerrar Sesion"),
                        ),
                      ),
                ],);


                 */
              },
            )



          ]),
    );
  }
}