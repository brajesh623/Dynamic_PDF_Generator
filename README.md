
<h1 align="center">Dynamic_PDF_Generator</h1>

# Problem Statement:

Build a Spring Boot Application with REST API to generate PDF using Java Template Engine.

# Requirement
* REST API to accept data and generate a PDF based on the received data.
* Ability to download the above-generated PDF

Use Java Template Engines like Thymeleaf or iText to generate the PDF and store it on
the local storage which can be retrieved later. The input should have a proper schema.

Example
The request body of the API call looks something like this:
* {
"seller": "XYZ Pvt. Ltd.",
"sellerGstin": "29AABBCCDD121ZD",
"sellerAddress": "New Delhi, India",
"buyer": "Vedant Computers",
"buyerGstin": "29AABBCCDD131ZD",
"buyerAddress": "New Delhi, India",
"items": [
{
"name": "Product 1",
"quantity": "12 Nos",
"rate": 123.00,
"amount": 1476.00
}
]
}

- PDF Sample

![image](https://github.com/brajesh623/Dynamic_PDF_Generator/blob/main/Details.jpg)


# Note
* Only REST APIs are required. No UI.
* Testable on Postman / Swagger.
* Will prefer properly structured code.
* Will prefer Test Cases (TDD)

# Tech Stack Used
* Spring Boot
* Swagger
* Lombook
* ItextPDF
* Thymeleaf

# Direction to use
To use the project, follow the below steps:
* Clone the project from the GitHub repository.
* Open the project in your preferred Java IDE.
* Run the project.
* Use Postman or Swagger-UI to test the REST API.
The generated PDF document will be stored locally at "C:\\Users\\Public\\Downloads\" folder.
