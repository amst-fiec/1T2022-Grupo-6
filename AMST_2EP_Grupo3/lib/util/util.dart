import 'package:flutter/material.dart';
class Util {
  static Size sizeScreen({required BuildContext context}) {
    MediaQueryData queryData = MediaQuery.of(context);
    return queryData.size;
  }
}