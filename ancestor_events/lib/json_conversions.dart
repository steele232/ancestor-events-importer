import 'dart:convert';
import "models.dart";

// Resource: https://codingwithjoe.com/dart-fundamentals-working-with-json-in-dart-and-flutter/

// encode with jsonEncode



class MyConversions {

  // AuthResponse toJson
  static String toJson(AuthResponse s) {
    Map<String, dynamic> map() =>
    {
      'accessToken': s.accessToken,
      'tokenType': s.tokenType,
      'token': s.token
    };

    String result = jsonEncode(map());
    return result;
  }

  // AuthResponse fromJson
  static AuthResponse fromJson(String jsonString) {
    Map<String, dynamic> json = jsonDecode(jsonString);
    String accessToken = json['accessToken'];
    String tokenType = json['tokenType'];
    DateTime token = DateTime.parse(json['token']);
    AuthResponse s = new AuthResponse(accessToken, tokenType, tokenType);
    return s;
  }



}
  


