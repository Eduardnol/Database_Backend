db = new Mongo().getDB("testContainer");

db.createCollection('users');
db.createCollection('persona');
db.createCollection('discipuladoMenores');
db.createCollection('customFields');

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

