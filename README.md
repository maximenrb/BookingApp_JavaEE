# Booking web app

**AirBnB like web application**

## Functionality
### Done
- Home page:
  - Research accommodations
  - View last offers
- Login & Register
- CRUD (with research, edit and delete):
  - Accommodation   
  - Offer
  - Booking (no edition and deletion)
  - User (for Administrator) 
- Add form:
  - Accommodation:
    - General informations
    - House rules
    - Add pictures
    - Add rooms and amenities 
  - Offer
- Booking page with booking and virtual transaction
- Wallet system

### To Do
- Evaluation
- Bookmark
- Java forms verifications

## Bugs


## Instalation
- Install a MySQL server on your favorite distribution
- Run the ` airbnbdb.sql ` script, alternately you can create a database named ` airbnbdb ` and set database connection informations in ` WebContent/WEN-INF/resources.xml `. Tables will be created automatically
- Download and set [Apache TomEE Plume v8.0.1](http://tomee.apache.org/download-archive.html)
- Launch server, by default TomEE use 8080 port, so you can access the home page in local at : ` localhost:8080/airbnb/home `
- You can log into the application with the email address ` admin@admin.com ` and the password ` admin ` if you have used the ` airbnbdb.sql ` script.

## Built With
- [J2EE](https://www.oracle.com/fr/java/technologies/java-ee-glance.html) - Java Enterprise Edition (Java EE) is a set of specifications, extending Java SE with specifications for enterprise features such as distributed computing and web services.
- [TomEE](https://tomee.apache.org/) - Apache TomEE is a lightweight, yet powerful, JavaEE Application server with feature rich tooling.
- [MySQL](https://www.mysql.com/) - MySQL Database Service is a fully managed database service to deploy cloud-native applications.
- [Bootstrap](https://getbootstrap.com/) - Bootstrap is a free and open-source CSS framework directed at responsive, designed for front-end web development.

## Author
- [Amot98](https://github.com/Amot98)
- [MaximeNrb](https://github.com/maximenrb)
- [Lekolix](https://github.com/Lekolix)

## License

<img align="left" width= "150" alt="CC Attribution-NonCommercial-NoDerivatives 4.0 International Public License Logo" src="https://raw.githubusercontent.com/maximenrb/BookingApp_JavaEE/master/git_img/by-nc-nd.eu.png">

This project is licensed under the CC Attribution-NonCommercial-NoDerivatives 4.0 International Public License

See the [LICENSE.md](https://github.com/maximenrb/TravelMap/blob/master/LICENSE.md) file for details