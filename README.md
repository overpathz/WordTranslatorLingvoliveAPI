# MonobankRestClient

## Technologies

- ##### Java 8
- ##### Spring (RestTemplate Bean)

### Let's try to get the personal data:
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        ClientConnection connection = context.getBean("connection", ClientConnection.class);
        PersonalData data = connection.getClientInfo();
        System.out.println(data);

![image](https://i.ibb.co/HNLzKmG/image.png)

// You need the X-Token to send request to get the response with the client info
// You can get the X-Token here: https://api.monobank.ua/    (for Monobank users)
// Enter this token in application.properties file
