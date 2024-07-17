package com.example.database_user.configuration;


import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseTest extends TestMongoDBContainer {

/*  protected String token;

  @Autowired
  private JWTService jwtService;


  @BeforeEach
  public void generateToken() {
    AuthUserDTO userDetailsDTO = AuthUserDTO.builder()
        .email("test@example.com")
        .password("testPassword")
        .build();
    token = jwtService.generateToken(userDetailsDTO);
  }

  @Test
  public void test_ok() {
    System.out.println("Test ok");
    Assertions.assertTrue(true);
  }*/
}
