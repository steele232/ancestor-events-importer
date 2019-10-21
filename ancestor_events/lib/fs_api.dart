import 'models.dart';
import 'json_conversions.dart';

import 'dart:async';
import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;

class ApiService {
  final String _authURL =
      "https://identint.familysearch.org/cis-web/oauth2/v3/token";
  AuthResponse _auth;

  ApiService() {
    print("Hello ApiService");

    var post = fetchPost();

    // initialAuth();
  }

  Future<Post> fetchPost() async {
    final response =
        await http.get('https://jsonplaceholder.typicode.com/posts/1');

    if (response.statusCode == 200) {
      // If server returns an OK response, parse the JSON.
      return Post.fromJson(json.decode(response.body));
    } else {
      // If that response was not OK, throw an error.
      throw Exception('Failed to load post');
    }
  }

  // Future<AuthResponse> initialAuth() async {
  //   var httpClient = createHttpClient();

  //   // request.headers.add("Accept", "application/json");
  //   // request.headers.add("Content-Type", "application/x-www-form-urlencoded");
  //   // request.write('password=1234pass&grant_type=password&client_id=a02f100000RMy4WAAT&username=tum000147359');
  // }
}

class Post {
  final int userId;
  final int id;
  final String title;
  final String body;

  Post({this.userId, this.id, this.title, this.body});

  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
      userId: json['userId'],
      id: json['id'],
      title: json['title'],
      body: json['body'],
    );
  }
}
