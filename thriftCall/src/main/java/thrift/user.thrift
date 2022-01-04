namespace java com.example.demo

struct User {
  1:  string firstName;
  2:  string lastName;
  3:  string email;
}

service UserService {
  list<User> getUsers();
  User saveUser(1:User user)
}