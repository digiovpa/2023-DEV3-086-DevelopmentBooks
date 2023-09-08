# 2023-DEV3-086-DevelopmentBooks

This is a Java Maven Spring Boot application.

****************************
You can clone this repository using GIT or SVN and add it in your favorite IDE, then run the Application class to start it.
****************************
Since it is a REST webservice, you can test it for example via Postman; using a GET request.

GET http://localhost:8085/development-books/compute-pricing?[id]=[q] where [id] is the id of the software development book chosen and q the quantity picked of this book id.
Up to five [id]=[q] can be specificed in the request, each of them should be separated by the character & 

Example query:

GET http://localhost:8085/development-books/compute-pricing?1=2&2=2&3=2&4=1&5=1

This query means that we are picking:
- 2 Clean Code (Robert Martin, 2008)
- 2 The Clean Coder (Robert Martin, 2011)
- 2 Clean Architecture (Robert Martin, 2017)
- 1 Test Driven Development by Example (Kent Beck, 2003)
- 1 Working Effectively With Legacy Code (Michael C. Feathers, 2004)
  
****************************
List of software development books available:

id 1: Clean Code (Robert Martin, 2008)
id 2: The Clean Coder (Robert Martin, 2011)
id 3: Clean Architecture (Robert Martin, 2017)
id 4: Test Driven Development by Example (Kent Beck, 2003)
id 5: Working Effectively With Legacy Code (Michael C. Feathers, 2004)
