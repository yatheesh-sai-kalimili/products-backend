Products Backend Spring Boot Application 

The application has the following endpoints.

First endpoint:  http://localhost:8081/api/products/upload 
Takes an excel sheet as an input. The sheet has three columns: First column has dates; the second column has the corresponding price of the products, and the third column has the product ID. The application stores this data in the database (PostgreSQL). 

Second endpoint: http://localhost:8081/api/products/productsInformation
For any given day the second endpoint should give the price, name, and interest rate of the product. Inputs should be date and product ID. 

Third endpoint:  http://localhost:8081/api/products/priceHistory/{productId}
For input as product ID, return prices for the next 3 days. 

Fourth endpoint: http://localhost:8081/api/products/priceHistory
Fetches all the price history data

Tables created:
Table name: Product_details
 Columns:
 product_name – Character Varying (255)
 product_id (Primary Key) - integer
 Interest_rate - Numeric
 start_date - Date
 maturity_date - Date
Table_name: price_history
 Columns:
 history_id (Primary Key) –bigint
 history_date - date
 price_on_that_day - numeric 
 product_id – integer
 
 Note: Please create tables for testing all the endpoints as automatic table creation is having issues with the data type of the columns.
 
 https://user-images.githubusercontent.com/98400559/151169305-140b96ce-cb52-4bf0-8f4b-e47415fdac0c.PNG
 
 
 
 
 
