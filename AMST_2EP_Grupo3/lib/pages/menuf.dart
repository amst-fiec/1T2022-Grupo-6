import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class Menuflotante extends StatelessWidget{
  final List<PopupMenuEntry> menuList;
  //final Widget? icon;

  const Menuflotante({Key? key, required this.menuList}) : super(key: key);

  @override
  Widget build(BuildContext context){
    return PopupMenuButton(
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16.0),
      ),
      itemBuilder: ((context) => menuList),

    );

  }

}