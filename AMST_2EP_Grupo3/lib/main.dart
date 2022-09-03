import 'package:amst_2ep_grupo3/provider/google_sign_in.dart';
import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:amst_2ep_grupo3/shared/nav.dart';
import 'package:provider/provider.dart';
import 'firebase_options.dart';
import 'package:amst_2ep_grupo3/util/routes.dart';


Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,

  );
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  _AppState createState() => _AppState();
}

@override
Widget build(BuildContext context){
  return ChangeNotifierProvider(
      create: (context) => GoogleSignInProvider(),
      child: MaterialApp(
    title: 'Flutter Demo',
    theme: ThemeData(
      primarySwatch: Colors.blue,
    ),

      ),
  );

}


class _AppState extends State<MyApp> {
  final GlobalKey<NavigatorState> navigatorKey =
  new GlobalKey<NavigatorState>();

  @override
  void initState() {
    super.initState();
  }
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
        create: (context) => GoogleSignInProvider(),
    child: MaterialApp(
      title: 'Vacheck',
      navigatorKey: navigatorKey,
      initialRoute: '/',
      onGenerateRoute: Routes.generateRoute,
      theme: ThemeData(
        primaryColor: Colors.red[400],
      ),

    ),
    );
  }
}
