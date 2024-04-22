db = db.getSiblingDB('management');

db.createUser({
  user: 'test',
  pwd: 'test',
  roles: [
    {role: 'readWrite', db: 'management'}
  ]
});

db.users.insertOne({
  id: 2,
  createdAt: new Date(),
  email: "test0@example.com",
  isLocked: false,
  name: "John",
  surname: "Doe",
  password: "testPassword",
  loginHistory: [],
  passwordHistory: [],
  loginIpHistory: [],
  passwordUpdatedAt: new Date(),
  role: "admin",
});

