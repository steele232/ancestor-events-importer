

class AuthResponse {
  final String accessToken;
  final String tokenType;
  final String token;

  AuthResponse(this.accessToken, this.tokenType, this.token);
}

class Person {
  String id;
  DisplayPerson display;
}

class CurrentPerson {
  String description;
  List<Person> persons;
  List<ChildAndParentsRelationship> childAndParentsRelationships;
}

class DisplayPerson {
  String name;
  String gender;
  String lifespan;
  String birthDate;
}

class ChildAndParentsRelationship {
  String id;
  FamilyMember mother;
  FamilyMember father;
  FamilyMember child;
}

class FamilyMember {
  String resourceId;
}

class Ancestry {
  List<Person> persons;
}
