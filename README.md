To run the application in cmd use mvn exec:java -Dexec.mainClass="com.sysaid.assignment.AssignmentApplication", then use "http://localhost:8080/userName".  
  
API:  
To add a random task from external API to a local fakeDB: /add-new-task  
To add a task by key from external API to a local fakeDB: /api/add-task-by-key/{taskKey}  
To add a task to a user's completed tasks: /api/completed-tasks/add-task/{userName}/{taskKey}  
To add a task to a user's wishlist: /api/wishlist/add-task/{userName}/{taskKey}  

What can be added/improved in order for the code to be "production-ready”:  
Add a database and normal interaction with it. Add authorization and user session. Add protection for APIs against DDoS attacks to keep the cost of paid calls to third-party APIs under control. Add unit tests and automated UI tests.  

