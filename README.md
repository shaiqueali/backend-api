
# Challenge:
You are being asked to assist a Bank with its Document Management System, as part of its Credit
Journey Digitization initiative. The Bank is using Java Spring, and Spring Boot for its systems.
The Bank asked you to develop the system that covers the User stories below, in a RESTful manner.
During the Sprint Planning Session that you attended, it was estimated that the effort required shouldn&#39;t
cost more than 2 Developer Story Points. Experience, has shown that 2 Developer Story Points have
been accomplished in a quarter of a day of a Developer&#39;s daily productive time, but this is obviously not
always the case.
1. Tasks - Basic RESTful API:
A. As a User, I want to be able to upload a PDF Document, so that I can later perform other actions with
it (e.g. view it, edit it, remove it etc)
B. As a User, I want to be able to remove my uploaded Document, so that I exercise my GDPR &quot;Right to
be forgotten&quot;
C. As a User, I want to be able to be able to view all my uploaded documents, so that I can select one
and view it
It is implied that the stories adhere to the non-functional requirements mentioned below, and
specifically but not exclusively, to the testing coverage requirements measured with JaCoCo.
In addition to the system described above, the Tech Lead of the Team decided it&#39;s a good idea to
leverage a 3rd party provider for the Posts and Comments functionality, so that the Team outsources
the horizontal scalability demands of Posts and Comments. The Tech Lead performed research and
came up with the free service, provided here: https://jsonplaceholder.typicode.com/guide.html. You are
now requested to leverage the 3rd Party API in order to accomplish the tasks described below.
2. Integration with 3rd Party Posts and Comments API:
A. As a User, I want to create a Post and associate it with an existing Document that I have uploaded in
the Bank&#39;s system, so that other Users can later engage and comment
B. As a User, I want to be able to view the Posts that are associated with a Document, so that I can read
them
C. As a User, I want to create Comments and associate them with Documents that I have uploaded in the
Bank&#39;s system, so that I can respond to Posts
The Tech Lead got an overview of the all stories above, and she shared with you some insights:
1: You can assume that an in-memory database should suffice for all the tasks
2: You shall assume that the 3rd Party System isn&#39;t highly available. You are advised to include retry and
fallback logic for your integration. You will receive extra credit if you manage to make use of off-the-
shelf libraries like Feign (https://github.com/OpenFeign/feign)
2.A: You can assume a ZeroOrOne-to-One relationship of a Post and a Document entity
Non-functional requirements:
- The system has to have at least 60% Unit testing and 60% Integration testing coverage, when
measured with JaCoCo
- The system&#39;s APIs have to abide to RESTful API best practices
- Document format supported is PDF

- The front-end part of the system will be developed by the dedicated team, therefore, you are only
asked to provide the API for the requested functionalities
- The system shall be implemented in Java Spring, using Gradle as build tool
