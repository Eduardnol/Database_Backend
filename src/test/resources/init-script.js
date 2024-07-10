db.createUser({
  user: 'root',
  pwd: 'root',
  roles: [
    {
      role: 'readWrite',
      db: 'testContainer',
    },
  ],
});

use('testContainer');

db.createCollection('users', {capped: false});
db.createCollection('persona', {capped: false});
db.createCollection('discipuladoMenores', {capped: false});
db.createCollection('customFields', {capped: false});

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

