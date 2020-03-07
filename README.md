# Welcome to Omnicuris!

This is an ecom backend application API document
# Pre-requisites
>MySql Database
>Create a database named **omnicuris**
>import the schema added in database-schema using below command
> > mysql omnicuris < omnicuris.sql

>give the password of your mysql in **application.properties**
> > spring.datasource.password=**mysqlpassword**


# API Doc
## Add new item/items

Path: **/features/addNewItem**
Method: **POST**
Request Body: 
>[
	   {
        "itemName": "Product-003",
        "manufacturer": "Omnicuris",
        "noOfItemAvailable": 4
    },
    {
        "itemName": "Product-004",
        "manufacturer": "Omnicuris",
        "noOfItemAvailable": 10
    }
]

## Get All Items

Path: **/features/allItems**
Method: **GET**

## Add item stock

Path: **/features/addNewItem**
Method: **POST**
Request Param: 
>id: Integer
>count: Integer

## Place Order/Orders

Path: **/features/placeOrder**
Method: **POST**
Request Body: 
>[
    {
        "itemId": 1,
        "email": "email_id@company.com",
        "noOfItem": 2
    },
    {
        "itemId": 4,
        "email": "email_id@company.com",
        "noOfItem": 2
    }
]

## Get All Orders

Path: **/features/allOrders**
Method: **GET**

## Get Orders based on Email

Path: **/features/ordersByEmail**
Method: **GET**
Request Param: 
>email: String

## Update an Order

Path: **/features/updateOrder**
Method: **PUT**
Request Body: 
>{
	"itemId" : 2,
	"email" : "email_id@company.com",
	"noOfItem" : 1
}

## Update an Item

Path: **/features/updateItem**
Method: **PUT**
Request Body: 
>{
	"id": 2,
	"itemName" : "Product-002",
	"manufacturer" : "Omnicuris",
	"noOfItemAvailable" : 10
}
