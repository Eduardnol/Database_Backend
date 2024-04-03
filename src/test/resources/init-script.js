db = db.getSiblingDB('user_management');

db.createUser({
  user: 'test_container',
  pwd: 'test_container',
  roles: [
    {role: 'readWrite', db: 'user_management'}
  ]
});

db.users.insertOne({
  id: 2,
  createdAt: new Date(),
  email: "mail@mailss.com",
  isLocked: false,
  name: "John",
  password: "password",
  surname: "Doe",
  loginHistory: [],
  passwordHistory: [],
  loginIpHistory: [],
  passwordUpdatedAt: new Date(),
  role: "admin",
});

